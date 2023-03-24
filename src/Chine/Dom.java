package src.Chine;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class Dom {
    public static void xpath(Integer QueryNumber) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        // Chemin vers le fichier XML à traiter
        File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");

        // Création d'un objet Document à partir du fichier XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().parse(inputFile);

        // Création d'un objet XPath pour exécuter la requête XQuery
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        // Requête XQuery stockée dans une variable
        XmlQuery query = new XmlQuery();
        String xquery = query.GetQuery(QueryNumber);

        // Compilation de la requête XQuery en une expression XPath
        XPathExpression expr = xpath.compile(xquery);

        // Exécution de la requête XQuery sur le document XML
        Object result = expr.evaluate(doc, XPathConstants.NODESET);

        // Affichage des résultats
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }
}
