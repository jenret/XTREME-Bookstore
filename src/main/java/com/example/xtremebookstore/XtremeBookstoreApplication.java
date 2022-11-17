package com.example.xtremebookstore;

import com.example.xtremebookstore.data.BookBLL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XtremeBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtremeBookstoreApplication.class, args);
        BookBLL bBLL= new BookBLL();
        bBLL.findAll();
    }

}
