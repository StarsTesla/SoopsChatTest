package fun.soops.service.Impl;

import fun.soops.dao.ChatHistoryDAO;
import fun.soops.dao.FriendDAO;
import fun.soops.dao.UserDAO;
import fun.soops.entity.ChatHistory;
import fun.soops.entity.Friend;
import fun.soops.entity.User;
import fun.soops.service.ChatService;
import fun.soops.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    ChatHistoryDAO chatHistoryDAO;
    @Autowired
    FriendDAO friendDAO;

    public List<Message> getHistoryBy2User(User user1, User user2) {

        Friend friend = friendDAO.getFriendBy2UsersId(user1.getId(), user2.getId());
        List<ChatHistory> histyList = chatHistoryDAO.getHistoryByFriendId(friend.getId());

        List<Message> messages = new ArrayList<Message>();
        for (ChatHistory history : histyList) {
            messages.add(history.getMessage());
        }

        return messages;
    }

    public void saveHistory(List<Message> messages) {
        List<ChatHistory> histyList = new ArrayList<ChatHistory>();
        for (Message message : messages) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Friend friend = friendDAO.getFriendBy2UsersId(message.getFromUser(), message.getToUser());

            histyList.add(new ChatHistory(uuid, friend.getId(), message));
        }
        chatHistoryDAO.saveHistory(histyList);
    }


    /**
     * 做持久化聊天记录
     * 增删查
     * 包括文件和普通的文本
     */


    //TODO
}
