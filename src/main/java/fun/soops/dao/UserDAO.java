package fun.soops.dao;


import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    //TODO

    User login(@Param("username") String username, @Param("password") String password);

    User getUserById(@Param("userId") String userId);

    User getUserByUsername(@Param("username") String username);


}
