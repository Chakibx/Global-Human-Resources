package src;
import java.sql.*;
import src.UsaDAO.MysqlConnection;
import src.UsaDAO.MysqlQuery;
import src.UsaDAO.QueryExecution;

public class Main {
    public static void main(String[] args) throws SQLException {
        MysqlQuery query = new MysqlQuery();
        String sql = query.getMSQL4();
        QueryExecution execution = new QueryExecution(MysqlConnection.getConnection(),sql,4);
        execution.Execute();
    }
}
