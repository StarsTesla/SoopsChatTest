package fun.soops.entity;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private Date birth;
    private File avatar;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", avatar=" + avatar +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }
//TODO
}
