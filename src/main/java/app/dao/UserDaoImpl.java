package app.dao;

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
    public void update(User user) {
        entityManager.merge(user);
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
        return entityManager.createQuery("from User",User.class)
                .getResultList();
    }

}
