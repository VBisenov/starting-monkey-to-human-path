package PO61.Bisenov.wdad.data.managers;

public class PrefManagerTest {
    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        System.out.println(pm.getProperty("appconfig.rmi.server.registry.registryaddress"));
        pm.setProperty("appconfig.rmi.server.registry.registryaddress", "localhost");
        pm.addBindedObject("name", "class");
        pm.removeBindedObject("name");
    }
}
