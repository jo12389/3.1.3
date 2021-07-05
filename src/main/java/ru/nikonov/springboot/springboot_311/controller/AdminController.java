package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikonov.springboot.springboot_311.model.Role;
import ru.nikonov.springboot.springboot_311.model.User;
import ru.nikonov.springboot.springboot_311.service.RoleServiceImpl;
import ru.nikonov.springboot.springboot_311.service.UserServiceImpl;
import java.util.List;


@Controller
public class AdminController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/admin")
    public String ListUser(Model model, Authentication authentication){
        User user = userService.getUserByLogin(authentication.getName());
        model.addAttribute("currentuser", user);
        model.addAttribute("userList",userService.getListFromService());
        List<Role> roles = roleService.allRole();
        model.addAttribute("roles", roles);
        //новый юзер
        User newuser = new User();
        model.addAttribute("newuser", newuser);
        return "admin";
    }



    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("newuser") User user,
                           @RequestParam(required = false, name = "role_id") Integer[] role_id) {
               user.setRoles(roleService.roleById(role_id));
        userService.save(user);
        return "redirect:/admin";
    }


    @RequestMapping("/updateUser")
    public String update(@ModelAttribute("user") User user,@RequestParam("id") int id, @RequestParam(required = false, name = "role_id") Integer[] role_id ){
        user.setRoles(roleService.roleById(role_id));
        userService.update(id, user);
        return "redirect:/admin";
    }



    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }


}
