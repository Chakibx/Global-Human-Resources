package src.Mediator;
import net.sf.saxon.s9api.SaxonApiException;
import src.Chine.Dom;
import src.Chine.XmlQuery;
import src.France.PostgresqlQueryExecution;
import src.QueryClasses.Query_0;
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
        }

    }
}
