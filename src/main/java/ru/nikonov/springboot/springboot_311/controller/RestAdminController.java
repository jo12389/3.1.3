package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikonov.springboot.springboot_311.model.Role;
import ru.nikonov.springboot.springboot_311.model.User;
import ru.nikonov.springboot.springboot_311.service.RoleServiceImpl;
import ru.nikonov.springboot.springboot_311.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class RestAdminController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public RestAdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUser(){
        List <User> userList = userService.getListFromService();
      /*  return new ResponseEntity<>(userList, HttpStatus.OK);*/
        return ResponseEntity.ok().body(userService.getListFromService());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> read (@PathVariable int id){
        User user = userService.show(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.allRole();
        return ResponseEntity.ok().body(roles);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) {
       userService.save(user);
       return ResponseEntity.ok().body(user);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().body(id);
    }
}
