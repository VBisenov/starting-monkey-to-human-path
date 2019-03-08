package PO61.Bisenov.wdad.data.managers;

import PO61.Bisenov.wdad.utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Properties;

public class PreferencesManager {
    private static PreferencesManager instance;
    private static final String XML_PATH = "src\\main\\java\\PO61\\Bisenov\\wdad\\resources\\configuration\\appconfig.xml" ;
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
            TransformerFactory transformFactory = TransformerFactory.newInstance();
            Transformer transformer = transformFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(XML_PATH));
            transformer.transform(domSource, streamResult);
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

    private String getName(String key){
        String[] s = key.split("[.]");
        return s[s.length-1];
    }

    public void setProperty(String key, String value){
        document.getElementsByTagName(getName(key)).item(0).setTextContent(value);
    }

    public String getProperty(String key){
        return document.getElementsByTagName(getName(key)).item(0).getTextContent();
    }

    public void setProperties(Properties prop){
        String[] keys = {PreferencesManagerConstants.registryaddress, PreferencesManagerConstants.createregistry,
                PreferencesManagerConstants.registryport, PreferencesManagerConstants.policypath,
                PreferencesManagerConstants.usecodebaseonly, PreferencesManagerConstants.classprovider};
        for (String s: keys){
            prop.setProperty(s, prop.getProperty(s));
        }
    }

    public Properties getProperties() {
        Properties prop = new Properties();
        String[] keys = {PreferencesManagerConstants.registryaddress, PreferencesManagerConstants.createregistry,
                PreferencesManagerConstants.registryport, PreferencesManagerConstants.policypath,
                PreferencesManagerConstants.usecodebaseonly, PreferencesManagerConstants.classprovider};
        for (String s: keys){
            prop.setProperty(s, document.getElementsByTagName(getName(s)).item(0).getTextContent());
        }
        return prop;
    }

    public void addBindedObject(String name, String className){
        Element element = document.createElement("bindedObject");
        element.setAttribute("name", name);
        element.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(element);
        saveXML();
    }

    public void removeBindedObject(String name){
        NodeList list = document.getElementsByTagName("bindedObject");
        for (int i = 0; i < list.getLength(); i++) {
            Element el = (Element) list.item(i);
            if (el.getAttribute("name").equals(name)){
                el.getParentNode().removeChild(el);
            }
        }
        saveXML();
    }
}