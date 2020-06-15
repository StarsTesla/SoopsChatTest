package fun.soops.socket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TextHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<String, WebSocketSession>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
//
//        Map<String, Object> attributes = session.getAttributes();
//        clients.put((String)attributes.get("username"),session);
//        System.out.println("afterConnectionEstablished");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);


    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
//        String msg = message.getPayload();
//        String param[] = msg.split("\\|");
//        WebSocketSession SendUser = clients.get(param[1]);
//        if(SendUser != null)
//        {
//            System.out.println(param[1]);
//            String s = param[0];
//            SendUser.sendMessage(new TextMessage(s));
//        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
//
//        //获取 session 中用户名 并移除 clients 中对应的session
//        Map<String, Object> attributes = session.getAttributes();
//        clients.remove(attributes.get("username"));
//        System.out.println("Closed");
    }
}
