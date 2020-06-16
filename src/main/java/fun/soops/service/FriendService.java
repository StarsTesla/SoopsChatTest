package fun.soops.service;

import fun.soops.entity.Friend;
import fun.soops.entity.User;

import java.util.List;

public interface FriendService {


    //TODO
    //通过Id获取好友关系
    Friend getFriendById(String friendId);

    //通过2个用户ID来获取好友关系
    Friend getFriendBy2UsersId(String userId1, String userId2);

    //通过2个用户名来获取好友关系
    Friend getFriendBy2Usersname(String username1, String username2);

    //插入好友关系
    String insertFriend(String userId1, String userId2);

    //解除好友关系
    String deleteFriend(String userId1, String userId2);

    //通过用户ID来获取其所有好友关系
    List<User> getFriends(String userId);

}
