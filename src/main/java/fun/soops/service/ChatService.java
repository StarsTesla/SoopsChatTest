package fun.soops.service;

import fun.soops.entity.ChatHistory;
import fun.soops.entity.User;
import fun.soops.entity.Message;

import java.util.List;

public interface ChatService {

    List<Message> getHistoryBy2User(User user1, User user2);

    void saveHistory(List<Message> messages);
    //TODO
}
