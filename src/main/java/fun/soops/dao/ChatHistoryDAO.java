package fun.soops.dao;

import fun.soops.entity.ChatHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatHistoryDAO {
    List<ChatHistory> getHistoryByFriendId(String friendId);

}
