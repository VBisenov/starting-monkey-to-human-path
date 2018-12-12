package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Organization {
    private ArrayList<Department> departments;
    private String name;

    public Organization(){
        this.departments = new ArrayList<>();
    }
    public Organization(String name){
        this(name, new ArrayList<>());
    }

    public Organization(String name, ArrayList<Department> departments){
        this.name = name;
        this.departments = departments;
    }

    public int size(){
        return departments.size();
    }

    @XmlElement
    public ArrayList<Department> getDepartments() {
        return departments;
    }
    @XmlElement (name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Department department){
        this.departments.add(department);
    }

}
