package fun.soops.dao;


import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDAO {
    //TODO
    //定义insertUser方法，用于插入User信息
    void insertUser(User user);

    //定以查询方法
    User getUserById(String userId);

    User getUserByName(String userName);

    User isUserName(String userName);
}
