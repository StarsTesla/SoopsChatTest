package fun.soops.web;

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
    private Date createdOn;
    private String type;

    public Message(String content, String fromUser, String toUser, String type) {
        this.content = content;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.type = type;
        this.createdOn = new Date();
    }


}

