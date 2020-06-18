package fun.soops.dao;

import fun.soops.entity.Friend;
import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDAO {
    Friend getFriendById(String friendId);

    Friend getFriendBy2UsersId(@Param("userId1") String userId1, @Param("userId2") String userId2);

    Friend getFriendBy2Usersname(@Param("username1") String username1, @Param("username2") String username2);

    void insertFriend(Friend friend);

    void deleteFriend(Friend friend);

    List<Friend> getFriends(User user);


}
