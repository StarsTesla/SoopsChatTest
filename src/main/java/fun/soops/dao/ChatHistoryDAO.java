package fun.soops.dao;

import fun.soops.entity.ChatHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryDAO {
    List<ChatHistory> getHistoryByFriendId(@Param("friendId") String friendId);

    void saveHistory(List<ChatHistory> history);
}
