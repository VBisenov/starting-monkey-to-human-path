package PO61.Bisenov.wdad.data.managers;

import javax.xml.xpath.XPathExpressionException;

public class PreferencesManagerException extends XPathExpressionException {

    public PreferencesManagerException(String message) {
        super(message);
    }

    public PreferencesManagerException(Throwable cause) {
        super(cause);
    }

}