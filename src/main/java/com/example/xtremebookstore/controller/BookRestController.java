package com.example.xtremebookstore.controller;

import com.example.xtremebookstore.data.BookDAL;
import com.example.xtremebookstore.models.BookModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController { //add CRUD operations in here
    private BookDAL bDAL = new BookDAL();

    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN', 'EMP')")
    public void createBook(@RequestBody BookModel object){
        bDAL.addBook(object);
    }
    @GetMapping("/findAll")
    //@PreAuthorize("hasAnyRole('ADMIN','EMP')")
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
        bDAL.deleteBook(ISBN);
    }

    @GetMapping("/find/{title}")
    public List<BookModel> findByTitle(@PathVariable String title){
        return bDAL.findByTitle(title);
    }
}
