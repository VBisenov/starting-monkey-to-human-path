package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = {"name", "departments"})
public class Organization implements Serializable {

    private String name;

    @XmlElement(name = "department")
    private ArrayList<Department> departments;

    public Organization(){
    }

    public Organization(String name){
        this(name, new ArrayList<>());
    }

    public Organization(String name, ArrayList<Department> departments){
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Department> getListOfDepartments() {
        return departments;
    }


    public void add(Department department){
        this.departments.add(department);
    }


    public int resultSalary(){
        int result = 0;
        for (Department department: departments){
            result += department.resultSalary();
        }
        return result;
    }

    public int salaryAverage(){
        int count = 0;
        for (Department department: departments){
            count += department.getListOfEmployees().size();
        }
        return resultSalary() / count;
    }

    public int salaryAverage(String departmentName){
        for (Department department: departments){
            if (departmentName.equals(department.getName())){
                return department.salaryAverage();
            }
        }
        return 0;
    }

    public void setJobTitle(String firstName, String secondName, JobTitlesEnum newJobTitle){
        for (Department department: departments){
            if (department.isContains(firstName, secondName)) {
                department.getEmployee(firstName, secondName).setJobTitle(newJobTitle);
            }
        }
    }

    public void setSalary(String firstName, String secondName, int newSalary){
        for (Department department: departments){
            if (department.isContains(firstName, secondName)){
                department.getEmployee(firstName, secondName).setSalary(newSalary);
            }
        }
    }

    public void fireEmployee(String firstName, String secondName){
        for (Department department: departments){
            if (department.isContains(firstName, secondName)){
                 department.remove(firstName,secondName);
            }
        }
    }

    public boolean contains(Department department){
        for (Department dep: departments){
            if (dep.equals(department)){
                return true;
            }
        }
        return false;
    }

    public void addEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary, String departmentName){
        Employee employee = new Employee(firstName, secondName, salary, jobTitle);
        for (Department d: departments){
            if (d.getName().equals(departmentName)){
                d.add(employee);
            }
        }
    }
}
