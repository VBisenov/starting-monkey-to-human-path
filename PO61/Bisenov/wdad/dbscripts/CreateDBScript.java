package PO61.Bisenov.wdad.dbscripts;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBScript {
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        String sqlQuery = "DROP DATABASE organization";
        try{
            //driver registration
            DriverManager.registerDriver(new Driver());
            /*
            alternative .registerDriver - Class.forName();
             */

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.print("Creating DataBase...");
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            System.out.println(" OK");

        } catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("DataBase not found");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
