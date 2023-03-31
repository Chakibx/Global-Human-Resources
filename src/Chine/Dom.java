package src.Chine;
import net.sf.saxon.s9api.*;
import net.sf.saxon.s9api.XdmAtomicValue;
import net.sf.saxon.s9api.XdmNode;
import javax.xml.transform.stream.StreamSource;
import javax.xml.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import net.sf.saxon.s9api.XdmValue;

import src.QueryClasses.*;

public class Dom {
    public static ArrayList<Query_0> Execute_query_0(ArrayList<Query_0> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
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
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                String departmentValue = " ";
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("departement")) {
                        departmentValue = child.getStringValue();
                        //System.out.println("fils departement: "+ child.getStringValue());
                    }
                    if (child.getNodeName().toString().equals("total")) {
                        if (departmentValue.equals("Ventes et marketing")) {
                            Query_0 q = liste.get(0);
                            Query_0 p = new Query_0(departmentValue, (Integer.valueOf(child.getStringValue()) + q.getCoutTotal()), 1);
                            liste.set(0, p);
                        }
                        if (departmentValue.equals("Ressources humaines")) {
                            Query_0 q = liste.get(1);
                            Query_0 p = new Query_0(departmentValue, (Integer.valueOf(child.getStringValue()) + q.getCoutTotal()), 1);
                            liste.set(1, p);
                        }
                        if (departmentValue.equals("IT")) {
                            Query_0 q = liste.get(2);
                            Query_0 p = new Query_0(departmentValue, (Integer.valueOf(child.getStringValue()) + q.getCoutTotal()), 1);
                            liste.set(2, p);
                        }
                        if (departmentValue.equals("Finance")) {
                            Query_0 q = liste.get(3);
                            Query_0 p = new Query_0(departmentValue, (Integer.valueOf(child.getStringValue()) + q.getCoutTotal()), 1);
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

    public static Double Execute_query_1(Double moyenne) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("/home/chakib/IdeaProjects/PDI/data/chine/Chine.xml");
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
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("resultat")) {
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

    public static ArrayList<Query_2> Execute_query_2(ArrayList<Query_2> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("D:/intellij/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(2);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                String nomValue = " ";
                String fonctionValue = " ";
                Double salaireValue = 0.0;
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("nom")) {
                        nomValue = child.getStringValue();

                    }
                    if (child.getNodeName().toString().equals("fonction")) {
                        fonctionValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("salaire")) {
                        salaireValue = Double.valueOf(child.getStringValue());
                    }
                }
                Query_2 p = new Query_2(nomValue, fonctionValue, salaireValue, 3);
                liste.add(p);
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        return liste;
    }

    public static ArrayList<Query_3> Execute_query_3(ArrayList<Query_3> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("D:/intellij/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(3);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                String departmentValue = " ";
                String posteValue = " ";
                String nomValue = " ";
                Integer notePerformanceValue = 0;
                int pays = 3;

                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("departement")) {
                        departmentValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("nom")) {
                        nomValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("poste")) {
                        posteValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("notePerformance")) {
                        notePerformanceValue = Integer.valueOf(child.getStringValue());
                    }
                    if (departmentValue.equals("Ventes et marketing")) {
                        Query_3 q = liste.get(0);
                        if (notePerformanceValue > q.getNotePerformance()) {
                            Query_3 p = new Query_3(departmentValue, posteValue, nomValue, notePerformanceValue, pays);
                            liste.set(0, p);
                        }
                    }
                    if (departmentValue.equals("Ressources humaines")) {
                        Query_3 q = liste.get(1);
                        if (notePerformanceValue > q.getNotePerformance()) {
                            Query_3 p = new Query_3(departmentValue, posteValue, nomValue, notePerformanceValue, pays);
                            liste.set(1, p);
                        }
                    }

                    if (departmentValue.equals("IT")) {
                        Query_3 q = liste.get(2);
                        if (notePerformanceValue > q.getNotePerformance()) {
                            Query_3 p = new Query_3(departmentValue, posteValue, nomValue, notePerformanceValue, pays);
                            liste.set(2, p);
                        }
                    }
                    if (departmentValue.equals("Finance")) {
                        Query_3 q = liste.get(3);
                        if (notePerformanceValue > q.getNotePerformance()) {
                            Query_3 p = new Query_3(departmentValue, posteValue, nomValue, notePerformanceValue, pays);
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
    public static ArrayList<Query_4> Execute_query_4(ArrayList<Query_4> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("D:/intellij/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(4);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();
        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                Integer absencesValue = 0;
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("absences")) {
                        absencesValue = Integer.valueOf(child.getStringValue());
                    }

                }
                Query_4 p = new Query_4(absencesValue, 3);
                liste.add(p);
            }

        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        return liste;
    }
    public static ArrayList<Query_5> Execute_query_5(ArrayList<Query_5> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("D:/intellij/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(5);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                String nomValue = " ";
                String posteValue = " ";
                String DepartementValue =" ";
                Double moyennePerformanceValue = 0.0;
                Integer pays =3;
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("nom")) {
                        nomValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("poste")) {
                        posteValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("departement")) {
                        DepartementValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("moyennePerformance")) {
                        moyennePerformanceValue = Double.valueOf(child.getStringValue());
                    }
                }
                Query_5 p = new Query_5(nomValue, posteValue, DepartementValue, moyennePerformanceValue,3);
                liste.add(p);
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        return liste;
    }
    public static ArrayList<Query_6> Execute_query_6(ArrayList<Query_6> liste) throws SaxonApiException {
        // Chargement du fichier XML en entrée
        File inputFile = new File("D:/intellij/PDI/data/chine/Chine.xml");
        StreamSource input = new StreamSource(inputFile);

        // Création du processeur Saxon
        Processor processor = new Processor(false);

        // Création du compilateur XQuery
        XQueryCompiler compiler = processor.newXQueryCompiler();
        XmlQuery queryGetter = new XmlQuery();
        // Définition de la requête XQuery
        String xqueryExpression = queryGetter.GetQuery(6);

        //Instantiation du getter
        XQueryExecutable xqueryExec = compiler.compile(xqueryExpression);

        // Évaluation de la requête XQuery et affichage des résultats
        XQueryEvaluator evaluator = xqueryExec.load();
        evaluator.setSource(input);
        XdmValue result = evaluator.evaluate();

        if (result.size() > 0) {
            XdmSequenceIterator iterator = result.iterator();
            while (iterator.hasNext()) {
                XdmNode node1 = (XdmNode) iterator.next();
                XdmSequenceIterator j = node1.axisIterator(Axis.CHILD);
                String posteValue = " ";
                Double salaireValue = 0.0;
                Integer nombreHeuresValue = 0;
                Integer pays =3;
                while (j.hasNext()) {
                    XdmNode child = (XdmNode) j.next();
                    if (child.getNodeName().toString().equals("poste")) {
                        posteValue = child.getStringValue();
                    }
                    if (child.getNodeName().toString().equals("salaire")) {
                        salaireValue = Double.valueOf(child.getStringValue());
                    }
                    if (child.getNodeName().toString().equals("nombreHeures")) {
                        nombreHeuresValue = Integer.valueOf(child.getStringValue());
                    }
                }
                Query_6 p = new Query_6(posteValue, salaireValue, nombreHeuresValue ,3);
                liste.add(p);
            }
        } else {
            System.out.println("Aucun résultat trouvé.");
        }
        Collections.sort(liste, (p1, p2) -> Double.compare(p1.getSalaireBase(), p2.getSalaireBase()));
        return liste;
    }

}

