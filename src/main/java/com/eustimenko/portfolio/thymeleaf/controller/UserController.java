package com.eustimenko.portfolio.thymeleaf.controller;

import com.eustimenko.portfolio.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute(ModelParameters.USERS, service.list());

        return "index";
    }

    @GetMapping("/get")
    public String users(@RequestParam(value = "name", required = false, defaultValue = "") String name, Model model) {
        if (name.isEmpty()) {
            model.addAttribute(ModelParameters.USERS, service.list());
        } else {
            model.addAttribute(ModelParameters.USERS, service.list(name));
        }

        return "index :: users";
    }

    @GetMapping("/new")
    public String createUser(@RequestParam(value = "name") String name, Model model) {
        service.addUser(name);
        model.addAttribute(ModelParameters.USERS, service.list());

        return "index :: users";
    }
}