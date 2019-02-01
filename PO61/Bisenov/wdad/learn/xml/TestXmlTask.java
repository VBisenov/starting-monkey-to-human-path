package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class TestXmlTask {
    public static void main(String[] args) {
        Employee firstEmp = new Employee("Pasha", "Bastrikov", 40000, JobTitlesEnum.SECRETARY);
        Employee secondEmp = new Employee("Vova", "Bisenov", 80000, JobTitlesEnum.ENGINEER);
        Employee thirdEmp = new Employee("Egor", "Arutov", 50000, JobTitlesEnum.HEAD);

        Department firstDep = new Department("Software");
        firstDep.add(firstEmp);
        firstDep.add(secondEmp);

        Department secondDep = new Department("Security");
        secondDep.add(thirdEmp);

        Organization organization = new Organization("Apple");
        organization.add(firstDep);
        organization.add(secondDep);

        XmlTask xmlTask = new XmlTask(organization);
        System.out.println(xmlTask.salaryAverage());
        System.out.println(xmlTask.salaryAverage("Software"));
        xmlTask.setSalary("Pasha", "Bastrikov", 55000);
        xmlTask.setJobTitle("Pasha", "Bastrikov", JobTitlesEnum.ASSISTANT);
        //xmlTask.fireEmployee("Pasha", "Bastrikov");

        File file = new File("C:\\Users\\Владимир\\Desktop\\Учёба\\3 курс\\Java\\starting-monkey-to-human-path\\src\\PO61\\Bisenov\\wdad\\learn\\xml\\organization.xml");
        try{
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(organization, file);
            System.err.println("Write to file: "+file.getAbsolutePath());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
