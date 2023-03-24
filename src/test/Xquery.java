package src.test;
import net.sf.saxon.s9api.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Xquery {
    public static void xpath() throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile =  new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();

        // Définition de la requête XQuery
        String xqueryExpression = "for $p in chine/postes/poste[occupation = 'false']" +
                "order by $p/salaireBase ascending\n" +
                "return concat('libelle= ', $p/libelle, ' salaire de Base= ', $p/salaireBase, ' nombreHeuresParSemaine= ', $p/nombreHeuresParSemaine)\n";
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
