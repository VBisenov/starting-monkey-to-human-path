package PO61.Bisenov.wdad.learn.xml;

import java.util.ArrayList;

public class Organization {
    private String name;
    private ArrayList<Department> departments;

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

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void add(Department department){
        departments.add(department);
    }

    public int size() {
        return departments.size();
    }

    public int salaryAverage(){
        int result = 0;
        int count = 0;
        for (Department department: departments){
            result += department.salary();
            count += department.size();
        }
        return result/count;
    }

    public int salaryAverage(String departmentName){
        int result = 0;
        for (Department department: departments){
            if (department.getName().equals(departmentName)){
                result = department.salaryAverage();
            }
        }
        return result;
    }

    public void setJobTitle(String firstName, String secondName, JOB_TITLES_ENUM newJobTitle){
        for (Department department: departments){
            if (department.isExist(firstName, secondName)){
                department.getEmployee(firstName, secondName).setJobTitle(newJobTitle);
            }
        }
    }

    public void setSalary(String firstName, String secondName, int newSalary){
        for (Department department: departments){
            if (department.isExist(firstName, secondName)){
                department.getEmployee(firstName, secondName).setSalary(newSalary);
            }
        }
    }

    public void fireEmployee(String firstName, String secondName){
        for (Department department: departments){
            if (department.isExist(firstName, secondName)){
                department.remove(firstName, secondName);
            }
        }
    }
}
