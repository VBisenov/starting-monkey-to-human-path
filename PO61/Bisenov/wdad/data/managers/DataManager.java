package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataManager extends Remote {
    int salaryAverage() throws RemoteException;
    int salaryAverage(String departmentName) throws RemoteException;
    void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle) throws RemoteException;
    void setSalary(String firstName, String secondName, int newSalary) throws RemoteException;
    void fireEmployee(String firstName, String secondName) throws RemoteException;
    void add(Department department) throws AlreadyAddedException, RemoteException;
}
