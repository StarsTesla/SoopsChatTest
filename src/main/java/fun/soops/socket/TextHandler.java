package fun.soops.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.soops.service.ChatService;
import fun.soops.service.UserService;
import fun.soops.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * Author:Stars
 * Description:主要的websockt处理中心
 * 保存和解除session
 * 接收消息和发送给相应的用户，用json来解析发往哪个用户
 */

//TODO 文件的发送和接收

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

    public TextHandler() {
    }

    //websocket 建立后
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("afterConnectionEstablished");
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId"); //取出拦截器存入的yserId
        clients.put(userId, session); //将其userID和session绑定放到map里
        log.info("Websock with " + attributes.get("username") + " has established");
//        System.out.println("afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload(); //取出原始的字符串
        //String param[] = msg.split("\\|");
//        System.out.println(msg);
        Message messageObj = mapper.readValue(msg, Message.class); //将json字符串转化为Message对象
        Map<String, Object> attributes = session.getAttributes();
        String username = (String) attributes.get("username"); //浏览器未存username，只知道交流的session是谁
        messageObj.setFromUser(username); // TODO 优化需前端使用cookie来进行存储username
        String jsonStr = mapper.writeValueAsString(messageObj); //将写入fromUser的message对象重新做成json字符串
        messages.add(messageObj); //报存消息对象，达到一定量后就持久化
//        System.out.println("json字符串转为对象：" + messageObj.getContent());
//        System.out.println("对象转为json字符串：" + jsonStr);

        String ToUserId = userService.getUserByUsername(messageObj.getToUser()).getId();

        WebSocketSession SendUser = clients.get(ToUserId); // 获取发送对象的session
        if (SendUser != null) {
            SendUser.sendMessage(new TextMessage(jsonStr)); //发送消息
        } else {
            log.info("好友不在线................"); //如果没有session，说明他没上线
        }
        if (messages.size() > 30) {  //如果message大于30条就存入数据库，下面还有关闭session时也存入数据库的操作
            saveMessagesToHistory();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
//        System.out.println("======================closed=========================");
        //获取 session 中用户名 并移除 clients 中对应的session
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");
        clients.remove(userId);

        log.info("Websock with " + attributes.get("username") + " has closed");
//        System.out.println("Closed and save.............");

        saveMessagesToHistory(); //保存剩下的消息

    }

    public void saveMessagesToHistory() {
        chatService.saveHistory(messages);
        messages.clear();
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
