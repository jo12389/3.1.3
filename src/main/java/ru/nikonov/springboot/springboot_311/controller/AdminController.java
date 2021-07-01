package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikonov.springboot.springboot_311.model.User;
import ru.nikonov.springboot.springboot_311.service.UserService;


@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/admin")
    public String testList( Model model){

        model.addAttribute("userList",userService.getListFromService());
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

    @RequestMapping("/updateUser")
    public String update(@RequestParam("id") int id, @ModelAttribute("user") User user ){
      //  User updateUser = user;
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){

        userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }


}
