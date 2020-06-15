package fun.soops.dao;

import fun.soops.entity.Friend;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendDAO {
    Friend getFriendById(String friendId);

    Friend getFriendBy2UsersId(String userId1, String userId2);

    Friend getFriendBy2Usersname(String userName1, String userName2);

}
