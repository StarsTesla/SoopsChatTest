package fun.soops.web;

import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    @Autowired
    @Qualifier("friendService")
    private FriendService friendService;
    private Logger log = LoggerFactory.getLogger(FriendController.class);

    //TODO 需要用户模块支持
//    @GetMapping("/getFriends")
//    public List<User> getFriends(String userId)
//    {
//        log.info("查询所有好友 ");
//        List<User> friends = friendService.getFriends(userId);
//        return friends;
//    }


    @PostMapping(value = "/addFriend")
    public String addFriend(String userId1, String userId2) {
        Friend friend1 = friendService.getFriendBy2UsersId(userId1, userId2);
        Friend friend2 = friendService.getFriendBy2UsersId(userId2, userId1);

        if (friend1 != null || friend2 != null) {
            log.warn("已经存在好友关系");
            return "{msg:已经存在好友关系,code:400}";
        }

        String friendId = friendService.insertFriend(userId1, userId2);
        log.info("insert friend of " + userId1 + " and " + userId2);

        return "{msg:添加成功,code:200,friendId:" + friendId + "}";

    }

    @PostMapping("/deleteFriend")
    public String deleteFriend(String userId1, String userId2) {
        Friend friend1 = friendService.getFriendBy2UsersId(userId1, userId2);
        Friend friend2 = friendService.getFriendBy2UsersId(userId2, userId1);

        if (friend1 == null && friend2 == null) {
            log.warn("不存在好友关系");
            return "{msg:不存在好友关系,code:400}";
        }
        String friendId = friendService.deleteFriend(userId1, userId2);
        return "{msg:删除成功,code:200,friendId:" + friendId + "}";

    }


}
