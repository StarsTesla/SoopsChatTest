package fun.soops.test;

/*
 *核心模块测试类
 */

import fun.soops.entity.File;
import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.FileService;
import fun.soops.service.ChatService;
import fun.soops.service.FriendService;
import fun.soops.entity.Message;
import fun.soops.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-websocket.xml")
public class TestCore {

    @Autowired
    @Qualifier("friendService")
    FriendService friendService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Autowired
    @Qualifier("fileService")
    FileService fileService;

    @Autowired
    ChatService chatService;

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

//    @Test
//    public void insertFriend() {
//        String id1 = "003";
//        String id2 = "004";
//        friendService.insertFriend(id1, id2);
//    }
//
//    @Test
//    public void deleteFriend() {
//        String friendid = "001";
//        friendService.deleteFriend("001", "002");
//    }

    @Test
    public void insertUser() {
        File file = new File("001", "01.jpg", "jpg");
        userService.insertUser("lbb", "456", new Date(), file);
        System.out.println(file);
    }

    //    @Test
    //    public void getUserId(){
//        User user = userService.getUserById("001");
//        System.out.println(user);
//    }
    @Test
    public void getUserByName() {
        User user = userService.getUserByName("lb");
        System.out.println(user);
        if (user.getUsername().equals("sad")) {
            System.out.println("已存在");
        } else {
            System.out.println("还未存在");
        }
    }

    @Test
    public void getUserId() {
        User user = userService.getUserById("c72e20849b6b412eb52319fdb9e1639f");
        System.out.println(user);

        System.out.println(user.getAvatar());
    }

//    @Test
//    public void addFile(){
//        fileService.addAvatar("002.jpg");
//        System.out.println("ok");
//    }

    @Test
    public void insertMessage() {
        List<Message> messages = new ArrayList<Message>();

        messages.add(new Message("11111", "001", "003", "text", new Date()));
        messages.add(new Message("22222", "003", "004", "text", new Date()));
        messages.add(new Message("33333", "003", "004", "text", new Date()));
        chatService.saveHistory(messages);
    }

    @Test
    public void readMessage() {
        User user1 = userService.getUserById("003");
        User user2 = userService.getUserById("004");
        List<Message> messages = chatService.getHistoryBy2User(user1, user2);
        for (Message message : messages) {
            System.out.println(message);
        }
    }


}
