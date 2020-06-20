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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
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

    @Autowired
    FileService fileService;

    @GetMapping("/register")
    public ModelAndView Register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@Param("username") String username, @Param("password") String password,
                                   @Param("birth") Date birth, @Param("realName") String realName, HttpSession session, MultipartFile filename) {

        try {
            User isUser = userService.getUserByUsername(username);
            if (isUser != null && isUser.equals(username)) {
                System.out.println("已存在用户！！");
                return new ModelAndView("redirect:/register.html");
            } else {

                File file = fileService.addAvatar(realName);
                User user = userService.insertUser(username, password, birth, file);
                System.out.println(user);
                System.out.println("register success");
            }
        } catch (RuntimeException e) {
            System.out.println("catch");
            System.out.println(username);
            System.out.println(password);
            return new ModelAndView("redirect:/register.html");
        }
        System.out.println("goLogin");
        return new ModelAndView("redirect:login");
    }

    //用于验证用户名是否存在问题
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

    //将上传文件保存本地target
    @RequestMapping("/savefile")
    public String saveFile(MultipartFile realName, HttpSession session) throws IOException {
        System.out.println("upload file");
        //获取上传文件的原始名称
        String fileName = realName.getOriginalFilename();
        //生成唯一UUID
        String uuid = UUID.randomUUID().toString();
        //获取文件后缀
        String ext = FilenameUtils.getExtension(fileName);
        //拼接完整唯一文件名
        String uniqueFilename = uuid + "." + ext;
        //获取文件类型
        String fileType = realName.getContentType();
        System.out.println(uniqueFilename);
        System.out.println(fileType);

        //保存文件到target中
        String realPath = session.getServletContext().getRealPath("/upload");
        System.out.println(realPath);
        realName.transferTo(new java.io.File(realPath + "\\" + uniqueFilename));
        return "ok";
    }


    //
    @RequestMapping("/searchUser")
    public List<User> searchUser(@Param("searchUsername") String searchUsername) {
        List<User> users = userService.searchUserByUsername(searchUsername);

        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @GetMapping("/getMe")
    public User getMe(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User me = userService.getUserByUsername(username);
        return me;
    }

}
