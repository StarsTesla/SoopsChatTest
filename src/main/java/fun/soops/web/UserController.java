package fun.soops.web;

import fun.soops.entity.User;
import fun.soops.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {
     // 注入service
    @Autowired
    private UserService userService;
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session){
        System.out.println("login:::username:::" + username + ", password:::" + password);
        User user = userService.login(username, password);
        if (user != null ) {
            //登录成功
            //session保存
            session.setAttribute("loginUser", user);
            return "success";
        }
        else {
            //登录失败

            return "fail";
        }
    }
    @PostMapping("/update")
    public String update(User user){
        userService.update(user);
        return user.getUsername();
    }
    //TODO

}
