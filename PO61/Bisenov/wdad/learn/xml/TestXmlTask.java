package PO61.Bisenov.wdad.learn.xml;

public class TestXmlTask {
    public static void main(String[] args) {

        XmlTask xmlTask = new XmlTask();
        System.out.println(xmlTask.salaryAverage());
        System.out.println(xmlTask.salaryAverage("Software"));
        xmlTask.setSalary("Pasha", "Bastrikov", 55000);
        xmlTask.setJobTitle("Pasha", "Bastrikov", JobTitlesEnum.ASSISTANT);
        //xmlTask.fireEmployee("Pasha", "Bastrikov");
    }
}
