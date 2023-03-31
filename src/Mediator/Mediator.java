package src.Mediator;
import net.sf.saxon.s9api.SaxonApiException;
import src.Chine.Dom;
import src.Chine.XmlQuery;
import src.France.PostgresqlQueryExecution;
import src.QueryClasses.Query_0;
import src.QueryClasses.Query_2;
import src.QueryClasses.Query_3;
import src.QueryClasses.Query_4;
import src.QueryClasses.Query_5;
import src.QueryClasses.Query_6;
import src.QueryClasses.Query_7;
import src.QueryClasses.Query_8;
import src.Usa.MysqlQuery;
import src.Usa.MysqlQueryExecution;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mediator {
    public static void mediate(Integer QueryNumber) throws SQLException, SaxonApiException {
        switch (QueryNumber) {
            case 0 -> {
                ArrayList<Query_0> liste0 = new ArrayList<Query_0>();
                Query_0 q1 = new Query_0("Ventes et marketing",0,1);
                Query_0 q2 = new Query_0("Ressources humaines",0,1);
                Query_0 q3 = new Query_0("IT",0,1);
                Query_0 q4 = new Query_0("Finance",0,1);
                liste0.add(q1);
                liste0.add(q2);
                liste0.add(q3);
                liste0.add(q4);

                liste0 = MysqlQueryExecution.Execute_query_0(liste0);
                liste0 = PostgresqlQueryExecution.Execute_query_0(liste0);
                liste0 = Dom.Execute_query_0(liste0);
                for (Query_0 element : liste0) {
                        System.out.println(element.getNom()  + " " +element.getCoutTotal());
                }
            }
            case 1 -> {
                Double moyenne = 0.0;
                //moyenne = MysqlQueryExecution.Execute_query_1(moyenne);
                //moyenne = PostgresqlQueryExecution.Execute_query_1(moyenne);
                moyenne = Dom.Execute_query_1(moyenne);
                System.out.println("Moyenne= "+ moyenne/3);
            }
            case 2->{
                ArrayList<Query_2> liste2 = new ArrayList<Query_2>();
                liste2 = MysqlQueryExecution.Execute_query_2(liste2);
                liste2 = PostgresqlQueryExecution.Execute_query_2(liste2);
                liste2 = Dom.Execute_query_2(liste2);
                for (Query_2 element : liste2) {
                    System.out.println(element.getNom()  + " " +element.getFonction()+ " "+ element.getSalaire() + " " + element.getPays());
                }

            }
            case 3->{
                ArrayList<Query_3> liste3 = new ArrayList<Query_3>();
                Query_3 q1 = new Query_3("Ventes et marketing"," "," ", 0,0);
                Query_3 q2 = new Query_3("Ressources humaines"," "," ", 0,0);
                Query_3 q3 = new Query_3("IT"," "," ", 0,0);
                Query_3 q4 = new Query_3("Finance"," "," ", 0,0);
                liste3.add(q1);
                liste3.add(q2);
                liste3.add(q3);
                liste3.add(q4);

                liste3 = MysqlQueryExecution.Execute_query_3(liste3);
                liste3 = PostgresqlQueryExecution.Execute_query_3(liste3);
                liste3 = Dom.Execute_query_3(liste3);
                for (Query_3 element : liste3) {
                    System.out.println(element.getDepartement()  + " " +element.getPoste()+" "+element.getNom()+ " "+element.getNotePerformance()+" "+element.getPays());
                }
            }
            case 4->{
                ArrayList<Query_4> liste4 = new ArrayList<Query_4>();
                liste4 = MysqlQueryExecution.Execute_query_4(liste4);
                liste4 = PostgresqlQueryExecution.Execute_query_4(liste4);
                //liste4 = Dom.Execute_query_4(liste4);
                for (Query_4 element : liste4) {
                    System.out.println(element.getNombreAbsences()  + "|" +element.getPays());
                }
            }
            case 5->{
                ArrayList<Query_5> liste5 = new ArrayList<Query_5>();
                liste5 = MysqlQueryExecution.Execute_query_5(liste5);
                liste5 = PostgresqlQueryExecution.Execute_query_5(liste5);
                //liste5 = Dom.Execute_query_5(liste5);
                for (Query_5 element : liste5) {
                    System.out.println(element.getNom()+ "|" +element.getPoste()+"|"+element.getDepartement()+"|"+element.getMoyennePerformance()+"|"+element.getPays());
                }
            }
            case 6->{
                ArrayList<Query_6> liste6 = new ArrayList<Query_6>();
                liste6 = MysqlQueryExecution.Execute_query_6(liste6);
                liste6 = PostgresqlQueryExecution.Execute_query_6(liste6);
                //liste6 = Dom.Execute_query_6(liste6);
                for (Query_6 element : liste6) {
                    System.out.println(element.getPoste()+ "|" +element.getSalaireBase()+"|"+element.getNombreHeuresParSemaine()+"|"+element.getPays());
                }
            }
            case 7->{
                ArrayList<Query_7> liste7 = new ArrayList<Query_7>();
                liste7 = MysqlQueryExecution.Execute_query_7(liste7);
                liste7 = PostgresqlQueryExecution.Execute_query_7(liste7);
                //liste7 = Dom.Execute_query_7(liste7);
                for (Query_7 element : liste7) {
                    System.out.println(element.getNom()+ "|" +element.getNoteAvant()+"|"+element.getDateNoteAvant()+"|"+element.getNoteApres()+element.getTypeFormation()+"|"+element.getDateDebutFormation()+"|"+element.getPays());
                }
            }
            case 8->{
                ArrayList<Query_8> liste8 = new ArrayList<Query_8>();
                liste8 = MysqlQueryExecution.Execute_query_8(liste8);
                liste8 = PostgresqlQueryExecution.Execute_query_8(liste8);
                //liste8 = Dom.Execute_query_8(liste8);
                for (Query_8 element : liste8) {
                    System.out.println(element.getNom()+ "|" +element.getDernierePerformance()+"|"+element.getPoste()+"|"+element.getAugmentation()+" "+element.getPays());
                }
            }
        }


    }
}
