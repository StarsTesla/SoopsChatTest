package fun.soops.service.Impl;

import fun.soops.dao.UserDAO;
import fun.soops.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;


    //TODO
}
