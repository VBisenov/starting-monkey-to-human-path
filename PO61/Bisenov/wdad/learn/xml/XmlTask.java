package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlTask {

    private Organization organization;

    public XmlTask(){
        this.organization = (Organization) loadObjFromXml("src\\PO61\\Bisenov\\wdad\\learn\\xml\\organization.xml", Organization.class);
    }

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

    public Object loadObjFromXml(String filepath, Class c){
        Object obj = null;
        try{
            File file = new File(filepath);
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            obj = unmarshaller.unmarshal(file);
        }
        catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return obj;

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
        writeXML();
    }
}
