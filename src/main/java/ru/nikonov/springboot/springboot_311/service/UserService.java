package ru.nikonov.springboot.springboot_311.service;



import ru.nikonov.springboot.springboot_311.model.User;

import java.util.List;


public interface UserService {
    public List<User> getListFromService();
    User show(int id);
    public void save(User user);
    public void update(User updatedUser);
    public void delete(int id);
    User getUserByLogin(String name);
}
