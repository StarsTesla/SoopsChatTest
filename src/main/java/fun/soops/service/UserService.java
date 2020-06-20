package fun.soops.service;

import fun.soops.entity.File;
import fun.soops.entity.User;

import java.util.Date;
import java.util.List;

import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User login(String username, String password);

    //TODO
    User insertUser(String username, String password, Date birth, File file);

    User getUserById(String userId);

    User getUserByUsername(String userName);

    boolean isUserName(String userName);

    List<User> searchUserByUsername(@Param("username") String username);
}
