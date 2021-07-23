package ru.nikonov.springboot.springboot_311.dao;


import ru.nikonov.springboot.springboot_311.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUserForID(int id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getUserByLogin(String name);
}
