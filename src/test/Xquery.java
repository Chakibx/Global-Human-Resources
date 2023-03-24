package src.test;

import java.io.File;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

public class Xquery {
    public static void xpath() throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XPath
        XPathCompiler compiler = processor.newXPathCompiler();

        // Définition de la requête XPath
        String xpathExpression = "//employe[telephone='1234567890']/prenom";
        XPathExecutable xpathExec = compiler.compile(xpathExpression);

        // Évaluation de la requête XPath et affichage des résultats
        XPathSelector selector = xpathExec.load();
        selector.setContextItem(processor.newDocumentBuilder().build(input));
        XdmValue result = selector.evaluate();
        if (result.size() > 0) {
            for (XdmItem item : result) {
                System.out.println(item.getStringValue());
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }



    }
}
