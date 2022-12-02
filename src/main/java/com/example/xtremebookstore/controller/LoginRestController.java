package com.example.xtremebookstore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    @GetMapping("")
    public String loggedIn(@AuthenticationPrincipal User user) {
        return user.getAuthorities().toString();
    }

}
