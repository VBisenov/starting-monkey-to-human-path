package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Department {
    private ArrayList<Employee> employees;
    private String name;

    public Department(String name){
        this(name, new ArrayList<>());
    }

    public Department(String name, ArrayList<Employee> employees){
        this.name = name;
        this.employees = employees;
    }

    public int size(){
        return employees.size();
    }

    @XmlElement
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Employee employee){
        this.employees.add(employee);
    }

    public double salaryAverage(){
        double salary = 0;
        for (Employee employee: employees) {
            salary += employee.getSalary();
        }
        return salary/employees.size();
    }

    public boolean isExist(String firstName, String secondName){
        for (Employee employee: employees){
                if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)) {
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
        employees.remove(getEmployee(firstName, secondName));
//        for(Employee employee: employees){
//            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
//                employees.remove(employee);
//            }
//        }
    }
}
