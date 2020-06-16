package fun.soops.entity;

import fun.soops.web.Message;

import java.util.Date;

public class ChatHistory {
    private String id;
    private String friendId;
    private Message message;

    public ChatHistory() {
    }

    ;

    public ChatHistory(String id, String friendId, Message message) {
        this.id = id;
        this.friendId = friendId;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
