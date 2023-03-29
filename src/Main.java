package src;

import src.test.Xquery;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
            try {
                Xquery.xpath();
                /*System.out.println("\n************************************************************************************");
                System.out.print("Enter Query Number: ");
                Scanner s = new Scanner(System.in);
                int QueryNumber = s.nextInt();

                //XML Data Base
                Dom.xpath(QueryNumber);*/

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

