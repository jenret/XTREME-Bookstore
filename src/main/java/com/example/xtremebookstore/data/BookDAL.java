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
        String sql = "Select * from  `XTreme-Bookstore`.book_view";
        List<BookModel> bookModel = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel books = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getInt("AuthorID"),
                        rs.getString("authorName"),
                        rs.getString("datePublished"),
                        rs.getDouble("purchasePrice"));
                bookModel.add(books);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }

    public BookModel findById(String ISBN) {
        String sql = "Select * from `XTreme-Bookstore`.book_view where ISBN = (?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ISBN);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel found = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getInt("AuthorID"),
                        rs.getString("AuthorName"),
                        rs.getString("PublishDate"),
                        rs.getDouble("PurchasePrice"));
                return found;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
