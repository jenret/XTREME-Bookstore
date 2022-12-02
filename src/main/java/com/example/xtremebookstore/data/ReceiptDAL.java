package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.ReceiptModel;
import com.example.xtremebookstore.models.SalesPerBook;
import com.example.xtremebookstore.models.SalesPerStore;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ReceiptDAL {

    private static final String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "admin";
    private static final String password = "PRO150db";

    public static void createReceipt(ReceiptModel receiptModel) {
        String insert = "insert into sales (bookID, userID, storeID, salePrice) values (?, ?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(insert);
            pst.setString(1, receiptModel.getBookISBN());
            pst.setInt(2, receiptModel.getUserID());
            pst.setInt(3, receiptModel.getStoreID());
            pst.setDouble(4, receiptModel.getSalePrice());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ReceiptModel> getAllReceipts(){
        String sql = "SELECT * FROM receipts_view";
        ArrayList<ReceiptModel> receipts = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                receipts.add(new ReceiptModel(rs.getInt(1)
                        ,rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getTimestamp(9).toLocalDateTime()));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return receipts;
    }
    //Get Receipts by month
    public static ArrayList<ReceiptModel> getReceiptsFromCurrentMonth(){
        String sql = "SELECT * FROM `XTreme-Bookstore`.receipts_view\n" +
                "WHERE monthname(timeOfSale) LIKE monthname(current_timestamp) " +
                "and year(timeOfSale) = year(current_timestamp())\n" +
                "GROUP BY extract(YEAR from timeOfSale), extract(MONTH from timeOfSale)\n" +
                "order by timeOfSale";
        ArrayList<ReceiptModel> receipts = new ArrayList<>();
        try{
            Connection con  = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                receipts.add(new ReceiptModel(rs.getInt(1)
                        ,rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getTimestamp(9).toLocalDateTime()));
            }
        }catch (SQLException sqle){sqle.printStackTrace();}
        return receipts;
    }
    public static ArrayList<ReceiptModel> getReceiptsFromAMonth(String month){
        String sql = "SELECT * FROM `XTreme-Bookstore`.receipts_view\n" +
                "WHERE monthname(timeOfSale) LIKE ? " +
                "and year(timeOfSale) = year(current_timestamp())\n" +
                "GROUP BY extract(YEAR from timeOfSale), extract(MONTH from timeOfSale)\n" +
                "order by timeOfSale";
        ArrayList<ReceiptModel> receipts = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,"%"+month+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                receipts.add(new ReceiptModel(rs.getInt(1)
                        ,rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getTimestamp(9).toLocalDateTime()));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return receipts;
    }
    public void getTotalSalesByBook(){

    }

    public static ArrayList<SalesPerBook> getBookSalesPerMonth(int month) {
        String query = "select title, count(salesID) as numberOfSales, sum(salePrice) as salesTotal " +
                "from receipts_view where month(timeOfSale) = ? and year(timeOfSale) = year(now()) group by title";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, month);
            ResultSet rs = pst.executeQuery();
            ArrayList<SalesPerBook> results = new ArrayList<>();
            while (rs.next()) {
                results.add(new SalesPerBook(rs.getString("title"),
                        rs.getDouble("salesTotal"),
                        rs.getInt("numberOfSales")));
            }
            return results;
        } catch (Exception e) {
            System.out.println("Error getBookSalesPerMonth");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<SalesPerStore> getStoreSalesPerMonth(int month) {
        String query = "select storeName, sum(salePrice) as salesTotal, count(salesID) as numberOfSales " +
                "from receipts_view month(timeOfSale) = ? and year(timeOfSale) = year(now()) group by title";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, month);
            ResultSet rs = pst.executeQuery();
            ArrayList<SalesPerStore> results = new ArrayList<>();
            while (rs.next()) {
                results.add(new SalesPerStore(rs.getString("storeName"),
                        rs.getDouble("salesTotal"),
                        rs.getInt("numberOfSales")));
            }
            return results;
        } catch (Exception e) {
            System.out.println("Error getStoreSalesPerMonth");
            e.printStackTrace();
            return null;
        }
    }

}
