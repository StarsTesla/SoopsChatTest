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

/**
 * Author:Stars
 * Description:好友业务，增删查
 * 好友关系是双向的，因此数据库里AtoB和BtoA的关系都存了
 * 这样操作起来，以及逻辑更简单，只是增删要操作两次
 */

@Service("friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    @Qualifier("friendDAO")
    private FriendDAO friendDAO;

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    //通过Id获取好友关系
    public Friend getFriendById(String friendId) {
        return friendDAO.getFriendById(friendId);
    }

    //通过2个用户ID来获取好友关系
    public Friend getFriendBy2UsersId(String userId1, String userId2) {
        return friendDAO.getFriendBy2UsersId(userId1, userId2);
    }

    //通过2个用户名来获取好友关系
    public Friend getFriendBy2Usersname(String username1, String username2) {
        return friendDAO.getFriendBy2Usersname(username1, username2);
    }

    //插入好友关系
    public String insertFriend(String userId1, String userId2) {

        //使用UUID作为唯一标识符
        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        Friend newFriend = new Friend(uuid1, userId1, userId2);
        friendDAO.insertFriend(newFriend);
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        Friend newFriendFriend = new Friend(uuid2, userId2, userId1);
        friendDAO.insertFriend(newFriendFriend);
        return uuid1;
    }

    //解除好友关系
    public String deleteFriend(String userId1, String userId2) {
        Friend friend = friendDAO.getFriendBy2UsersId(userId1, userId2);
        Friend friendFriend = friendDAO.getFriendBy2UsersId(userId2, userId1);
        friendDAO.deleteFriend(friend);
        friendDAO.deleteFriend(friendFriend);
        return friend.getId();
    }

    //通过用户ID来获取其所有好友关系
    public List<User> getFriends(String userId) {

        User user = userDAO.getUserById(userId);
        List<Friend> friends = friendDAO.getFriends(user);
        List<User> users = new ArrayList<User>();
        for (Friend friend : friends) {
            User mate = userDAO.getUserById(friend.getUserId2());
            users.add(mate);
        }

        return users;

    }

}
