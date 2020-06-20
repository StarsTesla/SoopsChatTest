package fun.soops.dao;

import fun.soops.entity.ChatHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryDAO {
    List<ChatHistory> getHistoryByFriendId(@Param("friendId1") String friendId1, @Param("friendId2") String friendId2);

    void saveHistory(List<ChatHistory> history);
}
