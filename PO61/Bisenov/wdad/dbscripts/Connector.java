package PO61.Bisenov.wdad.dbscripts;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    public static Statement connectionToServer(String DB_URL, String User, String Pass) {
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(DB_URL, User,Pass);
            statement = connection.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return statement;
    }

    public static Statement connectionToDataBase(String DB_URL, String DB_Name, String User, String Pass){
        Statement statement = null;
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(DB_URL+DB_Name, User,Pass);
            statement = connection.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return statement;
    }
}
