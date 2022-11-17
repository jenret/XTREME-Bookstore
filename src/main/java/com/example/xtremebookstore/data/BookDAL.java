package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.BookModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAL {
    String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    String user = "admin";
    String password = "PRO150db";

    public List<BookModel> findAll() {
        String sql = "Select * from  `XTreme-Bookstore`.books";
        List<BookModel> bookModel = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel books = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getDate("publishDate"),
                        rs.getString("Edition"),
                        rs.getDouble("purchasePrice"));
                bookModel.add(books);
                System.out.print(rs.getString("ISBN") + " ");
                System.out.print(rs.getString("Title") + " ");
                System.out.print(rs.getString("Author") + " ");
                System.out.print(rs.getDate("publishDate") + " ");
                System.out.print(rs.getString("Edition") + " ");
                System.out.println(" " +rs.getDouble("purchasePrice") + " ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }
}
