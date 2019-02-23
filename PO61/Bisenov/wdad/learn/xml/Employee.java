package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName","secondName","hireDate","salary","jobTitle"})

public class Employee implements Serializable {
    private String firstName, secondName;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate hireDate;
    private int salary;
    private JobTitlesEnum jobTitle;

    public Employee(){

    }

    public Employee(String firstName, String secondName, int salary, JobTitlesEnum jobTitle){
        this(firstName, secondName, LocalDate.now(), salary, jobTitle);
    }

    private Employee(String firstName, String secondName, LocalDate hireDate, int salary, JobTitlesEnum jobTitle){
        this.firstName = firstName;
        this.secondName = secondName;
        this.hireDate = hireDate;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public JobTitlesEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitlesEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;

        return emp.getFirstName().equals(firstName) && emp.getSecondName().equals(secondName)
                && emp.getHireDate().equals(hireDate)
                && emp.getSalary() == salary && emp.getJobTitle().equals(jobTitle);
    }
}
