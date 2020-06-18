package fun.soops.entity;

/**
 * Author:Stars
 * Description:聊天记录实体类
 */
public class ChatHistory {
    private String id;
    private String friendId;
    private Message message;

    public ChatHistory() {
    }

    public ChatHistory(String id, String friendId, Message message) {
        this.id = id;
        this.friendId = friendId;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatHistory{" +
                "id='" + id + '\'' +
                ", friendId='" + friendId + '\'' +
                ", message=" + message +
                '}';
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
