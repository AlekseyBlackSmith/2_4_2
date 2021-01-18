package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeById(Long userId) {
        entityManager.createQuery("delete from User where userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void update(Long id, User user) {
        User userForUpdate = getById(id);
        userForUpdate.setUserName(user.getUserName());
        userForUpdate.setUserPassword(user.getUserPassword());
        userForUpdate.setRoles(user.getRoles());
    }

    @Override
    public User getById(Long userId) {
        return entityManager.createQuery("from User where userId = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public User getByName(String userName) {
        return entityManager.createQuery("from User where userName = :userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }


    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultList();
    }

}
