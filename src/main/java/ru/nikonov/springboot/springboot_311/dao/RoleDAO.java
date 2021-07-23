package ru.nikonov.springboot.springboot_311.dao;

import ru.nikonov.springboot.springboot_311.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    Set<Role> roleById(Integer[] role_id);

    List<Role> allRole();
}
