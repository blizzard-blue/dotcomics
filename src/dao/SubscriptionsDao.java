package dao;

import db.db;
import models.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sicaz on 4/12/2016.
 */
public class SubscriptionsDao {
    private List<Subscription> subscriptions;
    private List<String> usernames;
    protected Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public SubscriptionsDao(String username) {
        subscriptions = new ArrayList<Subscription>();
        usernames = new ArrayList<String>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement("select * from User where username = ?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            int userid = 0;
            if(rs.next())
                userid = rs.getInt("userid");

            stmt = conn.prepareStatement("select author from Subscription where userid = ?");
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
            while(rs.next()) {
                usernames.add(rs.getString("author"));
            }

            for(String un : usernames) {
                stmt = conn.prepareStatement("select * from User where username = ?");
                stmt.setString(1, un);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    addSubscription(username, rs.getString("username"), "/account?author=" + rs.getString("username"));
                    System.out.println("Authors subscribed to" + rs.getString("username"));
                }
            }

            conn.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Subscription> getAllSubscriptions(){
        return subscriptions;
    }

    public void addSubscription(String username, String author, String path){
        Subscription s = new Subscription();
        s.setUser(username);
        s.setAuthor(author);
        s.setPath(path);
        s.toggleSubscribed();

        subscriptions.add(s);
    }

    public void subscribeAuthor(String uI, String uN){     //uN is the author current user is subscribing to
        try{
            conn = db.getConnection();

            stmt = conn.prepareStatement("select * from Subscription where userid = ? and author = ?");
            stmt.setString(1, uI);
            stmt.setString(2, uN);
            rs = stmt.executeQuery();

            boolean subscriptionValid = false;  //if bookmark already exists, bookmarkValid is false
            if(!rs.next())
                subscriptionValid = true;

            if(subscriptionValid){
                stmt = conn.prepareStatement("insert into Subscription(userid, author) values (?, ?)");
                stmt.setString(1, uI);
                stmt.setString(2, uN);
                stmt.executeUpdate();
            }

            conn.close();
            rs.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
