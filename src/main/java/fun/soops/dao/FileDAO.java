package fun.soops.dao;

import fun.soops.entity.File;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDAO {


    //TODO
    //定义添加avatar信息方法
    void addAvatar(File file);
}
