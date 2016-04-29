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

    public UserAcct getUser(String email){
        rs = null;
        UserAcct user = new UserAcct();
        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from User where emailaddress = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if(rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setAbout(rs.getString("aboutme"));
                user.setUserid(rs.getString("userid"));
                user.setProfileImg(rs.getString("profileimg"));
            }
            conn.close();
            rs.close();
            stmt.close();

        }catch(Exception e){
            System.out.println("connection unsuccessful");
            e.printStackTrace();
        }
        return user;
    }

    public void createUser(String username){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        try {
            conn = db.getConnection();

            stmt = conn.prepareStatement("insert into User(username, emailaddress) values (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();

            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void createUser(String username, String descr){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        try {
            conn = db.getConnection();

            stmt = conn.prepareStatement("insert into User(username, emailaddress, aboutme) values (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, user.getEmail());
            stmt.setString(3, descr);
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

    public void updateBio(String bio){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("update User set aboutme = ? where emailaddress = ?");
            stmt.setString(1, bio);
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();

            System.out.println("Current user's bio updated: " + bio);

            conn.close();
            stmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void updateImg(String path){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        try{
            conn = db.getConnection();
            stmt = conn.prepareStatement("update User set profileimg = ? where emailaddress = ?");
            stmt.setString(1, path);
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();

            System.out.println("Current user's image path updated: " + path);

            conn.close();
            stmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
