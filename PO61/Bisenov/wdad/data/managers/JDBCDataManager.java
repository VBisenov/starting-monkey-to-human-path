package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.data.storage.DataSourceFactory;
import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDataManager implements DataManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String DB_NAME = "organization";

    private Statement getStatement(){
        Statement statement = null;
        DataSource ds = DataSourceFactory.createDataSource();
        try {
            Connection connection = ds.getConnection();
            statement = connection.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return statement;
    }

    @Override
    public int salaryAverage() {
        int result = 0;
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT AVG(salary) FROM employees");
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        System.out.print("Average salary in organization: ");
        return result;
    }

    @Override
    public int salaryAverage(String departmentName) throws RemoteException {
        int result = 0;
        int departmentID = getDepartmentID(departmentName);
        Statement statement = getStatement();
        if (departmentID == 0){
            System.err.println("Department ["+departmentName+"] does not exists");
        }
        try {
            ResultSet rs = statement.executeQuery("SELECT AVG(salary) FROM employees WHERE departments_id = '"+departmentID+"'");
            while (rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        System.out.print("Average salary in department "+departmentName+": ");
        return result;
    }

    @Override
    public void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle) throws RemoteException {
        int jobTitleID = getJobTitleID(newJobTitle);
        Statement statement = getStatement();
        if (jobTitleID == 0){
            System.err.println("Job title ["+newJobTitle.toString()+"] does not exists");
        }
        try {
            statement.executeUpdate("UPDATE employees SET jobtitles_id = '"+jobTitleID+"' WHERE first_name = '"+firstName+"' AND second_name = '"+secondName+"'");
            System.out.println("Set job title of employee ["+firstName+" "+secondName+"] to ["+newJobTitle.toString()+"]");
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void setSalary(String firstName, String secondName, int newSalary) throws RemoteException {
        int empID = getEmployeeID(firstName, secondName);
        Statement statement = getStatement();
        if (empID == 0){
            System.err.println("Employee ["+firstName+" "+secondName+"] does not exists");
        }
        try {
            statement.executeUpdate("UPDATE employees SET salary = '"+newSalary+"' WHERE id = '"+empID+"'");
            System.out.println("Changed salary of employee ["+empID+"] ["+firstName+" "+secondName+"] to "+newSalary);
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void fireEmployee(String firstName, String secondName) throws RemoteException {
        int empID = getEmployeeID(firstName, secondName);
        Statement statement = getStatement();
        if (empID == 0){
            System.err.println("Employee ["+firstName+" "+secondName+"] does not exists");
        }
        try {
            statement.executeUpdate("DELETE FROM employees WHERE id ='"+empID+"'");
            System.out.println("Removed employee ["+empID+"] ["+firstName+" "+secondName+"]");
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void add(Department department) throws AlreadyAddedException, RemoteException {
        Statement statement = getStatement();
        String departmentName = department.getName();
        int id = getNewDepID();
        try {

            String query = "INSERT INTO departments (id, name, description) VALUES ('"+id+"', '"+departmentName+"', 'department of "+departmentName+"' )";
            statement.executeUpdate(query);
            System.out.println("Create department ["+departmentName+"]");
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
    }

    private int getJobTitleID(JobTitlesEnum newJobTitle){
        int result = 0;
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT id FROM jobtitles WHERE name = '"+newJobTitle.toString()+"'");
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return result;
    }

    private int getDepartmentID(String departmentName){
        int id = 0;
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT id FROM departments WHERE name = '" + departmentName + "'");
            while (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return id;
    }

    private int getEmployeeID(String firstName, String secondName){
        int result = 0;
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery("SELECT id FROM employees WHERE first_name = '" + firstName + "' AND second_name = '" + secondName + "'");
            while (rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return result;
    }

    private int getNewDepID(){
        Statement statement = getStatement();
        int result = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM departments");
            while (rs.next()){
                result = rs.getInt(1)+1;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return result;
    }

    private void closeStatement(Statement statement){
        try {
            statement.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
