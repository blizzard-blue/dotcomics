package dao;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import db.db;
import models.UserAcct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jessica on 3/30/2016.
 */
public class UserDao {
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public boolean userExists(String emailaddress){
        boolean exists = false;
        rs = null;

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select emailaddress from User where emailaddress = ?");
            stmt.setString(1, emailaddress);
            rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("User exists in database");
                exists = true;

            }else{
                System.out.println("user does not exist in database");
                exists = false;
            }

            conn.close();
            rs.close();
            stmt.close();

        }catch(Exception e){
            System.out.println("connection unsuccessful");
            e.printStackTrace();
        }
        return exists;
    }

    public UserAcct getUser(String nickname){
        // placeholder code
        UserAcct user = new UserAcct();
        user.setUsername(nickname);
        return user;
    }

    public void createUser(String username){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        try {
            conn = db.getConnection();
            String scope = "https://www.googleapis.com/auth/userinfo.profile";


            stmt = conn.prepareStatement("insert into User(username, firstname, lastname, emailaddress) values (?, ?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, username);
            stmt.setString(3, username);
            stmt.setString(4, user.getEmail());
            stmt.executeUpdate();

            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean usernameExists(String username){
        boolean usernameExists = true;

        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from User where username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if(rs.next())
                usernameExists = true;
            else
                usernameExists = false;

            conn.close();
            stmt.close();
            rs.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return usernameExists;

    }



}
