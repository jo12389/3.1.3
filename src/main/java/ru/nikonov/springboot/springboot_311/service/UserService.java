package ru.nikonov.springboot.springboot_311.service;


import ru.nikonov.springboot.springboot_311.model.User;

import java.util.List;


public interface UserService {
    public List<User> getAllUsers();

    User getUserForID(int id);

    public void createUser(User user);

    void updateUser(User user);

    public void deleteUser(int id);

    User getUserByLogin(String name);
}
