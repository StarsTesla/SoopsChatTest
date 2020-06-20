package fun.soops.web;

import com.alibaba.druid.support.json.JSONUtils;
import fun.soops.entity.Message;
import fun.soops.entity.User;
import fun.soops.service.ChatService;
import fun.soops.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:Stars
 * Description: 处理聊天的接口
 */


@RestController
@RequestMapping(value = "/api/chat")
@CrossOrigin("*")  //允许跨域
public class ChatController {
    private Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;

    //TODO 这只是个临时的接口，真正的接口应该是登录在UserController
    @PostMapping("/addTempUser")
    public Map<String, String> addTempUser(String username, HttpSession session) {
        log.info("addTempUser");
        session.setAttribute("username", username);
        User user = userService.getUserByUsername(username);
        System.out.println(user);
        session.setAttribute("userId", user.getId());
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", "200");
        params.put("msg", "jj");
        return params;
    }

    //添加聊天窗口，但目前前端控制发给谁，暂时没用
    @CrossOrigin("*")
    @PostMapping("/addChatWindow")
    public Map<String, String> addChatWindow(String friendName, HttpSession session) {
        log.info("addChatWindow with " + friendName);
        // 获取它的朋友，并加入到session中，这样ws那边就能取出来了
        // 目前只能一对一
        User friend = userService.getUserByUsername(friendName);
        session.setAttribute("friendId", friend.getId());
        session.setAttribute("friendName", friendName);
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", "200");
        params.put("msg", "ok");
        return params;

    }

    //获取聊天记录
    @GetMapping("/getChatHistory")
    public List<Message> getChatHistory(String friendName, HttpSession session) {
        String username = (String) session.getAttribute("username");

        User user1 = userService.getUserByUsername(username);
        User user2 = userService.getUserByUsername(friendName);

        List<Message> messages = chatService.getHistoryBy2User(user1, user2);

        return messages;
    }


}
