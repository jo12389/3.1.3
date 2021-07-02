package ru.nikonov.springboot.springboot_311.dao;



import ru.nikonov.springboot.springboot_311.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUserList();
    User show(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    User getUserByLogin(String name);
}
