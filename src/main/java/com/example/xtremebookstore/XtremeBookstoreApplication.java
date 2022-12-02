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

        //Date date = new Date(2022, 10,10);
        //BookModel object = new BookModel("1112223334445", "Delete Me", 1, date, 0, 69.69);
        //bBLL.addBook(object);
        //bBLL.deleteBook("1234512345123");
        //bDAL.findAll();
    }

}
