package PO61.Bisenov.wdad.learn.rmi;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManagerImpl;
import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private static int registryPort;
    private static String createRegistry;
    private static String registryAddress;
    public static void main(String[] args) {
        PreferencesManager manager = PreferencesManager.getInstance();
        registryPort = Integer.parseInt(manager.getProperty(PreferencesManagerConstants.registryport));
        createRegistry = manager.getProperty(PreferencesManagerConstants.createregistry);
        registryAddress = manager.getProperty(PreferencesManagerConstants.registryaddress);
        try {
            XmlDataManager obj = new XmlDataManagerImpl();
            XmlDataManager stub = (XmlDataManager) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry;
            System.out.print("Create registry...");
            if (createRegistry.equals("yes")) {
                registry = LocateRegistry.createRegistry(registryPort);
            } else {
                registry = LocateRegistry.getRegistry(registryPort);
            }
            System.out.println(" OK");
            System.out.print("Export object...");
            registry.bind("Organization", stub);
            System.out.println(" OK");
        } catch (RemoteException | AlreadyBoundException ex){
            ex.printStackTrace();
        }
    }
}
