package fun.soops.service.Impl;

import fun.soops.dao.FriendDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl {


    @Autowired
    @Qualifier("friendDAO")
    private FriendDAO friendDAO;

    //TODO
}
