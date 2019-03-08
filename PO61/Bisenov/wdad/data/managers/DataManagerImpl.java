package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.*;

public class DataManagerImpl implements DataManager {
    private XmlTask xmlTask;

    public DataManagerImpl(){
        xmlTask = new XmlTask();
    }

    @Override
    public int salaryAverage() {
        return xmlTask.salaryAverage();
    }

    @Override
    public int salaryAverage(String departmentName) {
        return xmlTask.salaryAverage(departmentName);
    }

    @Override
    public void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle) {
        xmlTask.setJobTitle(firstName, secondName, newJobTitle);
    }

    @Override
    public void setSalary(String firstName, String secondName, int newSalary) {
        xmlTask.setSalary(firstName, secondName, newSalary);
    }

    @Override
    public void fireEmployee(String firstName, String secondName) {
        xmlTask.fireEmployee(firstName, secondName);
    }

    @Override
    public void add(Department department) throws AlreadyAddedException{
        xmlTask.add(department);
    }
}
