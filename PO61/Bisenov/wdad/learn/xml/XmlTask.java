package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlTask {

    private Organization organization;

    private void writeXML(){
        File file = new File("src\\PO61\\Bisenov\\wdad\\learn\\xml\\organization.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(organization, file);
        } catch (JAXBException ex){
            ex.printStackTrace();
        }
    }

    public XmlTask(Organization organization){
        this.organization = organization;
    }

    public int salaryAverage(){
        return organization.salaryAverage();
    }

    public int salaryAverage(String departmentName){
        return organization.salaryAverage(departmentName);
    }

    public void setJobTitle(Employee employee, JobTitlesEnum newJobTitle){
        organization.setJobTitle(employee.getFirstName(), employee.getSecondName(), newJobTitle);
        writeXML();
    }

    public void setSalary(Employee employee, int newSalary){
        organization.setSalary(employee.getFirstName(), employee.getSecondName(), newSalary);
        writeXML();
    }

    public void fireEmployee(Employee employee){
        organization.fireEmployee(employee.getFirstName(), employee.getSecondName());
        writeXML();
    }

    public void add(Department department) throws AlreadyAddedException{
        if (organization.contains(department)){
            throw new AlreadyAddedException("Department is already added");
        }
        organization.add(department);
    }
}
