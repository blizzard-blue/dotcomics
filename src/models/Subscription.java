package models;

import java.io.Serializable;

/**
 * Created by Joseph on 4/13/2016.
 */
public class Subscription  implements java.io.Serializable{
    private String user;
    private String author;
    private boolean subscribed;
    private String path;

    public String getUser(){ return user; }
    public String getAuthor(){ return author; }
    public boolean isSubscribed(){ return subscribed; }
    public String getPath(){ return path; }

    public void setUser(String username){
        this.user = username;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setPath(String path){
        this.path = path;
    }
    public void toggleSubscribed(){
        if(subscribed)
            subscribed = false;
        else
            subscribed = true;
    }

}
