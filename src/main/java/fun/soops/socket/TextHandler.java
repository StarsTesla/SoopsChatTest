package fun.soops.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.soops.service.UserService;
import fun.soops.web.Message;
import org.apache.commons.lang3.StringUtils;
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
import java.util.concurrent.atomic.AtomicInteger;

public class TextHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();
    private final Logger log = LoggerFactory.getLogger(TextHandler.class);
    public static ObjectMapper mapper = new ObjectMapper();
    public static List<Message> messages = new ArrayList<Message>();

    @Autowired
    private UserService userService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("afterConnectionEstablished");
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");
        clients.put(userId, session);
        log.info("Websock with " + attributes.get("username") + " has open");
//        System.out.println("afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload();
        //String param[] = msg.split("\\|");
        System.out.println(msg);
        Message messageObj = mapper.readValue(msg, Message.class);
        Map<String, Object> attributes = session.getAttributes();
        String username = (String) attributes.get("username");
        messageObj.setFromUser(username);
        String jsonStr = mapper.writeValueAsString(messageObj);
        messages.add(messageObj);
        System.out.println("json字符串转为对象：" + messageObj.getContent());
        System.out.println("对象转为json字符串：" + jsonStr);

        String ToUserId = userService.getUserByUsername(messageObj.getToUser()).getId();

        WebSocketSession SendUser = clients.get(ToUserId);
        if (SendUser != null) {
            SendUser.sendMessage(new TextMessage(jsonStr));
        } else {
            log.info("好友不在线................");
        }
        if (messages.size() > 10) {
            //存入数据库
            //TODO
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("======================closed=========================");

        //获取 session 中用户名 并移除 clients 中对应的session
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");
        clients.remove(userId);
        log.info("Websock with " + attributes.get("username") + " has closed");
        System.out.println("Closed.............");

        //存入数据库
        //TODO
    }


}
