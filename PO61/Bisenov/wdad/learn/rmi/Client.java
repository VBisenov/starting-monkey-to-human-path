package PO61.Bisenov.wdad.learn.rmi;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManager;
import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;
import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private static int registryPort;
    private static String registryAddress;
    public static void main(String[] args) {
        PreferencesManager manager = PreferencesManager.getInstance();
        registryPort = Integer.parseInt(manager.getProperty(PreferencesManagerConstants.registryport));
        registryAddress = PreferencesManagerConstants.registryaddress;
        try {
            System.out.print("Connecting to registry...");
            Registry registry = LocateRegistry.getRegistry(registryPort);
            System.out.println(" OK");
            XmlDataManager object = (XmlDataManager) registry.lookup("Organization");
            System.out.println(object.salaryAverage());
//            object.fireEmployee("Egor", "Arutov");
            object.setJobTitle("Egor", "Arutov", JobTitlesEnum.MANAGER);
            object.setSalary("Egor", "Arutov", 40000);
            Department department = new Department("Design");
            object.add(department);
        } catch (RemoteException | NotBoundException | AlreadyAddedException ex){
            ex.getMessage();
        }
    }
}
