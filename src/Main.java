package src;

import src.Chine.Dom;
import src.Mediator.Mediator;
import src.QueryClasses.Query_0;
import src.Usa.MysqlConnection;
import src.Usa.MysqlQuery;
import src.Usa.MysqlQueryExecution;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            try {

                Mediator.mediate(0);

                /* System.out.println("\n************************************************************************************");
                System.out.print("Enter Query Number: ");
                Scanner s = new Scanner(System.in);
                int QueryNumber = s.nextInt();*/

                //XML Data Base
                //Dom.xquery(0);

                //Mysql Database
                /*MysqlQuery Mquery = new MysqlQuery();
                String Msql = Mquery.GetQuery(0);
                MysqlQueryExecution Mexecution = new MysqlQueryExecution(MysqlConnection.getConnection(),Msql,0);
                Mexecution.Execute();
                */
/*
                //postgresql Database
                PostgresqlQuery Pquery = new PostgresqlQuery();
                String Psql = Pquery.GetQuery(QueryNumber);
                PostgresqlQueryExecution Pexecution = new PostgresqlQueryExecution(PostgresqlConnection.getConnection(),Psql,QueryNumber);
                Pexecution.Execute();*/

            }catch (Exception e){
                System.out.println("Errors: "+e.getMessage());
            }
    }

}

