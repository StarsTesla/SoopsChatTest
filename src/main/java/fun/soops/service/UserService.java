package fun.soops.service;


import fun.soops.entity.User;

public interface UserService {

    User getUserById(String userId);

    User getUserByUsername(String username);
    //TODO
}
