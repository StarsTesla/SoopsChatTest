package fun.soops.service.Impl;

import com.github.pagehelper.PageHelper;
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

/**
 * Author:Stars
 * Description:聊天
 */

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    ChatHistoryDAO chatHistoryDAO;
    @Autowired
    FriendDAO friendDAO;


    //通过两个用户 来获取之间所有聊天记录
    public List<Message> getHistoryBy2User(User user1, User user2) {

        Friend friend1 = friendDAO.getFriendBy2UsersId(user1.getId(), user2.getId());
        Friend friend2 = friendDAO.getFriendBy2UsersId(user2.getId(), user1.getId());
        // PageHelper.startPage(num,10);  //默认一次显示最多10条
        List<ChatHistory> histyList = chatHistoryDAO.getHistoryByFriendId(friend1.getId(), friend2.getId());

        List<Message> messages = new ArrayList<Message>();
        for (ChatHistory history : histyList) {
            messages.add(history.getMessage());
        }

        return messages;
    }

    //保存最新的消息记录
    public void saveHistory(List<Message> messages) {
        List<ChatHistory> histyList = new ArrayList<ChatHistory>();
        for (Message message : messages) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Friend friend = friendDAO.getFriendBy2Usersname(message.getFromUser(), message.getToUser());

            histyList.add(new ChatHistory(uuid, friend.getId(), message));
        }
        chatHistoryDAO.saveHistory(histyList);
    }


    /**
     * 做持久化聊天记录
     * 增删查
     * 包括文件和普通的文本
     */


    //TODO 还差分页啥的
}
