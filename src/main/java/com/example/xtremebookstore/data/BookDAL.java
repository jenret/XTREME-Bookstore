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

    public void addBook(BookModel object) {
        List<BookModel> bookModel = findAll();
        bookModel.add(object);
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
            pst.executeUpdate();
        } catch (Exception e) {
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
                        rs.getString("datePublished"),
                        rs.getDouble("PurchasePrice"));
                return found;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BookModel> findByTitle(String title){
        String sql = "SELECT * FROM `XTreme-Bookstore`.book_view WHERE title like (?)";
        List<BookModel> bookModel = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + title + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel found = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getInt("AuthorID"),
                        rs.getString("AuthorName"),
                        rs.getString("datePublished"),
                        rs.getDouble("PurchasePrice"));
                 bookModel.add(found);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }

    public List<BookModel> findByAuthor(String author){
        String sql = "SELECT * FROM `XTreme-Bookstore`.book_view WHERE authorName like (?)";
        List<BookModel> bookModel = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + author + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookModel found = new BookModel(rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getInt("AuthorID"),
                        rs.getString("AuthorName"),
                        rs.getString("datePublished"),
                        rs.getDouble("PurchasePrice"));
                bookModel.add(found);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookModel;
    }
}
