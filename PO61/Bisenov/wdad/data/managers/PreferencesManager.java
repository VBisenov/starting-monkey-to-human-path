package PO61.Bisenov.wdad.data.managers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class PreferencesManager {
    private static PreferencesManager instance;
    public static final String XML_PATH = "C:\\Users\\Владимир\\Desktop\\Учёба\\3 курс\\Java\\starting-monkey-to-human-path\\src\\PO61\\Bisenov\\wdad\\resources\\configuration\\appconfig.xml" ;
    private Document document;

    private PreferencesManager(){
        try{
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(XML_PATH);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static PreferencesManager getInstance(){
        if (instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    private void saveXML(){
        try{
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(document), new StreamResult(new File(XML_PATH)));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCreateRegistry(String createRegistry) {
        document.getElementsByTagName("createregistry").item(0).setTextContent(createRegistry);
        saveXML();
    }

    public String getCreateRegistry() {
        return document.getElementsByTagName("createregistry").item(0).getTextContent();
    }

    public void setRegistryAddress(String registryAddress) {
        document.getElementsByTagName("registryaddress").item(0).setTextContent(registryAddress);
        saveXML();
    }

    public String getRegistryAddress() {
        return document.getElementsByTagName("registryaddress").item(0).getTextContent();
    }

    public void setRegistryPort(String registryPort) {
        document.getElementsByTagName("registryport").item(0).setTextContent(registryPort);
        saveXML();
    }

    public String getRegistryPort() {
        return document.getElementsByTagName("registryport").item(0).getTextContent();
    }

    public void setPolicyPath(String policyPath) {
        document.getElementsByTagName("policypath").item(0).setTextContent(policyPath);
        saveXML();
    }

    public String getPolicyPath() {
        return document.getElementsByTagName("policypath").item(0).getTextContent();
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        document.getElementsByTagName("usecodebaseonly").item(0).setTextContent(useCodeBaseOnly);
        saveXML();
    }

    public String getUseCodeBaseOnly() {
        return document.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
    }

    public void setClassProvider(String classProvider) {
        document.getElementsByTagName("classprovider").item(0).setTextContent(classProvider);
        saveXML();
    }

    public String getClassProvider() {
        return document.getElementsByTagName("classprovider").item(0).getTextContent();
    }
}