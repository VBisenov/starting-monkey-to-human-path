package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.RemoteException;

public class JDBCDataManagerTest implements DataManager {
    public static void main(String[] args) throws RemoteException {
        JDBCDataManager manager = new JDBCDataManager();
        manager.getInfo(); //todo here
    }

    @Override
    public int salaryAverage() throws RemoteException {
        return 0;
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
