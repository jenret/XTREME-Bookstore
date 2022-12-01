package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.ReceiptDAL;
import com.example.xtremebookstore.data.UsersDAL;
import com.example.xtremebookstore.models.ReceiptModel;
import com.example.xtremebookstore.models.UserModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("")
    public List<ReceiptModel> findAllReceipt() {
        try {
            return ReceiptDAL.getAllReceipts();
        } catch (Exception e) {
            System.out.println("Error findAllUsers");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/currentMonth")
    public ArrayList<ReceiptModel> getCurrentMonthData() {
        try {
            return ReceiptDAL.getReceiptsFromCurrentMonth();
        } catch (Exception e) {
            System.out.println("Error getCurrentMontData");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{month}")
    public ArrayList<ReceiptModel> getSelectedMonthData(@PathVariable String month) {
        try {
            return ReceiptDAL.getReceiptsFromAMonth(month);
        } catch (Exception e) {
            System.out.println("Error getSelectedMonthData");
            e.printStackTrace();
            return null;
        }
    }

}
