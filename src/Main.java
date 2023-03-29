package src;

import src.Chine.Dom;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            try {
                /* System.out.println("\n************************************************************************************");
                System.out.print("Enter Query Number: ");
                Scanner s = new Scanner(System.in);
                int QueryNumber = s.nextInt();*/

                //XML Data Base
                Dom.xquery(0);

               /* //Mysql Database
                MysqlQuery Mquery = new MysqlQuery();
                String Msql = Mquery.GetQuery(QueryNumber);
                MysqlQueryExecution Mexecution = new MysqlQueryExecution(MysqlConnection.getConnection(),Msql,QueryNumber);
                Mexecution.Execute();

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

