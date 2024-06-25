package com.axelprz.students.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    public static Connection getConnection() {
        Connection con = null;
        var db = "students_db";
        var url = "jdbc:mysql://localhost:3306/" + db;
        var user = "root";
        var password = "Mendoza05@";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return con;
    }
}
