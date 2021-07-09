package app.dao;

import app.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    void saveRole(Role role);
    Role getRoleById(long id);
    Role getRoleByName(String roleName);
}
