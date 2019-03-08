package PO61.Bisenov.wdad.dbscripts;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoDBScript {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String DB_NAME = "organization";

    public static void main(String[] args) {
        try {
            Statement statement = Connector.connectionToDataBase(DB_URL, DB_NAME, USER, PASS);
            String[] jbRows = {"id", "name"};
            String[] jbValues = {"1", "manager"};
            insertData("jobtitles",jbRows, jbValues, statement);
            String[] empRows = {"id", "first_name", "second_name", "birth_date",
                    "hire_date", "salary", "jobtitles_id", "departments_id"};
            String[] empValues = {"1", "Vova", "Bisenov", "1999-06-18", "2019-07-23", "20000", "1", "1"};
            insertData("employees", empRows, empValues, statement);
            String[] depRows = {"id", "name", "description"};
            String[] depValues = {"1", "engineering", "department of engineering"};
            insertData("departments", depRows, depValues, statement);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void insertData(String tableName, String rows[], String values[], Statement statement) throws SQLException{
        String resultRowsStringSet, resultValuesStringSet;

        StringBuilder resultRowsSet = new StringBuilder();
        for (String string: rows){
            resultRowsSet.append(string).append(", ");
        }
        resultRowsStringSet = resultRowsSet.substring(0, resultRowsSet.length()-2); // removes the last comma from the rows

        StringBuilder resultValuesSet = new StringBuilder();
        for (String string: values){
            resultValuesSet.append("'"+string+"'").append(", ");
        }
        System.out.print("Insert data in database table '"+tableName+"'...");
        resultValuesStringSet = resultValuesSet.substring(0, resultValuesSet.length()-2); // removes the last comma from the value
        String query = "INSERT INTO "+tableName+" ( "+resultRowsStringSet+" ) VALUES "+"( "+resultValuesStringSet+" )";
        statement.executeUpdate(query);
        System.out.println(" OK");
    }
}
