package fun.soops.service;


import fun.soops.entity.User;


public interface UserService {
    User login(String username, String password);

    User getUserById(String userId);

    User getUserByUsername(String username);

    //TODO
}
