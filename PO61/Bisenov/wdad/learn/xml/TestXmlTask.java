package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBException;
import java.io.File;

public class TestXmlTask {
    public static void main(String[] args) throws JAXBException {
        File file = new File("C:\\Users\\Владимир\\Desktop\\Учёба\\Лабы\\Java\\starting-monkey-to-human-path\\src\\PO61\\Bisenov\\wdad\\learn\\xml\\organization.xml");
        Employee firstEmployee = new Employee("Henry", "Thomassino", 5000, JobTitleEnum.assistant);
        Employee secondEmployee = new Employee("Joe", "Barbaro", 6000, JobTitleEnum.boss);

        Department department = new Department("dep");
        department.add(firstEmployee);
        department.add(secondEmployee);

        Organization organization = new Organization();
        organization.add(department);

        XmlTask task = new XmlTask(organization, file);

        task.setSalary("Joe","Barbaro", 5);
        task.setJobTitle("Henry", "Thomassino", JobTitleEnum.engineer);
     
        System.out.println(task.salaryAverage("dep"));
        System.out.println(task.salaryAverage());
        task.fireEmployee("Joe", "Barbaro");
        task.writeXML(file);
        
    }
}
