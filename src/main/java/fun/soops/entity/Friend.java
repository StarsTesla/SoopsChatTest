package fun.soops.entity;

/**
 * Author:Stars
 * Description:朋友实体类
 */

public class Friend {
    private String id;
    private String userId1;
    private String userId2;

    public Friend(String id, String userId1, String userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", userId1='" + userId1 + '\'' +
                ", userId2='" + userId2 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }
}
