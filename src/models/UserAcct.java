package models;

/**
 * Created by Jessica on 4/2/2016.
 */
public class UserAcct implements java.io.Serializable {
    private String username;
    private String about;
    private String userid;
    private String profileimg;

    public String getUserid() { return userid; }

    public String getUsername(){
        return username;
    }

    public String getAbout(){
        return about;
    }
    public String getProfileImg(){
        return profileimg;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setAbout(String about){
        this.about = about;
    }
    public void setUserid(String userid) { this.userid = userid; }
    public void setProfileImg(String path) { this.profileimg = path; }

}
