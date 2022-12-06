package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.ReceiptDAL;
import com.example.xtremebookstore.data.UsersDAL;
import com.example.xtremebookstore.models.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/batch")
    public boolean createReceiptsBatch(@RequestBody ArrayList<ReceiptModel> receiptModels, @AuthenticationPrincipal User user) {
        try {
            UserModel userModel = UsersDAL.getUserByUsername(user.getUsername());
            for(ReceiptModel receiptModel : receiptModels) {
                receiptModel.setUserID(userModel.getId());
                receiptModel.setStoreID(userModel.getStoreID());
            }
            ReceiptDAL.createReceipts(receiptModels);
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

    /**
     * for both getSalesPerBook and getSalesPerStore
     * give them either a month as an int 1-12 or 0 for current month
     */

    @GetMapping("/books/{month}")
    public ArrayList<SalesPerBook> getSalesPerBook(@PathVariable int month) {
        try {
            if(month == 0) {
                return ReceiptDAL.getBookSalesPerMonth(LocalDateTime.now().getMonthValue());
            } else {
                return ReceiptDAL.getBookSalesPerMonth(month);
            }
        } catch (Exception e) {
            System.out.println("Error getSalesPerBook");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/stores/{month}")
    public ArrayList<SalesPerStore> getSalesPerStore(@PathVariable int month) {
        try {
            if(month == 0) {
                return ReceiptDAL.getStoreSalesPerMonth(LocalDateTime.now().getMonthValue());
            } else {
                return ReceiptDAL.getStoreSalesPerMonth(month);
            }
        } catch (Exception e) {
            System.out.println("Error getSalesPerStore");
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/authors/{month}")
    public ArrayList<SalesPerAuthor> getSalesPerAuthor(@PathVariable int month) {
        try {
            if(month == 0) {
                return ReceiptDAL.getAuthorSalesPerMonth(LocalDateTime.now().getMonthValue());
            } else {
                return ReceiptDAL.getAuthorSalesPerMonth(month);
            }
        } catch (Exception e) {
            System.out.println("Error getSalesPerAuthor");
            e.printStackTrace();
            return null;
        }
    }

}
