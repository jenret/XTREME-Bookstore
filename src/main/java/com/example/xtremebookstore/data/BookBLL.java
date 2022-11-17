package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.BookModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//BLLS should have stuff that manipulates data
public class BookBLL {
    String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    String user = "admin";
    String password = "PRO150db";
    BookDAL bDAL = new BookDAL();

    public void addBook(BookModel object) {
        List<BookModel> bookModel = bDAL.findAll();
        bookModel.add(object);

        String sql = "Insert into `XTreme-Bookstore`.books (ISBN, Title, Author, ) Values ()";
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
        } catch (Exception e){
            e.printStackTrace();
        }

    }



}
