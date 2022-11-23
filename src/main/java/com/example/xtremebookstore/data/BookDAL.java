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
                        rs.getInt("Author"),
                        rs.getDate("publishDate"),
                        rs.getInt("Edition"),
                        rs.getDouble("purchasePrice"));
                bookModel.add(books);
                System.out.print(rs.getString("ISBN") + " ");
                System.out.print(rs.getString("Title") + " ");
                System.out.print(rs.getString("Author") + " ");
                System.out.print(rs.getDate("publishDate") + " ");
                System.out.print(rs.getString("Edition") + " ");
                System.out.println(" " + rs.getDouble("purchasePrice") + " ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }

    public BookModel findById(String ISBN) {
        String sql = "Select * from `XTreme-Bookstore`.books where ISBN = (?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ISBN);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel found = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getInt("Author"),
                        rs.getDate("PublishDate"),
                        rs.getInt("Edition"),
                        rs.getDouble("PurchasePrice"));

                System.out.print(found.getISBN() + " ");
                System.out.print(found.getTitle() + " ");
                System.out.print(found.getAuthor() + " ");
                System.out.print(found.getPublishDate() + " ");
                System.out.print(found.getEdition() + " ");
                System.out.println(found.getPurchasePrice() + " ");
                return found;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
