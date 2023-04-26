package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    public static Connection getConnection() {
        try {
            // Set up the connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://103.197.185.4/coffeeorder", "dacduc", "Dacduc123@#");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
