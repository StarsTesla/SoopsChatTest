package fun.soops.service.Impl;

import fun.soops.dao.FriendDAO;
import fun.soops.dao.UserDAO;
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

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

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

        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        Friend newFriend = new Friend(uuid1, userId1, userId2);
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        Friend newFriendFriend = new Friend(uuid2, userId2, userId1);
        friendDAO.insertFriend(newFriendFriend);
        return uuid1;
    }


    public String deleteFriend(String userId1, String userId2) {
        Friend friend = friendDAO.getFriendBy2UsersId(userId1, userId2);
        Friend friendFriend = friendDAO.getFriendBy2UsersId(userId2, userId1);
        friendDAO.deleteFriend(friend);
        friendDAO.deleteFriend(friendFriend);
        return friend.getId();
    }

    public List<User> getFriends(String userId) {
        //TODO 需要User模块到支持

        User user = userDAO.getUserById(userId);
        List<Friend> friends = friendDAO.getFriends(user);
        List<User> users = new ArrayList<User>();
        for (Friend friend : friends) {
            User mate = userDAO.getUserById(friend.getUserId2());
            users.add(mate);
        }

        return users;

    }


    //TODO
}
