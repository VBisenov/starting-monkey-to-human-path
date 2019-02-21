package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Properties;

public class PreferencesManager {
    private static PreferencesManager instance;
    public static final String XML_PATH = "src\\PO61\\Bisenov\\wdad\\resources\\configuration\\appconfig.xml" ;
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

    @Deprecated
    public void setCreateRegistry(String createRegistry) {
        document.getElementsByTagName("createregistry").item(0).setTextContent(createRegistry);
        saveXML();
    }

    @Deprecated
    public String getCreateRegistry() {
        return document.getElementsByTagName("createregistry").item(0).getTextContent();
    }

    @Deprecated
    public void setRegistryAddress(String registryAddress) {
        document.getElementsByTagName("registryaddress").item(0).setTextContent(registryAddress);
        saveXML();
    }

    @Deprecated
    public String getRegistryAddress() {
        return document.getElementsByTagName("registryaddress").item(0).getTextContent();
    }

    @Deprecated
    public void setRegistryPort(String registryPort) {
        document.getElementsByTagName("registryport").item(0).setTextContent(registryPort);
        saveXML();
    }

    @Deprecated
    public String getRegistryPort() {
        return document.getElementsByTagName("registryport").item(0).getTextContent();
    }

    @Deprecated
    public void setPolicyPath(String policyPath) {
        document.getElementsByTagName("policypath").item(0).setTextContent(policyPath);
        saveXML();
    }

    @Deprecated
    public String getPolicyPath() {
        return document.getElementsByTagName("policypath").item(0).getTextContent();
    }

    @Deprecated
    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        document.getElementsByTagName("usecodebaseonly").item(0).setTextContent(useCodeBaseOnly);
        saveXML();
    }

    @Deprecated
    public String getUseCodeBaseOnly() {
        return document.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
    }

    @Deprecated
    public void setClassProvider(String classProvider) {
        document.getElementsByTagName("classprovider").item(0).setTextContent(classProvider);
        saveXML();
    }

    @Deprecated
    public String getClassProvider() {
        return document.getElementsByTagName("classprovider").item(0).getTextContent();
    }


    public String getNameOfElement(String key){
        String[] strings = key.split("[.]");
        return strings[strings.length-1];
    }

    public void setProperty(String key, String value){
        document.getElementsByTagName(getNameOfElement(key)).item(0).setTextContent(value);
        saveXML();
    }

    public String getProperty(String key){
       return document.getElementsByTagName(getNameOfElement(key)).item(0).getTextContent();
    }

    public void setProperties(Properties prop){
        prop.stringPropertyNames().forEach(s -> setProperty(s,prop.getProperty(s)));
    }

    public Properties getProperties(){
        Properties properties = new Properties();
        String[] keys = {PreferencesManagerConstants.classprovider,PreferencesManagerConstants.createregistry,
                PreferencesManagerConstants.policypath, PreferencesManagerConstants.registryaddress,
                PreferencesManagerConstants.usecodebaseonly, PreferencesManagerConstants.registryport};
        for(String s : keys){
            properties.setProperty(s,document.getElementsByTagName(getNameOfElement(s)).item(0).getTextContent());
        }
        return properties;
    }


    public void addBindedObject(String name, String className){
        Element element = document.createElement("bindedObject");
        element.setAttribute("name", name);
        element.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(element);
        saveXML();
    }
    public void removeBindedObject(String name){
        NodeList list = document.getElementsByTagName("bindedBoject");
        Element element;
        for (int i = 0; i < list.getLength(); i++) {
            element = (Element) list.item(i);
            if (element.getAttribute("name").equals(name)){
                element.getParentNode().removeChild(element);
            }
        }
        saveXML();
    }
}