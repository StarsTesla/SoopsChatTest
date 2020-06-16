package fun.soops.service;


import fun.soops.entity.User;

public interface UserService {
    User login(String username, String password);
    User getId(Integer id);
    //TODO
}
