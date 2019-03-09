package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.learn.xml.AlreadyAddedException;
import PO61.Bisenov.wdad.learn.xml.Department;
import PO61.Bisenov.wdad.learn.xml.JobTitlesEnum;

import java.rmi.RemoteException;

public class JDBCDataManagerTest {
    public static void main(String[] args) throws RemoteException, AlreadyAddedException {
        JDBCDataManager manager = new JDBCDataManager();
        System.out.println(manager.salaryAverage());
        System.out.println(manager.salaryAverage("engineering"));
        manager.setSalary("Artur", "Egorov", 25000);
        //manager.fireEmployee("Pasha", "Bastrikov");
        manager.setJobTitle("Artur", "Egorov", JobTitlesEnum.ASSISTANT);
        Department department = new Department("hr");
        manager.add(department);
    }
}
