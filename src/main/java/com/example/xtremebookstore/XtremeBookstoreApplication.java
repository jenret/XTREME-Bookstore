package com.example.xtremebookstore;

import com.example.xtremebookstore.data.BookDAL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XtremeBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtremeBookstoreApplication.class, args);
        BookDAL bDAL = new BookDAL();
        bDAL.findAll();

    }

}
