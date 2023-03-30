vpackage src.Mediator;

import src.Chine.XmlQuery;
import src.France.PostgresqlQueryExecution;
import src.QueryClasses.Query_0;
import src.Usa.MysqlQuery;
import src.Usa.MysqlQueryExecution;

import java.util.ArrayList;

public class Mediator {
    public static void mediate(Integer QueryNumber) {
        switch (QueryNumber) :
        case 0 :
            ArrayList<Query_0> Liste_0 = new ArrayList<Query_0>();
            Liste_0 = PostgresqlQueryExecution(QueryNumber, Liste_0);
            Liste_0 = XmlQuery(QueryNumber, Liste_0);
            Liste_0 = MysqlQueryExecution(QueryNumber, Liste_0);
            Liste_0 = Csv(QueryNumber,Liste_0);
    }
}
