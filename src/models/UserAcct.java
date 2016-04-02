package models;

/**
 * Created by Jessica on 4/2/2016.
 */
public class UserAcct implements java.io.Serializable {
    private String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
