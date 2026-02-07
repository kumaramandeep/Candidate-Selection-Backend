package com.candidateselection.controller;

import com.candidateselection.model.User;
import com.candidateselection.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }
}
