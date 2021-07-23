package ru.nikonov.springboot.springboot_311.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikonov.springboot.springboot_311.dao.UserDAO;
import ru.nikonov.springboot.springboot_311.model.User;


import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserForID(int id) {
        return userDao.getUserForID(id);
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserByLogin(String name) {
        return userDao.getUserByLogin(name);
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.getUserByLogin(name);
    }


}
