package PO61.Bisenov.wdad.learn.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlTask {
    private  Organization organization;
    private File file;

    public XmlTask(Organization organization, File file){
        this.organization = organization;
        this.file = file;
    }

    public void writeXML(File file) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Organization.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(organization,file );
        } catch (JAXBException e) {
            e.getMessage();
        }
    }

    public double salaryAverage(){
        double salary = 0;
        for (int i = 0; i < organization.size(); i++) {
            salary += organization.getDepartments().get(i).salaryAverage();
        }
        return salary/organization.size();
    }

    public double salaryAverage(String departmentName){
        double result = 0;
        for (int i = 0; i < organization.size(); i++) {
            if (organization.getDepartments().get(i).getName().equals(departmentName)){
                result = organization.getDepartments().get(i).salaryAverage();
            }
        }
        return result;
    }

    public void setJobTitle(String firstName, String secondName, JobTitleEnum jobTitle){
        for (int i = 0; i < organization.size(); i++) {
            if (organization.getDepartments().get(i).isExist(firstName, secondName)){
                organization.getDepartments().get(i).getEmployee(firstName, secondName).setJobTitle(jobTitle);
            }
        }
    }

    public void setSalary(String firstName, String secondName, int salary){
        for (int i = 0; i < organization.size(); i++) {
            if (organization.getDepartments().get(i).isExist(firstName, secondName)){
                organization.getDepartments().get(i).getEmployee(firstName, secondName).setSalary(salary);
            }
        }
    }

    public void fireEmployee(String firstName, String secondName){
        for (int i = 0; i < organization.size(); i++) {
            if (organization.getDepartments().get(i).isExist(firstName, secondName)){
                organization.getDepartments().get(i).remove(firstName, secondName);
            }
        }
    }
}
