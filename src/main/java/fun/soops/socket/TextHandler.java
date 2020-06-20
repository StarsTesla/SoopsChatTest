package fun.soops.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.soops.entity.User;
import fun.soops.service.ChatService;
import fun.soops.service.FileService;
import fun.soops.service.FriendService;
import fun.soops.service.UserService;
import fun.soops.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * Author:Stars
 * Description:
 * 主要的websockt处理中心
 * 保存和解除session
 * 接收消息和发送给相应的用户，用json来解析发往哪个用户
 * 服务器发送的message主要有 文本内容（text) , 图片(image) , 上下线通知(line)
 * 当然，所有的内容都是做好json化的字符串，前端主要解析后判断type来进行操作
 */


public class TextHandler extends TextWebSocketHandler {

    //用Map来保存所有已经加入通信的客户端
    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();
    private final Logger log = LoggerFactory.getLogger(TextHandler.class);
    public static ObjectMapper mapper = new ObjectMapper(); //json处理对象
    public static List<Message> messages = new ArrayList<Message>();
    //final Lock writeLock;

    @Autowired
    private UserService userService;

    @Autowired
    ChatService chatService;
    @Autowired
    FileService fileService;

    @Autowired
    FriendService friendService;

    final BASE64Encoder encoder = new BASE64Encoder();
    final BASE64Decoder decoder = new BASE64Decoder();


    public TextHandler() {
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
        log.info("Binary Message comming......");

    }

    //websocket 建立后
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("afterConnectionEstablished");
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId"); //取出拦截器存入的yserId
        String username = (String) attributes.get("username");
        clients.put(userId, session); //将其userID和session绑定放到map里
        log.info("Websocket with " + attributes.get("username") + " has established");
        System.out.println("afterConnectionEstablished");

        Message onlineMessage = new Message("Online", username, "All friends", "line", new Date());
        String jsonStr = mapper.writeValueAsString(onlineMessage);

        List<User> friends = friendService.getFriends(userId);

        for (User friend : friends)  //遍历所有的好友 并发送 自己 已上线的通知
        {
            WebSocketSession wss = clients.get(friend.getId());
            if (wss != null) {
                wss.sendMessage(new TextMessage(jsonStr));
                //创建所有已经在线的好友信息，并发给自己,这里的date 没有实际意义，只是记录发信时间
                Message onlineMessageOfFriend = new Message("Online", friend.getUsername(), username, "line", new Date());
                String jsonStr2 = mapper.writeValueAsString(onlineMessageOfFriend);
                session.sendMessage(new TextMessage(jsonStr2));
            }
        }


    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Text  Messsage coming..........");

        String msg = message.getPayload(); //取出原始的字符串
        Map<String, Object> attributes = session.getAttributes();
        String username = (String) attributes.get("username"); //浏览器未存username，只知道交流的session是谁
        Message messageObj = mapper.readValue(msg, Message.class); //将json字符串转化为Message对象

        messageObj.setFromUser(username); // TODO 优化需前端使用cookie来进行存储username

        if (messageObj.getType() == "image") {
            String uuid = fileService.saveFile(messageObj.getContent());
            messageObj.setContent(uuid); //将文件存入磁盘，并将messageObj的内容更新为图片文件id
        }

        String ToUserId = userService.getUserByUsername(messageObj.getToUser()).getId();  //获取发送对象
        WebSocketSession reciver = clients.get(ToUserId); // 获取发送对象的session

        messages.add(messageObj); //报存消息对象，达到一定量后就持久化
        String jsonStr = mapper.writeValueAsString(messageObj); //将写入fromUser的message对象重新做成json字符串
        if (reciver != null) {
            reciver.sendMessage(new TextMessage(jsonStr));
        }
        if (messages.size() > 30) {  //如果message大于30条就存入数据库，下面还有关闭session时也存入数据库的操作
            saveMessagesToHistory();
        }

        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

//        System.out.println("======================closed=========================");
        //获取 session 中用户名 并移除 clients 中对应的session
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");
        String username = (String) attributes.get("username");
        clients.remove(userId);  //从服务器的session集合中移除这个用户的session

        Message offlineMessage = new Message("Offline", username, "All friends", "line", new Date());
        String jsonStr = mapper.writeValueAsString(offlineMessage);

        List<User> friends = friendService.getFriends(userId);

        for (User friend : friends)  //遍历所有的好友 并发送 user 下线的通知
        {
            WebSocketSession wss = clients.get(friend.getId());
            if (wss != null) {
                wss.sendMessage(new TextMessage(jsonStr));
            }
        }
        log.info("Websock with " + username + " has closed");
//        System.out.println("Closed and save.............");
        attributes.remove("userId");
        attributes.remove("username");

        saveMessagesToHistory(); //保存剩下的消息
        super.afterConnectionClosed(session, status);
    }

    public void saveMessagesToHistory() {
        if (messages.size() > 0) {
            chatService.saveHistory(messages);
            messages.clear();
        }

        // TODO 写锁报错了
//        this.writeLock.lock();
//
//        try {
//
//        }
//        finally{
//            this.writeLock.unlock();
//        }
    }


}
