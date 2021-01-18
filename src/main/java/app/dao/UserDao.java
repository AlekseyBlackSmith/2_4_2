package app.dao;

import app.model.Role;
import app.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void removeById(Long id);
    void update(Long id, User user);
    User getById(Long id);
    User getByName(String name);
    List<User> listUsers();
    List<Role> listRoles();

}
