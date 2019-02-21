package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.*;

public class XmlDataManagerImpl implements XmlDataManager{
    private XmlTask xmlTask;
    /*
    нужно создать объект XmlTask и через него реализовать эти методы
     */
    public XmlDataManagerImpl(){
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
    public void setJobTitle(Employee employee, JobTitlesEnum newJobTitle) {
        xmlTask.setJobTitle(employee, newJobTitle);
    }

    @Override
    public void setSalary(Employee employee, int newSalary) {
        xmlTask.setSalary(employee, newSalary);
    }

    @Override
    public void fireEmployee(Employee employee) {
        xmlTask.fireEmployee(employee);
    }

    @Override
    public void add(Department department) throws AlreadyAddedException{
        xmlTask.add(department);
    }
}
