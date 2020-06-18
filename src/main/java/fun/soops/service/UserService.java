package fun.soops.service;

import fun.soops.entity.File;
import fun.soops.entity.User;

import java.util.Date;
import fun.soops.entity.User;

public interface UserService {
    User login(String username, String password);

    User getUserById(String userId);

    //TODO
    User insertUser(String username, String password, Date birth, File file);

    User getUserById(String userId);

    User getUserByUsername(String userName);

    boolean isUserName(String userName);

}
