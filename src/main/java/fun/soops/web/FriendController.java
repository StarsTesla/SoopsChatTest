package fun.soops.web;

import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.FriendService;
import fun.soops.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:Stars
 * Description:处理好友的接口
 */


@RestController
@RequestMapping("/api/friend")
@CrossOrigin("*")  //允许跨域
public class FriendController {

    @Autowired
    @Qualifier("friendService")
    private FriendService friendService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(FriendController.class);

    // TODO 将接口返回都改成Json，用map和jackson


    @GetMapping("/getFriends")
    public List<User> getFriends(HttpSession session) {
        log.info("查询所有好友 ");
        String userId = (String) session.getAttribute("userId");
        log.info("UserID:" + userId);
        List<User> friends = friendService.getFriends(userId);
        return friends;

    }


    @PostMapping(value = "/addFriend")
    public Map<String, String> addFriend(String friendName, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        User friend = userService.getUserByUsername(friendName);

        Friend friendRel = friendService.getFriendBy2UsersId(userId, friend.getId());
        Map<String, String> data = new HashMap<String, String>();
        if (friendRel != null) {

            data.put("msg", "已经存在好友关系");
            data.put("code", "400");
            log.warn("已经存在好友关系");
            return data;
        }

        friendService.insertFriend(userId, friend.getId());
        log.info("insert friend of " + userId + " and " + friend.getId());
        data.put("msg", "添加成功");
        data.put("code", "200");
        return data;

    }

    @PostMapping("/deleteFriend")
    public Map<String, String> deleteFriend(String friendName, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        User friend = userService.getUserByUsername(friendName);
       // Friend friendRel = friendService.getFriendBy2UsersId(userId, friend.getId());
        Map<String, String> data = new HashMap<String, String>();
        if (friend == null) {
            log.warn("不存在好友关系");
            data.put("msg", "已经存在好友关系");
            data.put("code", "400");
            return data;
        }
        friendService.deleteFriend(userId, friend.getId());
        log.info("清除好友关系");
        data.put("msg", "删除关系成功");
        data.put("code", "200");
        return data;
    }


}
