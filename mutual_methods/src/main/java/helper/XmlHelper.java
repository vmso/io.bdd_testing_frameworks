package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlHelper {


    private final Logger log = LogManager.getLogger(XmlHelper.class);

    /**
     * Convert string object to xml file.
     *
     * @param xml String xml
     * @return Document
     */
    public Document stringXmlToDoc(String xml) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Xml file couldn't convert to xml.");
        }
        return null;
    }

    /**
     * find element by xpath from xml or document file and return the element nodes.
     *
     * @param xPath Xpath for searching element
     * @param doc   xml or html document for searching.
     * @return found nodes
     */
    private NodeList getNodesFromXmlDocByXpath(String xPath, Document doc) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            log.error(" Couldn't find element by {} from {}", doc, xPath);
        }
        return null;
    }

    /**
     * Finds the element by xpath and then update with new value.
     *
     * @param xml      The xml file to update
     * @param xPath    Xpath for searching element
     * @param newValue new value to update
     * @return updated document.
     */
    protected Document updateXmlNodesByXpath(String xml, String xPath, String newValue) {
        Document doc = stringXmlToDoc(xml);
        NodeList nodes = getNodesFromXmlDocByXpath(xPath, doc);

        for (int idx = 0; idx < nodes.getLength(); idx++) {
            nodes.item(idx).setTextContent(newValue);
            nodes.item(idx).setNodeValue(newValue);
        }
        return doc;
    }

    /**
     * converts to document object to string
     *
     * @param doc is document to convert to string
     * @return is document as string
     */
    protected String docToXmlString(Document doc) {
        try {

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString().replaceAll("[\n\r]", "");
        } catch (TransformerException e) {
            log.error("{},document couldn't convert to string", doc);
        }
        return null;
    }

}
