package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.UserModel;
import java.sql.*;
import java.util.ArrayList;

public class UsersDAL {
    //CRUD
    private static String url = "jdbc:mysql://xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com/XTreme-Bookstore?allowPublicKeyRetrieval=true&useSSL=false";
    private static String user = "admin";
    private static String password = "PRO150db";
    //USERS Table
    public static void createUser(UserModel userM){
        String sql = "INSERT INTO users (username, password, role, storeID) values ((?),(?),(?),(select stores.storeID from stores where name like (?)))";
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,userM.getUsername());
            pst.setString(2,userM.getPassword());
            pst.setString(3,userM.getRole());
            pst.setString(4,userM.getFkStoreName());
            pst.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<UserModel> getAllUsers(){
        String sql = "SELECT * FROM user_view";
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet users = pst.executeQuery();
            ArrayList<UserModel> userModels = new ArrayList<>();
            while(users.next()){
                UserModel curUser = new UserModel(
                        users.getInt(1),
                        users.getString(2),
                        users.getString(3),
                        users.getString(4),
                        users.getInt(5),
                        users.getString(6));
                userModels.add(curUser);
            }
            return userModels;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static UserModel getUserByUsername(String username) {
        String query = "select * from user_view where username = (?)";
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            result.next();
            UserModel userModel = new UserModel(
                    result.getInt("userID"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("role"),
                    result.getInt("storeID"),
                    result.getString("storeName"));
            return userModel;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
