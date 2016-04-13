package models;

/**
 * Created by Jessica on 4/2/2016.
 */
public class UserAcct implements java.io.Serializable {
    private String username;
    private String about;

    public String getUsername(){
        return username;
    }

    public String getAbout(){
        return about;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setAbout(String about){
        this.about = about;
    }
}
