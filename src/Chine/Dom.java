package src.Chine;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.transform.*;
import java.io.File;


public class Dom {
    public static void xpath(Integer QueryNumber) {
        try {
            File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            XmlQuery query = new XmlQuery();
            String expression = query.GetQuery(QueryNumber);

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            System.out.println("\nChine: ");

            System.out.println("0: Les noms des employés qui gagnent > 3500\n");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println(nNode.getNodeValue());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}