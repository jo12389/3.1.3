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
public class RestAdminController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public RestAdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value="/admins")
    public ResponseEntity<List<User>> showAllUser(){
        List <User> userList = userService.getListFromService();
      /*  return new ResponseEntity<>(userList, HttpStatus.OK);*/
        return ResponseEntity.ok().body(userService.getListFromService());
    }

    @GetMapping(value = "/admins/{id}")
    public ResponseEntity<User> read (@PathVariable int id){
        User user = userService.show(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/admins")
    public ResponseEntity<?> create(@RequestBody User user) {
       userService.save(user);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody User user) {
        userService.update(id,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
