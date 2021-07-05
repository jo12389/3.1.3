package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikonov.springboot.springboot_311.service.RoleServiceImpl;
import ru.nikonov.springboot.springboot_311.service.UserServiceImpl;

@RestController
public class RestUserController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public RestUserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

   /* @GetMapping("/admins/{id}")
    public String showUser(Model model, Authentication authentication,@PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserByLogin(authentication.getName()));
        return "user";
    }*/
}
