package PO61.Bisenov.wdad.dbscripts;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class CreateDBScript {
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "qwerty";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            System.out.print("Creating DataBase...");
            statement.executeUpdate("drop database organization");
            createDataBase("organization", statement);
            System.out.println(" OK");

        //    statement.executeQuery("SELECT * FROM employee"); TODO проверить работу и ResultSet
            String[] rows = {"firstname varchar(50)", "secondname varchar(50)"};
            createTable("employee", rows, statement);

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
    private static void createTable(String name, String[] rows, Statement statement) throws SQLException{
        if (rows == null){
            System.out.println("You haven't insert rows in the table");
            return;
        }
        StringBuilder resultRowsSet = new StringBuilder();
        for (String str: rows){
            if (str != null){
                resultRowsSet.append(", ").append(str);
            }
        }
        String query = ("CREATE TABLE "+name+" ("+resultRowsSet+")");
        statement.executeUpdate(query);
    }

    private static void createDataBase(String name, Statement statement) throws SQLException{
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+name);
    }
}
