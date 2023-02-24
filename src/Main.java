package src;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

import org.postgresql.util.ReaderInputStream;
import src.Chine.Dom;
import src.France.PostgresqlConnection;
import src.France.PostgresqlQuery;
import src.France.PostgresqlQueryExecution;
import src.Usa.MysqlConnection;
import src.Usa.MysqlQuery;
import src.Usa.MysqlQueryExecution;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws SQLException {
       while(true){
            try {
                System.out.println("\n************************************************************************************");
                System.out.print("Enter Query Number: ");
                Scanner s = new Scanner(System.in);
                int QueryNumber = s.nextInt();

                //Mysql Databse
                MysqlQuery Mquery = new MysqlQuery();
                String Msql = Mquery.GetQuery(QueryNumber);
                MysqlQueryExecution Mexecution = new MysqlQueryExecution(MysqlConnection.getConnection(),Msql,QueryNumber);
                Mexecution.Execute();

                //postgresql Database
                PostgresqlQuery Pquery = new PostgresqlQuery();
                String Psql = Pquery.GetQuery(QueryNumber);
                PostgresqlQueryExecution Pexecution = new PostgresqlQueryExecution(PostgresqlConnection.getConnection(),Psql,QueryNumber);
                Pexecution.Execute();

            }catch (Exception e){
                System.out.println("Errors: "+e.getMessage());
            }
        }
        /*//XML Data Base
        Dom.xpath(0);*/

    }

}
