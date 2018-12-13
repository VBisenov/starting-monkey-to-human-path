package PO61.Bisenov.wdad.data.managers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Set;

public class PreferencesManager {

    private static PreferencesManager instance;
    private Path xmlFile;
    private String path;
    private Document document;
    private XPath xPath;

    public static final String prefPath = "./src/PO61/Bisnov/wdad/learn/xml/organization.xml";

    private static final String[] keys = {"appconfig.rmi.server.registry.createregistry",
            "appconfig.rmi.server.registry.registryaddress",
            "appconfig.rmi.server.registry.registryport",
            "appconfig.rmi.client.policypath",
            "appconfig.rmi.client.usecodebaseonly",
            "appconfig.rmi.classprovider"
    };

    private PreferencesManager(String path) throws IOException, SAXException, ParserConfigurationException {
        this.path = path;
        this.xmlFile = Paths.get(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(true);
        this.document = builder.parse(xmlFile.toFile());
        XPathFactory xpathFactory = XPathFactory.newInstance();
        this.xPath = xpathFactory.newXPath();
    }

    public static PreferencesManager getInstance(String path) throws IOException, SAXException, ParserConfigurationException {
        if (instance == null) {
            instance = new PreferencesManager(path);
        }
        return instance;
    }

    private String lastElementKey(String keys) {
        String[] strings = keys.split("[.]");
        return strings[strings.length-1];
    }

    public void setProperty(String key, String value) {
        document.getElementsByTagName(lastElementKey(key)).item(0).setTextContent(value);
        saveXML();
    }

    public String getProperty(String key) throws PreferencesManagerException {
        Node node;
        try {
            node = (Node)xPath.evaluate(key.replace('.', '/'), document, XPathConstants.NODE);
            if (document == null) {
                System.out.println("null");
            } else {
                System.out.println("NOT null");
            }

        } catch (XPathExpressionException e) {
            throw new PreferencesManagerException("smth happened with preferences");
        }
        return node.getNodeValue();
    }

    public void setProperties(Hashtable<String,String> table) {
        Set<String> keySet = table.keySet();
        for (String s : keySet) {
            setProperty(s, table.get(s));
        }
    }

    public Hashtable<String, String> getProperties() {
        Hashtable<String, String> props = new Hashtable<>();
        for(String s : keys){
            try {
                props.put(s, getProperty(s));
            } catch (PreferencesManagerException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    public void addBindedObject(String name, String className) {
        Element element = document.createElement("bindedobject");
        element.setAttribute("name",name);
        element.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(element);
        saveXML();
    }

    public void removeBindedObject(String name) {
        NodeList nodeList = document.getElementsByTagName("bindedobject");
        Element element;
        for(int i=0;i<nodeList.getLength();i++){
            element = (Element) nodeList.item(i);
            if(element.getAttribute("name").equals(name)){
                element.getParentNode().removeChild(element);
            }
        }
        saveXML();
    }

    private void saveXML() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMImplementation domImpl = document.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype", "", "appconfig.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.path));
            transformer.transform(source, result);
        } catch (TransformerException e){
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

}