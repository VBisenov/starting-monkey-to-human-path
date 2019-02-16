package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.Employee;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XmlDataManager extends Remote {
    int salaryAverage() throws RemoteException;
    int salaryAverage(String departmentName) throws RemoteException;
    void setJobTitle(Employee employee, JobTitlesEnum newJobTitle) throws RemoteException;
    void setSalary(Employee employee, int newSalary) throws RemoteException;
    void fireEmployee(Employee employee) throws RemoteException;
    void add(Department department) throws AlreadyAddedException, RemoteException;
}
