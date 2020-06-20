package fun.soops.dao;


import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDAO {
    //TODO
    //定义insertUser方法，用于插入User信息
    void insertUser(User user);

    User isUserName(String userName);

    User login(@Param("username") String username, @Param("password") String password);

    User getUserById(@Param("userId") String userId);

    User getUserByUsername(@Param("username") String username);

    List<User> searchUserByUsername(@Param("username") String username);

}
