package ru.nikonov.springboot.springboot_311.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikonov.springboot.springboot_311.dao.RoleDAO;
import ru.nikonov.springboot.springboot_311.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Override
    public Set<Role> roleById(Integer[] role_id) {
        return roleDAO.roleById(role_id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.allRole();
    }
}
