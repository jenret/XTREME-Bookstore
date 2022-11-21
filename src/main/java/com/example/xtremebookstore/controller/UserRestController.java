package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.UsersDAL;
import com.example.xtremebookstore.models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @PostMapping("")
    public boolean createUser(@RequestBody UserModel userModel) {
        try {
            UsersDAL.createUser(userModel);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR createUser");
            e.printStackTrace();
            return false;
        }
    }

}
