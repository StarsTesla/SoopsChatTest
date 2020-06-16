package fun.soops.service;

import fun.soops.entity.ChatHistory;
import fun.soops.entity.User;
import fun.soops.entity.Message;

import java.util.List;

public interface ChatService {

    //通过两个用户 来获取之间所有聊天记录
    //这里查的是user1 -》 user2 的聊天记录
    List<Message> getHistoryBy2User(User user1, User user2);

    //保存最新的消息记录
    void saveHistory(List<Message> messages);
    //TODO 还差分页啥的
}
