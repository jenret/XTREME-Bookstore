package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.ReceiptDAL;
import com.example.xtremebookstore.data.UsersDAL;
import com.example.xtremebookstore.models.ReceiptModel;
import com.example.xtremebookstore.models.UserModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptRestController {

    /**
     * this method only needs the bookID/ISBN and salePrice to write to the DB
     */
    @PostMapping("")
    public boolean createReceipt(@RequestBody ReceiptModel receiptModel, @AuthenticationPrincipal User user) {
        try {
            UserModel userModel = UsersDAL.getUserByUsername(user.getUsername());
            receiptModel.setUserID(userModel.getId());
            receiptModel.setStoreID(userModel.getStoreID());
            ReceiptDAL.createReceipt(receiptModel);
            return true;
        } catch (Exception e) {
            System.out.println("Error createReceipt");
            e.printStackTrace();
            return false;
        }
    }

}