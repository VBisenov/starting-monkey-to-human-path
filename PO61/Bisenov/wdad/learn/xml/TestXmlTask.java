package PO61.Bisenov.wdad.learn.xml;

public class TestXmlTask {
    public static void main(String[] args) {
        Employee firstEmp = new Employee("Pasha", "Bastrikov", 40000, JobTitlesEnum.SECRETARY);
        Employee secondEmp = new Employee("Vova", "Bisenov", 80000, JobTitlesEnum.ENGINEER);
        Employee thirdEmp = new Employee("Egor", "Arutov", 50000, JobTitlesEnum.HEAD);

        Department firstDep = new Department("Software");
        firstDep.add(firstEmp);
        firstDep.add(secondEmp);

        Department secondDep = new Department("Security");
        secondDep.add(thirdEmp);

        Organization organization = new Organization("Apple");
        organization.add(firstDep);
        organization.add(secondDep);

        XmlTask xmlTask = new XmlTask();
        System.out.println(xmlTask.salaryAverage());
        System.out.println(xmlTask.salaryAverage("Software"));
//        xmlTask.setSalary("Pasha", "Bastrikov", 55000);
//        xmlTask.setJobTitle("Pasha", "Bastrikov", JobTitlesEnum.ASSISTANT);
        //xmlTask.fireEmployee("Pasha", "Bastrikov");
    }
}
