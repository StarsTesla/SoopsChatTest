package fun.soops.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Message 是用来做临时化存储的
 * 当用户关闭聊天窗口或者查看聊天记录时或者积累到一定量（20条）
 * 就将其存到ChatHistory里去
 * 要是我会redis就好了
 */

public class Message {
    private String content;
    private String fromUser;
    private String toUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdOn;

    private String type;

    public Message() {
    }

    ;

    public Message(String content, String fromUser, String toUser, String type, Date createdOn) {
        this.content = content;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.type = type;
        this.createdOn = createdOn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

