package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

public class Employee {
    private String firstName;
    private String secondName;
    private LocalDate hireDate;
    private int salary;
    private JobTitleEnum jobTitle;

    public Employee(String firstName, String secondName, int salary, JobTitleEnum jobTitle) throws JAXBException{
        this(firstName, secondName, salary, LocalDate.now(), jobTitle);
    }

    public Employee(String firstName, String secondName, int salary, LocalDate hireDate, JobTitleEnum jobTitle) throws JAXBException {
        this.firstName = firstName;
        this.secondName = secondName;
        this.salary = salary;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
    }

    @XmlElement (name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement (name = "secondname")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @XmlElement (name = "hiredate")
    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @XmlElement (name = "salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @XmlElement (name = "jobtitle")
    public JobTitleEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

}
