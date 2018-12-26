package PO61.Bisenov.wdad.learn.xml;

import java.time.LocalDate;

public class Employee {
    private LocalDate hireDate;
    private int salary;
    private JOB_TITLES_ENUM jobTitle;
    private String firstName;
    private String secondName;

    public Employee(String firstName, String secondName, int salary, JOB_TITLES_ENUM jobTitle){
        this(firstName, secondName, salary, jobTitle, LocalDate.now());
    }

    public Employee(String firstName, String secondName, int salary, JOB_TITLES_ENUM jobTitle, LocalDate hireDate){
        this.firstName = firstName;
        this.secondName = secondName;
        this.salary = salary;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public JOB_TITLES_ENUM getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JOB_TITLES_ENUM jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, %d", firstName, secondName, jobTitle.toString(), salary);
    }

    @Override
    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;
        return emp.getFirstName().equals(firstName) && emp.getSecondName().equals(secondName) &&
                emp.getJobTitle().equals(jobTitle) && emp.getSalary() == salary;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() ^ secondName.hashCode() ^ jobTitle.hashCode() ^ salary;
    }
}
