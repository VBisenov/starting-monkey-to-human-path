package PO61.Bisenov.wdad.data.storage;

import PO61.Bisenov.wdad.data.managers.PreferencesManager;
import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    public static DataSource createDataSource(){
        PreferencesManager manager = PreferencesManager.getInstance();
//        String className = manager.getProperty(PreferencesManagerConstants.classname);
//        String driverType = manager.getProperty(PreferencesManagerConstants.drivertype);
        String host = manager.getProperty(PreferencesManagerConstants.hostname);
        String port = manager.getProperty(PreferencesManagerConstants.port);
        String dbName = manager.getProperty(PreferencesManagerConstants.DBName);
        String user = manager.getProperty(PreferencesManagerConstants.user);
        String password = manager.getProperty(PreferencesManagerConstants.pass);

        MysqlDataSource ds = new MysqlDataSource();
        ds.setPassword(password);
        ds.setUser(user);
        ds.setServerName(host);
        ds.setDatabaseName(dbName);
        ds.setPort(Integer.valueOf(port));
        ds.setServerTimezone("UTC");
        return ds;
    }

    public static DataSource createDataSource(String className, String driverType, String host, int port, String dbName, String user, String password){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setPassword(password);
        ds.setUser(user);
        ds.setServerName(host);
        ds.setDatabaseName(dbName);
        ds.setPort(port);
        ds.setServerName("UTC");
        return ds;
    }
}
