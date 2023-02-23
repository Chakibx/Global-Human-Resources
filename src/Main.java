package src;
import java.sql.*;
import src.UsaDAO.MysqlConnection;
import src.UsaDAO.Query;
import src.UsaDAO.QueryExecution;

public class Main {
    public static void main(String[] args) throws SQLException {
        Query query = new Query();
        String sql = query.getQuery1();
        QueryExecution execution = new QueryExecution(MysqlConnection.getConnection(),sql);
        execution.Execute();
    }
}
