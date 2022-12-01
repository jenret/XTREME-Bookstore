package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.BookBLL;
import com.example.xtremebookstore.data.BookDAL;
import com.example.xtremebookstore.models.BookModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController { //add CRUD operations in here

    private BookBLL bBLL = new BookBLL();
    private BookDAL bDAL = new BookDAL();

    @PostMapping("/create")
    @ResponseBody
    public void createBook(@RequestBody BookModel object){
        bBLL.addBook(object);
    }
    @GetMapping("/findAll")
    public List<BookModel> findAll(){
        System.out.println("***FROM REST FIND ALL***");
        return bDAL.findAll();
    }
    @GetMapping("/{ISBN}")
    public void findById(@PathVariable String ISBN){
        bDAL.findById(ISBN);
    }


    @DeleteMapping("/{ISBN}")
    public void deleteBook(@PathVariable String ISBN){
        bBLL.deleteBook(ISBN);
    }
}
