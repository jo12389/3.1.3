package ru.nikonov.springboot.springboot_311.dao;

import org.springframework.stereotype.Repository;
import ru.nikonov.springboot.springboot_311.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Set<Role> roleById(Integer[] role_id) {
        Set<Role> roleResult = new HashSet<>();
        for (int id : role_id) {
            TypedQuery<Role> q = entityManager.createQuery("select role from Role role where role.id = :id", Role.class);
            q.setParameter("id", id);
            Role result = q.getResultList().stream().filter(role -> role.getId() == id).findAny().orElse(null);
            roleResult.add(result);
        }
        return roleResult;
    }

    @Override
    @Transactional
    public List<Role> allRole() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }
}
