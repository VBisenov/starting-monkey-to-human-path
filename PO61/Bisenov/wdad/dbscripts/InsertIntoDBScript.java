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
            String[] rows = {"id", "name"};
            String[] values = {"1", "manager"};
            insertData("jobtitles", rows, values, statement);
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
        resultValuesString = resultRowsSet.substring(0, resultRowsSet.length()-2); // removes the last comma from the values
        //String query = "INSERT INTO "+tableName+"("+resultRowsString+") "+"VALUES"+" ("+resultValuesString+")";

       statement.executeUpdate("INSERT INTO "+tableName+"("+resultRowsString+")"+"VALUES"+"("+resultValuesString+")");
    }
}
