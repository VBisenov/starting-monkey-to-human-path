package PO61.Bisenov.wdad.learn.rmi;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManager;
import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.Employee;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;
import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    static private String createregistry;
    static  private String registryaddress;
    static private int registryport;

    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        registryaddress = pm.getProperty(PreferencesManagerConstants.registryaddress);
        registryport = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.registryport));


        try{
            Registry registry = LocateRegistry.getRegistry("localhost", registryport);
            XmlDataManager manager = (XmlDataManager) registry.lookup("XmlDataManager");

            System.out.println(manager.salaryAverage());
            Department department = new Department("Security");
            Employee employee = new Employee("Artem","Filimonov", 30000, JobTitlesEnum.HEAD);
            department.add(employee);
            manager.add(department);
            manager.setSalary(employee, 35000);
            manager.setJobTitle(employee, JobTitlesEnum.ASSISTANT);
            //manager.fireEmployee(employee);

        } catch (RemoteException | NotBoundException | AlreadyAddedException ex){
            ex.printStackTrace();
        }
    }
}
