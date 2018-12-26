package PO61.Bisenov.wdad.learn.xml;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employees;

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

    public int size(){
        return employees.size();
    }

    public void add(Employee employee){
        employees.add(employee);
    }

    public int salaryAverage(){
        int result = 0;
        for (Employee employee: employees){
            result += employee.getSalary();
        }
        return result/size();
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

    public int salary(){
        int result = 0;
        for (Employee employee: employees){
            result += employee.getSalary();
        }
        return result;
    }
}
