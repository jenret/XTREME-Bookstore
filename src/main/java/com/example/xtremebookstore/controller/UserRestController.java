package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.UsersDAL;
import com.example.xtremebookstore.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("")
    public boolean createUser(@RequestBody UserModel userModel) {
        try {
            UsersDAL.createUser(userModel);
            inMemoryUserDetailsManager.createUser(User.withUsername(userModel.getUsername()).password(passwordEncoder.encode(userModel.getPassword())).roles(userModel.getRole()).build());
            return true;
        } catch (Exception e) {
            System.out.println("ERROR createUser");
            e.printStackTrace();
            return false;
        }
    }

}
