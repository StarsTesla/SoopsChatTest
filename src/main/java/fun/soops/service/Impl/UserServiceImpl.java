package fun.soops.service.Impl;

import fun.soops.dao.UserDAO;
import fun.soops.entity.File;
import fun.soops.entity.User;
import fun.soops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    //TODO
//    @Override
//    public User insertUser(User user) {
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        User user1 = new User(uuid,"lb01","123",new Date());
//        userDAO.insertUser(user1);
//        return user;
//    }

    @Override
    public User insertUser(String username, String password, Date birth, File file) {
        //生成唯一id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //生成User对象user1
        User user1 = new User(uuid, username, password, new Date(), file);
        userDAO.insertUser(user1);
        return user1;
    }

    @Override
    public User getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    @Override
    public boolean isUserName(String userName) {
        Boolean flag = false;
        User user = userDAO.isUserName(userName);
        if (user != null) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }


}
