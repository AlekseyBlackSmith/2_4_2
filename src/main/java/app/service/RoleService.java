package app.service;

import app.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void saveRole(Role role);
    public Role getRoleById(long id);
    public Role getRoleByName(String roleName);
}
