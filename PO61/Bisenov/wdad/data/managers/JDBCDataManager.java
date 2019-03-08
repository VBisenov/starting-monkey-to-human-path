package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.dbscripts.Connector;
import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDataManager implements DataManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "qwerty";
    private static final String DB_NAME = "organization";
    //todo implements with SQL-query

    private Statement getStatement(){
        return Connector.connectionToDataBase(DB_URL, DB_NAME, USER, PASS);
    }

    @Override
    public int salaryAverage() {
        return 0;
    }


    public void getInfo(){
        ResultSet set = null;
        try {
            set = getStatement().executeQuery("SELECT * FROM employees");
            while (set.next()){
                String firstName = set.getString("first_name");
                System.out.println(firstName);
                System.out.println("qwerty");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int salaryAverage(String departmentName) throws RemoteException {
        return 0;
    }

    @Override
    public void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle) throws RemoteException {

    }

    @Override
    public void setSalary(String firstName, String secondName, int newSalary) throws RemoteException {

    }

    @Override
    public void fireEmployee(String firstName, String secondName) throws RemoteException {

    }

    @Override
    public void add(Department department) throws AlreadyAddedException, RemoteException {

    }
}
