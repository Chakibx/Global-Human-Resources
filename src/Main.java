package src;
import java.sql.*;
import src.UsaDAO.MysqlConnection;
import src.UsaDAO.MysqlQuery;
import src.UsaDAO.MysqlQueryExecution;

public class Main {
    public static void main(String[] args) throws SQLException {
        MysqlQuery query = new MysqlQuery();
        String sql = query.getMSQL4();
        MysqlQueryExecution execution = new MysqlQueryExecution(MysqlConnection.getConnection(),sql,4);
        execution.Execute();
    }
}
