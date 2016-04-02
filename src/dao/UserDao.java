package dao;

import models.UserAcct;

/**
 * Created by Jessica on 3/30/2016.
 */
public class UserDao {

    public boolean userExists(String nickname){
        return true;
    }

    public UserAcct getUser(String nickname){
        // placeholder code
        UserAcct user = new UserAcct();
        user.setUsername(nickname);
        return user;
    }

    public void createUser(String nickname){

    }
}
