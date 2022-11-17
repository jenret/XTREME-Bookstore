package com.example.xtremebookstore.data;

import com.example.xtremebookstore.models.UserModel;

import java.sql.*;
import java.util.ArrayList;

public class MySQLDAL {
    //CRUD
    private String url = "xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com";
    private String user = "admin";
    private String password = "PRO150db";
    //USERS Table
    public void createUser(UserModel userM){
        String sql = "INSERT INTO users (username, password, role, storeID) values ((?),(?),(?),(?))";
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,userM.getUsername());
            pst.setString(2,userM.getPassword());
            pst.setString(3,userM.getRole());
            pst.setInt(4,userM.getStoreID());
            pst.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<UserModel> getAllUsers(){
        String sql = "SELECT * FROM users";
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet users = pst.executeQuery();
            ArrayList<UserModel> userModels = new ArrayList<>();
            while(users.next()){
                UserModel curUser = new UserModel(users.getInt(1),
                        users.getString(2),
                        users.getString(3),
                        users.getString(4),
                        users.getInt(5));
                userModels.add(curUser);
            }
            return userModels;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
