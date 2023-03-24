package src.test;

import java.io.File;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmValue;

public class Xquery {
    public static void xpath() throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();

        // Définition de la requête XQuery
        String xqueryExpression = "for $emp in /chine/employes/employe " +
                                  "let $perf := /chine/performances/performance[@idEmploye = $emp/@idEmploye]/notePerformance " +
                                  "where avg($perf) <= 12 " +
                                  "return concat('nom= ', $emp/nom, ' poste= ', /chine/postes/poste[@idPoste = $emp/@idPoste]/libelle, ' departement= ', /chine/departements/departement[@idDepartement = $emp/@idDepartement]/nomDepartement, ' moyenne_performance= ', avg($perf))";
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
