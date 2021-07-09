package ru.nikonov.springboot.springboot_311.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
@RestController
public class TEST {
    @GetMapping(value="/index")
    public ResponseEntity<?> Test(){
    return new ResponseEntity<>(HttpStatus.OK);

    }
}*/

@Controller
public class TEST {
    @RequestMapping("/index")
    public String Test(){
        return "index";

    }
}
