package PO61.Bisenov.wdad.dbscripts;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoDBScript {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/organization";
    private static final String USER = "root";
    private static final String PASS = "qwerty";

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();

            String[] jbRows = {"id", "name"};
            String[] jbValues = {"1", "manager"};
            insertData("jobtitles",jbRows, jbValues, statement);
            String[] empRows = {"id", "first_name", "second_name", "birth_date",
                    "hire_date", "salary", "jobtitles_id", "departments_id"};
            String[] empValues = {"1", "Vova", "Bisenov", "18-06-1999", "06-03-2019", "20000", "1", "1"};
            insertData("employees", empRows, empValues, statement);
            String[] depRows = {"id", "name", "description"};
            String[] depValues = {"1", "engineering", "department of engineering"};
            insertData("departments", depRows, depValues, statement);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    private static void insertData(String tableName, String rows[], String values[], Statement statement) throws SQLException{
        String resultRowsString, resultValuesString;

        StringBuilder resultRowsSet = new StringBuilder();
        for (String string: rows){
            resultRowsSet.append(string).append(", ");
        }
        resultRowsString = resultRowsSet.substring(0, resultRowsSet.length()-2); // removes the last comma from the rows

        StringBuilder resultValuesSet = new StringBuilder();
        for (String string: values){
            resultValuesSet.append(string).append(", ");
        }
        System.out.println("Insert data in database...");
        resultValuesString = resultRowsSet.substring(0, resultRowsSet.length()-2); // removes the last comma from the value
        statement.executeUpdate("INSERT INTO "+tableName+"("+resultRowsString+")"+"VALUES"+"("+resultValuesString+")");
        System.out.println(" OK");
    }
}
