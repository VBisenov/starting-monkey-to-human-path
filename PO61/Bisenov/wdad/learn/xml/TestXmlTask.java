package PO61.Bisenov.wdad.learn.xml;

public class TestXmlTask {
    public static void main(String[] args) {
        Employee firstEmployee = new Employee("Harry", "Potter", 5000, JOB_TITLES_ENUM.ASSISTANT);
        Employee secondEmployee = new Employee("Ron", "Wisley", 4000, JOB_TITLES_ENUM.ENGINEER);
        Employee thirdEmployee = new Employee("Hermiona", "Granger", 6000, JOB_TITLES_ENUM.HEAD);

        Employee fourthEmployee = new Employee("Draco", "Malfoy", 3000,JOB_TITLES_ENUM.HEAD);
        Employee fifthEmployee = new Employee("Vincent", "Crabbe", 4000, JOB_TITLES_ENUM.ASSISTANT);

        Department firstDepartment = new Department("Griffindor");
        firstDepartment.add(firstEmployee);
        firstDepartment.add(secondEmployee);
        firstDepartment.add(thirdEmployee);

        Department secondDepartment = new Department("Slytherin");
        secondDepartment.add(fourthEmployee);
        secondDepartment .add(fifthEmployee);

        Organization organization = new Organization("Hogwarts");
        organization.add(firstDepartment);
        organization.add(secondDepartment);

        XmlTask xmlTask = new XmlTask(organization);

        System.out.println(xmlTask.salaryAverage());
        System.out.println(xmlTask.salaryAverage("Slytherin"));
        xmlTask.setSalary("Harry", "Potter", 4000);
        xmlTask.setJobTitle("Harry", "Potter", JOB_TITLES_ENUM.HEAD);
        xmlTask.fireEmployee("Ron", "Wisley");
    }
}
