package fun.soops.service;

import fun.soops.entity.Friend;
import fun.soops.entity.User;

import java.util.List;

public interface FriendService {


    //TODO
    Friend getFriendById(String friendId);

    Friend getFriendBy2UsersId(String userId1, String userId2);

    Friend getFriendBy2Usersname(String username1, String username2);

    String insertFriend(String userId1, String userId2);

    String deleteFriend(String userId1, String userId2);

    List<User> getFriends(String userId);

}
