package fun.soops.entity;

import java.util.Date;

public class ChatHistory {
    private String id;
    private String friendId;
    private String content;
    private String type;
    private Date createdOn;

    public ChatHistory(String id, String friendId, String content, String type, Date createdOn) {
        this.id = id;
        this.friendId = friendId;
        this.content = content;
        this.type = type;
        this.createdOn = createdOn;
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

    public void setFriednId(String friendId) {
        this.friendId = friendId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
