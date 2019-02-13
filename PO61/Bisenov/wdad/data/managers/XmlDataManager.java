package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.Employee;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.Remote;

public interface XmlDataManager extends Remote {
    int salaryAverage();
    int salaryAverage(String departmentName);
    void setJobTitle(Employee employee, JobTitlesEnum newJobTitle);
    void setSalary(Employee employee, int newSalary);
    void fireEmployee(Employee employee);
    void add(Department department) throws AlreadyAddedException;
}
