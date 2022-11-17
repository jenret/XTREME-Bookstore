package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.BookModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBLL {

    String url = "xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com";
    String user = "admin";
    String password = "admin";

    public void addBook(BookModel bookModel) {
        List<BookModel> bookModelList = findAll();
    }

    public List<BookModel> findAll() {
        String sql = "Select * from  `XTreme-Bookstore`.books";
        List<BookModel> bookModel = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel books = new BookModel(rs.getInt("ISBN"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getDate("publishDate"),
                        rs.getString("Edition"),
                        rs.getDouble("purchasePrice"));
                bookModel.add(books);
                System.out.print(rs.getInt("ISBN") + " ");
                System.out.print(rs.getString("Title") + " ");
                System.out.print(rs.getString("Author") + " ");
                System.out.print(rs.getDate("publishDate") + " ");
                System.out.print(rs.getString("Edition") + " ");
                System.out.print(rs.getDouble("purchasePrice") + " ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }

}
