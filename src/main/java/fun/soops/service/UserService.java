package fun.soops.service;


import fun.soops.entity.File;
import fun.soops.entity.User;

import java.util.Date;

public interface UserService {


    //TODO
    User insertUser(String username, String password, Date birth, File file);

    User getUserById(String userId);

    User getUserByName(String userName);

    boolean isUserName(String userName);

}
