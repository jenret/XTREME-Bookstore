package com.example.xtremebookstore;

import com.example.xtremebookstore.data.BookBLL;
import com.example.xtremebookstore.data.BookDAL;
import com.example.xtremebookstore.models.BookModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Date;

@SpringBootApplication
public class XtremeBookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtremeBookstoreApplication.class, args);
        BookBLL bBLL= new BookBLL();
        BookDAL bDAL = new BookDAL();

        Date date = new Date(2022, 10,10);
        //BookModel object = new BookModel("1234512345123", "Delete Me", 1, date, 0, 69.69);
        //bBLL.addBook(object);
        //bBLL.deleteBook("1234512345123");
        //bDAL.findAll();
    }

}
