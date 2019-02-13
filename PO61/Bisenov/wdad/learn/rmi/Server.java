package PO61.Bisenov.wdad.learn.rmi;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManager;
import PO61.Bisenov.wdad.data.managers.XmlDataManagerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        try {
            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(pm.getProperty("registryport")));

            XmlDataManager manager = new XmlDataManagerImpl();
            //oracle пример на сайте

            /*
            Можно попробовать сделать:
            Класс XmlTask не принимает экземпляр Organization. Принимают его методы.
             */

        } catch (RemoteException ex){
            ex.printStackTrace();
        }
    }
}
