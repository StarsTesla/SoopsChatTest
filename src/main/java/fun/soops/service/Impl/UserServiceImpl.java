package fun.soops.service.Impl;

import fun.soops.dao.UserDAO;
import fun.soops.entity.User;
import fun.soops.service.UserService;
import fun.soops.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    public User login(String username, String password) {
        return userDAO.login(username,password);
    }

    public User getId(Integer id) {
        return  userDAO.getId(id);
    }


    //TODO
}
