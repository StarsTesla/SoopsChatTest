package fun.soops.entity;

/**
 * Author:Stars
 * Description:文件实体类
 */

public class File {
    private String id; //uuid
    private String realName; //123.jpg  //TODO 已经改为文件路径，请进行更改
    private String type; // jpg jpeg png 等

    //TODO
    private User user;

    public File() {
    }

    public File(String id, String realName, String type) {
        this.id = id;
        this.realName = realName;
        this.type = type;
    }


    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", realName='" + realName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
