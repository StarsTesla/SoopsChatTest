package fun.soops.entity;

/**
 * Author:Stars
 * Description:文件实体类
 */

public class File {
    private String id; //uuid
    private String realName; //123.jpg
    private String type; // jpg jpeg png 等

    //TODO

    public File() {
    }

    public File(String id, String realName, String type) {
        this.id = id;
        this.realName = realName;
        this.type = type;
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
