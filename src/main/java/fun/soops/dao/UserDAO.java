package fun.soops.dao;


import fun.soops.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    //TODO
    User getUserById(@Param("userId") String userId);

    User getUserByUsername(@Param("username") String username);

}
