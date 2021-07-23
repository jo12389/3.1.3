package ru.nikonov.springboot.springboot_311.service;

import ru.nikonov.springboot.springboot_311.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> roleById(Integer[] role_id);

    List<Role> getAllRoles();
}
