package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection connection;
    private ConnectionSingleton(){

    }
    public static Connection getConnection(){
        if (connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library",
                        "root",
                        "matkhaula");
                System.out.println("successful connection");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("connection failed");
            }

        }return connection;
    }

}

