package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.BookModel;

import java.sql.*;
import java.util.List;

//BLLS should have stuff that manipulates data
public class BookBLL {
    String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    String user = "admin";
    String password = "PRO150db";
    BookDAL bDAL = new BookDAL();

    //add words
    public void addBook(BookModel object) {
        System.out.println("SALLS");
        List<BookModel> bookModel = bDAL.findAll();
        bookModel.add(object);
        System.out.println("BALLS");
        String sql = "Insert into `XTreme-Bookstore`.books (ISBN, Title, Author, Edition, publishDate, purchasePrice) Values (?,?,?,?,?,?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, object.getISBN());
            pst.setString(2, object.getTitle());
            pst.setInt(3, object.getAuthor());
            pst.setInt(4, object.getEdition());
            pst.setString(5, object.getPublishDate());
            pst.setDouble(6, object.getPurchasePrice());
            System.out.println("HALLS");
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("THRALLS");
            e.printStackTrace();
        }
    }

    //delete works
    public void deleteBook(String ISBN) {
        String sql = "Delete from `XTreme-Bookstore`.books where ISBN = (?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ISBN);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
