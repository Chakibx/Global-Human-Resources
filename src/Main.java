package src;
import java.sql.*;
import src.Chine.Dom;
import src.France.PostgresqlConnection;
import src.France.PostgresqlQuery;
import src.France.PostgresqlQueryExecution;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*try {
            PostgresqlQuery query = new PostgresqlQuery();
            String sql = query.getPSQL9();
            PostgresqlQueryExecution execution = new PostgresqlQueryExecution(PostgresqlConnection.getConnection(),sql,9);
            execution.Execute();
        }catch (Exception e){
            System.out.println("Errors: "+e.getMessage());
        }*/
        Dom.xpath();
    }
}
