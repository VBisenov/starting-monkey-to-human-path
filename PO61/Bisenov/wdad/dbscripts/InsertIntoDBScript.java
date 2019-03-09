package PO61.Bisenov.wdad.dbscripts;

import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoDBScript {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String DB_NAME = "organization";

    public static void main(String[] args) {
        Statement statement = Connector.connectionToDataBase(DB_URL, DB_NAME, USER, PASS);
        String[] jobTitlesRows = {"id", "name"};
        String[] employeesRows = {"id", "first_name", "second_name", "birth_date", "hire_date", "salary", "jobtitles_id", "departments_id"};
        String[] departmentsRows = {"id", "name", "description"};

        String[] firstJobTitlesValues = {"1", "head"};
        String[] firstEmployeesValues = {"1", "Vova", "Bisenov", "1999-06-18", "2019-07-23", "20000", "1", "1"};
        String[] firstDepartmentsValues = {"1", "engineering", "department of engineering"};

        String[] secondJobTitlesValues = {"2", "assistant"};
        String[] secondEmployeesValues = {"2", "Pasha", "Bastrikov", "1997-12-30", "2019-07-25", "25000", "2", "1"};

        String[] secondDepartmentValues = {"2", "design", "department of security"};
        String[] thirdEmployeesValues = {"3", "Artur", "Egorov", "1998-01-20", "2019-07-26", "30000", "1", "2"};

        insertIntoTable(statement, "jobtitles", jobTitlesRows, firstJobTitlesValues);
        insertIntoTable(statement, "jobtitles", jobTitlesRows, secondJobTitlesValues);

        insertIntoTable(statement, "departments", departmentsRows, firstDepartmentsValues);
        insertIntoTable(statement, "departments", departmentsRows, secondDepartmentValues);

        insertIntoTable(statement, "employees", employeesRows, firstEmployeesValues);
        insertIntoTable(statement, "employees", employeesRows, secondEmployeesValues);
        insertIntoTable(statement, "employees", employeesRows, thirdEmployeesValues);

        try {
            statement.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private static void insertIntoTable(Statement statement, String tableName, String[] rows, String[] values) {
        String rowsStringSet = getRowsStringFromStringsArray(rows);
        String valuesStringSet = getValuesStringFromStringsArray(values);
        String query = "INSERT INTO " + tableName + " (" + rowsStringSet + ") VALUES" + " (" + valuesStringSet + ") ";
        try {
            System.out.print("Insert data into table '"+tableName+"'...");
            statement.executeUpdate(query);
            System.out.println(" OK");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static String getRowsStringFromStringsArray(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
                sb.append(str).append(", ");
        }
        return sb.toString().substring(0, sb.length()-2); //delete last comma
    }

    private static String getValuesStringFromStringsArray(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append("'"+str+"'").append(", ");
        }
        return sb.toString().substring(0, sb.length()-2); //delete last comma
    }
}
