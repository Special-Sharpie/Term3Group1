package com.example.term3group1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    //Method that creates a database connection, returns a Connection
    public static Connection createConnection() throws ClassNotFoundException{
        String user = "";
        String password = "";
        String url = "";
        Connection conn = null;
        try {
            // Reads the connection settings from file
            FileInputStream fis = new FileInputStream("../connection.properties");
            Properties props = new Properties();
            props.load(fis);
            url = (String) props.get("url");
            user = (String) props.get("user");
            password = (String) props.get("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    url,
                    user,
                    password
            );
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

}
