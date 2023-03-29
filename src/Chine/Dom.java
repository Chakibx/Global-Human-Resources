package src.Chine;
import net.sf.saxon.s9api.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Dom {
    public static void xquery(Integer QueryNumber) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile =  new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(QueryNumber);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            for (XdmItem item : result) {
                System.out.println(item.getStringValue());
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }

    }
}
