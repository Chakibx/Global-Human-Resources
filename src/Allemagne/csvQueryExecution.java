package src.Allemagne;
import java.util.*;
import org.apache.commons.dhcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
public class csvQueryExecution {
    private final static String csvFolder = "data\\allemagne\\";
    private final static String query ="select * + ' ' from Employe";
    public static void main(String[] args){
        BasicDataSource ds = new BasicDataSource();
    }
}
