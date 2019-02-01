package PO61.Bisenov.wdad.learn.xml;

public class XmlTask {

    private Organization organization;

    public XmlTask(Organization organization){
        this.organization = organization;
    }

    public int salaryAverage(){
        return organization.salaryAverage();
    }

    public int salaryAverage(String departmentName){
        return organization.salaryAverage(departmentName);
    }

    public void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle){
        organization.setJobTitle(firstName, secondName, newJobTitle);
    }

    public void setSalary(String firstName, String secondName, int newSalary){
        organization.setSalary(firstName, secondName, newSalary);
    }

    public void fireEmployee(String firstName, String secondName){
        organization.fireEmployee(firstName, secondName);
    }
}
