package fun.soops.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

//        ServletServerHttpRequest serverHttpRequest =  (ServletServerHttpRequest) request;
//        HttpSession session = serverHttpRequest.getServletRequest().getSession();
//
//        // 在握手前验证是否存在用户信息，不存在时拒绝连接
//        String username = (String) session.getAttribute("username");
//
//        if (null == username) {
//            System.out.println("握手失败");
//            return false;
//        } else {
//            // 将用户信息放入WebSocketSession中
//            attributes.put("username", username);
//            // httpSessionId用于唯一确定连接客户端的身份
//            attributes.put("sessionId", session.getId());
//            System.out.println("握手成功 你好! " + username);
//            return true;
//        }
        //TODO


        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
