package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikonov.springboot.springboot_311.model.Role;
import ru.nikonov.springboot.springboot_311.model.User;
import ru.nikonov.springboot.springboot_311.service.RoleService;
import ru.nikonov.springboot.springboot_311.service.RoleServiceImpl;
import ru.nikonov.springboot.springboot_311.service.UserService;
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

    public AdminController() {
    }
    @GetMapping("/admin")
    public String testList(Model model, Authentication authentication){
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
    @RequestMapping("/addNewUser")
    public String addNewUser(Model model){
    User user = new User();
    model.addAttribute("user",user);
    return "test-user-info";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("id")int id, Model model){
        User user = userService.show(id);
        model.addAttribute("user",user);
        return "test-user-edit";
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
    public String deleteUser(@ModelAttribute("user") User user, @RequestParam("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }


}
