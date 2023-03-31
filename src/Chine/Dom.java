package src.Chine;
import net.sf.saxon.s9api.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.*;
import java.io.File;
import java.util.ArrayList;

import net.sf.saxon.s9api.XdmAtomicValue;
import src.QueryClasses.Query_0;

public class Dom {
    public static ArrayList<Query_0> Execute_query_0(ArrayList<Query_0> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile =  new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(0);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
                XdmSequenceIterator iterator = result.iterator();
                while(iterator.hasNext()){
                    XdmNode node1 = (XdmNode) iterator.next();
                    XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                    String departmentValue = " ";
                    while (j.hasNext()) {
                        XdmNode child = (XdmNode) j.next();
                        if(child.getNodeName().toString().equals("departement")){
                            departmentValue = child.getStringValue();
                            //System.out.println("fils departement: "+ child.getStringValue());
                        }
                        if(child.getNodeName().toString().equals("total")){
                            if (departmentValue.equals("Ventes et marketing")) {
                                Query_0 q = liste.get(0);
                                Query_0 p = new Query_0(departmentValue,(Integer.valueOf(child.getStringValue()) + q.getCoutTotal()),1);
                                liste.set(0, p);
                            }
                            if (departmentValue.equals( "Ressources humaines")) {
                                Query_0 q = liste.get(1);
                                Query_0 p = new Query_0(departmentValue,(Integer.valueOf(child.getStringValue()) + q.getCoutTotal()),1);
                                liste.set(1, p);
                            }
                            if (departmentValue.equals("IT")) {
                                Query_0 q = liste.get(2);
                                Query_0 p = new Query_0(departmentValue,(Integer.valueOf(child.getStringValue()) + q.getCoutTotal()),1);
                                liste.set(2, p);
                            }
                            if (departmentValue.equals("Finance")) {
                                Query_0 q = liste.get(3);
                                Query_0 p = new Query_0(departmentValue,(Integer.valueOf(child.getStringValue()) + q.getCoutTotal()),1);
                                liste.set(3, p);
                            }
                        }
                    }
                }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        return liste;
    }
    public static Double Execute_query_1(Double moyenne) throws SaxonApiException{
        // Chargement du fichier XML en entrée
        File inputFile =  new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(0);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while(iterator.hasNext()){
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if(child.getNodeName().toString().equals("resultat")){
                       moyenne = moyenne + Double.valueOf(child.getStringValue());
                       System.out.println("valeur xquery");
                    }
                }
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        return moyenne;
    }
}
