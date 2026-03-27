package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.entity.User;
import com.bugtracker.bugtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST API
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
    return userService.login(email, password);
}
}