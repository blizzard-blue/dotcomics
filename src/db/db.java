package db;

import com.google.appengine.api.utils.SystemProperty;
import com.google.cloud.sql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jessica on 3/19/2016.
 */
public class db {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url;
        String dbdriver = null;
        Connection conn = null;

        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Load the class that provides the new "jdbc:google:mysql://" prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");
            url = "jdbc:google:mysql://dotcomics-v2:dotcomics-db/dotcomicsdb";
            dbdriver = "com.mysql.jdbc.GoogleDriver";
        } else {
            // Local MySQL instance to use during development.
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://173.194.231.139:3306/dotcomicsdb";
            dbdriver = "com.mysql.jdbc.Driver";
        }

        //context.setAttribute("dbdriver", dbdriver);
        //context.setAttribute("url", url);

        conn = DriverManager.getConnection(url, "root", "bluesclues");
        System.out.print("DB connection successful.");


        return conn;

    }
//    public static ResultSet ExecQuery(String query){
//
//    }
//
//    public static int ExecUpdateQuery(String query){
//
//    }
}
