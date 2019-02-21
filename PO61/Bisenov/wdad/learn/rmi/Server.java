package PO61.Bisenov.wdad.learn.rmi;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManagerImpl;
import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    static private String createregistry;
    static  private String registryaddress;
    static private int registryport;

    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        createregistry = pm.getProperty(PreferencesManagerConstants.createregistry);
        registryaddress = pm.getProperty(PreferencesManagerConstants.registryaddress);
        registryport = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.registryport));
        Registry registry = null;

        try {
            if (createregistry.equals("yes")) {
                System.out.print("Create registry...");
                registry = LocateRegistry.createRegistry(registryport);
                System.out.println(" OK");
            }


            if (registry != null) {
                System.out.print("Exporting object...");
                final XmlDataManager manager = new XmlDataManagerImpl();
                Remote stub = UnicastRemoteObject.exportObject(manager, 0);
                registry.bind("XmlDataManager", stub);
                System.out.println(" OK");
            }
            pm.addBindedObject("XmlDataManager", "src\\PO61\\Bisenov\\wdad\\data\\managers\\XmlDataManager.java");

        } catch (RemoteException | AlreadyBoundException ex){
            ex.printStackTrace();
        }
    }
}
