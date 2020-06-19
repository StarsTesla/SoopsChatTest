package fun.soops.web;

import fun.soops.entity.File;
import fun.soops.entity.User;
import fun.soops.service.FileService;
import fun.soops.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
     // 注入service
    @Autowired
    private UserService userService;
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");   // 获取session信息，移除session，直接返回登录界面，并连接跳转。
        return "redirect:login";
    }
    @PostMapping("/login")
    public String login(@Param("username")String username, @Param("password")String password, HttpSession session){
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
    public void update(@Param("username")String username , @Param("password")String password ,@Param("birth") Date birth , @Param("avatar")File avatar){
        //头像上传 未完成 修改个人信息 要调用先暂停 后台测试已通过
    }

    @Autowired
    FileService fileService;

//    public Boolean isUsername(){
//        return null;
//    }

    @GetMapping("/register")
    public ModelAndView Register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Param("username") String username, @Param("password") String password,
                                   @Param("birth") Date birth, @Param("realName") String realName) {
//        String username, String password, Date birth, File file
        try {
            User isUser = userService.getUserByUsername(username);
            if (isUser != null && isUser.equals(username)) {
                System.out.println("已存在用户！！");
                return new ModelAndView("redirect:/register.html");
            } else {
                File file = fileService.addAvatar(realName);
                User user = userService.insertUser(username, password, birth, file);
                System.out.println(user);
                System.out.println("注册成功！！");
            }
        } catch (RuntimeException e) {
            return new ModelAndView("redirect:/register.html");
        }
        return new ModelAndView("redirect:/login.html");
    }


    @PostMapping("/isUserName")
    public String isUserName(@Param("username") String username) {
        Boolean flag = userService.isUserName(username);
        System.out.println(flag);
        if (flag) {
            return "no";
        } else {
            return "ok";
        }
    }

    @PatchMapping("/login")
    public String goLogin() {
        return "login";
    }
}
