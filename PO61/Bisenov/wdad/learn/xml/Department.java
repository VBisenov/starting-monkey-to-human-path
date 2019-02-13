package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "employees"})

public class Department {
    private String name;

    @XmlElement(name = "employee")
    private ArrayList<Employee> employees;

    public Department(){

    }

    public Department(String name){
        this(name, new ArrayList<>());
    }

    public Department(String name, ArrayList<Employee> employees){
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void add(Employee employee){
        this.employees.add(employee);
    }

    public int resultSalary(){
        int result = 0;
        for (Employee employee: employees){
            result += employee.getSalary();
        }
        return result;
    }

    public int salaryAverage(){
        return resultSalary()/this.employees.size();
    }

    public boolean isExist(String firstName, String secondName){
        for (Employee employee: employees){
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(String firstName, String secondName){
        for (Employee employee: employees){
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                return employee;
            }
        }
        return null;
    }

    public void remove(String firstName, String secondName){
        for (Employee employee: employees){
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                employees.remove(employee);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        Department  department = (Department) obj;
        return department.getName().equals(getName()) && department.getEmployees().equals(getEmployees());
    }
}
