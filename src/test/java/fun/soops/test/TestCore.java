package fun.soops.test;

/*
 *核心模块测试类
 */

import fun.soops.entity.File;
import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.FriendService;
import fun.soops.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.net.www.content.image.png;

import java.awt.*;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-websocket.xml")
public class TestCore {

    @Autowired
    @Qualifier("friendService")
    FriendService friendService;
    UserService userService;
    //TODO
    private final Logger log = LoggerFactory.getLogger(TestCore.class);


    @Test
    public void test() {
        System.out.println("hello");
    }

    @Test
    public void queryFriend() {
        String id = "001";
        // Friend friend = friendService.getFriendById(id);
        //Friend friend = friendService.getFriendBy2UsersId("001","002");
        Friend friend = friendService.getFriendBy2Usersname("Stars", "Lb");
        System.out.println(friend);
    }

    @Test
    public void update() {

        File file = new File();
        User user = new User("111","lb","7758258",new Date(),file);
        userService.update(user);
    }
    @Test
    public void insertFriend() {
        String id1 = "003";
        String id2 = "004";
        friendService.insertFriend(id1, id2);
    }

    @Test
    public void deleteFriend() {
        String friendid = "001";
        friendService.deleteFriend("001", "002");
    }


}
