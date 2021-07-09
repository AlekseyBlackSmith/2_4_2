package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void removeUserById(Long id);
    void updateUser(User user);
    User getUserById(Long id);
    User getUserByNameWithRoles(String name);
    List<User> getAllUsers();
    User getUserByIdWithRoles(Long id);
}
