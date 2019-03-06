package PO61.Bisenov.wdad.dbscripts;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class CreateDBScript {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String dbName = "organization";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            System.out.print("Creating DataBase...");
            statement.executeUpdate("drop database "+dbName); //todo delete this line
            createDataBase(dbName, statement);
            System.out.println(" OK");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, USER, PASS); // connecting to
            statement = connection.createStatement();                                                        // already created DB

            String[] empRows = {"id integer(10)", "first_name varchar(50)", "second_name varchar(50)", "birth_date date",
                    "hire_date date", "salary numeric(15, 5)", "jobtitles_id integer(10)", "departments_id integer(10)"};
            createTable("employees", empRows, statement);
            String[] depRows = {"id integer(10)", "name varchar(50)", "description varchar(255)"};
            createTable("departments", depRows, statement);
            String[] jtRows = {"id integer(10)", "name varchar(100)"};
            createTable("jobtitles", jtRows, statement);

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
        String resultRowsString = null;
        if (rows == null){
            System.out.println("You haven't insert rows in the table");
            return;
        }
        StringBuilder resultRowsSet = new StringBuilder();
        for (String str: rows){
                resultRowsSet.append(str).append(", ");
        }
        resultRowsString = resultRowsSet.substring(0, resultRowsSet.length()-2); // removes the last comma from the rows
        String query = ("CREATE TABLE "+name+" ("+resultRowsString+")");
        statement.executeUpdate(query);
    }

    private static void createDataBase(String name, Statement statement) throws SQLException{
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+name);
    }
}
