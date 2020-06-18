package fun.soops.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Author:Stars
 * Description:websocket拦截器
 * 如果用户信息没存在于session中的话，说明用户未登录
 * 如果存在，就存到WebSocketSession 中，TextHandler就能获取到这个信息了
 */


@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private Logger log = LoggerFactory.getLogger(WebSocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        //获取session
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        HttpSession session = serverHttpRequest.getServletRequest().getSession();

        // 在握手前验证是否存在用户信息，即是否登录，不存在时拒绝连接
        String username = (String) session.getAttribute("username");
        String userId = (String) session.getAttribute("userId");


        if (null == username) {
            log.info("握手失败");
            log.warn("用户未登录");
            //System.out.println("握手失败");
            return false;//TODO 返回至登录页面
        } else {
            // 将用户信息放入WebSocketSession中
            attributes.put("username", username);
            attributes.put("userId", userId);
            log.info("握手成功 你好! " + username);
            //System.out.println("握手成功 你好! " + username);
            return true;//放行
        }
        //TODO


        //   return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        //System.out.println("After Handshake");
        log.info("握手后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
