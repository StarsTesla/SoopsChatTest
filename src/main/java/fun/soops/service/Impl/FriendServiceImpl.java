package fun.soops.service.Impl;

import fun.soops.dao.FriendDAO;
import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("friendService")
public class FriendServiceImpl implements FriendService {

    private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    @Autowired
    @Qualifier("friendDAO")
    private FriendDAO friendDAO;

    public Friend getFriendById(String friendId) {
        return friendDAO.getFriendById(friendId);
    }

    public Friend getFriendBy2UsersId(String userId1, String userId2) {
        return friendDAO.getFriendBy2UsersId(userId1, userId2);
    }

    public Friend getFriendBy2Usersname(String username1, String username2) {
        return friendDAO.getFriendBy2Usersname(username1, username2);
    }

    public String insertFriend(String userId1, String userId2) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        Friend newFriend = new Friend(uuid, userId1, userId2);
        friendDAO.insertFriend(newFriend);
        return uuid;
    }


    public String deleteFriend(String userId1, String userId2) {
        Friend friend = friendDAO.getFriendBy2UsersId(userId1, userId2);
        if (friend == null) {
            friend = friendDAO.getFriendBy2UsersId(userId2, userId1);
        }
        friendDAO.deleteFriend(friend);
        return friend.getId();
    }
//
//    public List<User> getFriends(String userId){
//        //TODO 需要User模块到支持
//
//        User user = userDAO.getUserById(userId);
//        List<Friend> friends = friendDAO.getFriends(user);
//        List<User> users = new ArrayList<User>();
//        for(Friend friend: friends)
//        {
//            if(friend.getUserId1().equals(user.getId()))
//            {
//                User user2 = userDAO.getUserById(friend.getUserId2());
//                users.add(user2);
//            }
//            else{
//                User user1 = userDAO.getUserById(friend.getUserId1());
//                users.add(user1);
//            }
//        }
//
//        return users;
//
//    }
//
//


    //TODO
}
