package fun.soops.dao;


import fun.soops.entity.File;
import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDAO {
    //TODO
    //定义insertUser方法，用于插入User信息
    void insertUser(User user);
    //修改个人信息
    void update(@Param("id")String id ,@Param("username")String username , @Param("password")String password ,@Param("birth") Date birth , @Param("avatar")File avatar);

    User isUserName(String userName);
    //通过名字查id
    String getIdByUsername(@Param("username")String username);

    User login(@Param("username") String username, @Param("password") String password);

    User getUserById(@Param("userId") String userId);

    User getUserByUsername(@Param("username") String username);


}
