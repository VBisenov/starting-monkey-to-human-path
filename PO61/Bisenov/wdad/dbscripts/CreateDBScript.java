package PO61.Bisenov.wdad.dbscripts;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBScript {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String DB_NAME = "organization";

    public static void main(String[] args) {
        Statement statement = Connector.connectionToServer(DB_URL, USER, PASS);
        createDataBase(DB_NAME ,statement);
        statement = Connector.connectionToDataBase(DB_URL, DB_NAME, USER, PASS);
        String[] empRows = {"id integer(10)", "first_name varchar(50)", "second_name varchar(50)", "birth_date date",
                "hire_date date", "salary numeric(15, 5)", "jobtitles_id integer(10)", "departments_id integer(10)"};
        createTable(statement, "employees", empRows);
        String[] depRows = {"id integer(10)", "name varchar(50)", "description varchar(255)"};
        createTable(statement,"departments", depRows);
        String[] jtRows = {"id integer(10)", "name varchar(100)"};
        createTable(statement,"jobtitles", jtRows);

        createPrimaryKey(statement, "employees", "id");
        createPrimaryKey(statement, "departments", "id");
        createPrimaryKey(statement, "jobtitles", "id");

        createForeignKey(statement, "employees", "departments_id", "departments", "id");
        createForeignKey(statement, "employees", "jobtitles_id", "jobtitles", "id");

        try {
            statement.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    private static void createDataBase(String DBName,Statement statement){
        try {
            System.out.print("Create database '"+DBName+"'...");
            statement.executeUpdate("DROP DATABASE IF EXISTS "+ DBName); //todo delete this line
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DBName);
            System.out.println(" OK");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void createTable(Statement statement, String name, String[] rows){
        StringBuilder sb = new StringBuilder();
        for (String str: rows){
                sb.append(str).append(", ");
        }
        String resultStringRows = sb.toString().substring(0, sb.toString().length()-2); // delete the last comma
        try {
            System.out.print("Create table '"+name+"' in database '"+DB_NAME+"'...");
            statement.executeUpdate("CREATE TABLE "+name+" ("+resultStringRows+")");
            System.out.println(" OK");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    private static void createForeignKey(Statement statement, String tableName, String columnName, String foreignTable, String foreignColumn){
        try {
            String query = "ALTER TABLE "+tableName+" ADD FOREIGN KEY ("+columnName+") REFERENCES "+foreignTable+" ("+foreignColumn+");";
         //   String query = "ALTER TABLE employees ADD FOREIGN KEY (departments_id) REFERENCES departments (id)";
            statement.execute(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void createPrimaryKey(Statement statement, String tableName, String columnName){
        try{
            String query = "ALTER TABLE "+tableName+" ADD PRIMARY KEY AUTO_INCREMENT ("+columnName+")";
            statement.execute(query);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}