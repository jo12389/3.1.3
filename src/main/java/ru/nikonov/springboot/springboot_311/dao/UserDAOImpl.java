package ru.nikonov.springboot.springboot_311.dao;

import org.springframework.stereotype.Repository;
import ru.nikonov.springboot.springboot_311.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUserList() {
        return entityManager.createQuery("select  user from  User  user", User.class).getResultList();
    }

    @Override
    @Transactional
    public User show(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void delete(int id) {

       entityManager.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();


      //  entityManager.remove(entityManager.find(User.class,id));

    }

    @Override
    public User getUserByLogin(String name) {

        return entityManager.createQuery("select user from User user where user.name = :name", User.class)
                .setParameter("name",name).getSingleResult();
    }
}
