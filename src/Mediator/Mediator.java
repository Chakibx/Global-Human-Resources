package src.Mediator;
import net.sf.saxon.s9api.SaxonApiException;
import src.Chine.XmlQueryExecution;
import src.France.PostgresqlQueryExecution;
import src.QueryClasses.*;
import src.Usa.MysqlQueryExecution;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mediator {
    public static ArrayList<Query_0> mediate_query_0(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_0> liste0 = new ArrayList<Query_0>();
        Query_0 q1 = new Query_0("Ventes et marketing",0);
        Query_0 q2 = new Query_0("Ressources humaines",0);
        Query_0 q3 = new Query_0("IT",0);
        Query_0 q4 = new Query_0("Finance",0);
        liste0.add(q1);
        liste0.add(q2);
        liste0.add(q3);
        liste0.add(q4);

        if (USA == 1) liste0 = MysqlQueryExecution.Execute_query_0(liste0);
        if (France == 1) liste0 = PostgresqlQueryExecution.Execute_query_0(liste0);
        if (Chine == 1)liste0 = XmlQueryExecution.Execute_query_0(liste0);

        return liste0;
    }
    public static double mediate_query_1(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        Double moyenne = 0.0;
        int nbrCountrySelected=0;
        if (USA == 1) { moyenne = MysqlQueryExecution.Execute_query_1(moyenne); nbrCountrySelected++;}
        if (France == 1) {moyenne = PostgresqlQueryExecution.Execute_query_1(moyenne); nbrCountrySelected++;}
        if (Chine == 1) {moyenne = XmlQueryExecution.Execute_query_1(moyenne); nbrCountrySelected++;}
        return moyenne/nbrCountrySelected;
    }
    public static ArrayList<Query_2> mediate_query_2(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_2> liste2 = new ArrayList<Query_2>();
        if (USA == 1)liste2 = MysqlQueryExecution.Execute_query_2(liste2);
        if (France == 1)liste2 = PostgresqlQueryExecution.Execute_query_2(liste2);
        if (Chine ==1)liste2 = XmlQueryExecution.Execute_query_2(liste2);
        return  liste2;
    }
    public static ArrayList<Query_3> mediate_query_3(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_3> liste3 = new ArrayList<Query_3>();
        Query_3 q1 = new Query_3("Ventes et marketing"," "," ", 0,0);
        Query_3 q2 = new Query_3("Ressources humaines"," "," ", 0,0);
        Query_3 q3 = new Query_3("IT"," "," ", 0,0);
        Query_3 q4 = new Query_3("Finance"," "," ", 0,0);
        liste3.add(q1);
        liste3.add(q2);
        liste3.add(q3);
        liste3.add(q4);

        if(USA == 1)liste3 = MysqlQueryExecution.Execute_query_3(liste3);
        if(France == 1)liste3 = PostgresqlQueryExecution.Execute_query_3(liste3);
        if(Chine == 1)liste3 = XmlQueryExecution.Execute_query_3(liste3);
        return liste3;
    }
    public static ArrayList<Query_4> mediate_query_4(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_4> liste4 = new ArrayList<Query_4>();
        if (USA == 1)liste4 = MysqlQueryExecution.Execute_query_4(liste4);
        if (France == 1)liste4 = PostgresqlQueryExecution.Execute_query_4(liste4);
        if (Chine == 1)liste4 = XmlQueryExecution.Execute_query_4(liste4);
        return liste4;
    }
    public static ArrayList<Query_5> mediate_query_5(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_5> liste5 = new ArrayList<Query_5>();

        if (USA == 1) liste5 = MysqlQueryExecution.Execute_query_5(liste5);
        if (France == 1)liste5 = PostgresqlQueryExecution.Execute_query_5(liste5);
        if (Chine == 1)liste5 = XmlQueryExecution.Execute_query_5(liste5);
        return liste5;
    }
    public static ArrayList<Query_6> mediate_query_6(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_6> liste6 = new ArrayList<Query_6>();
        if (USA==1)liste6 = MysqlQueryExecution.Execute_query_6(liste6);
        if(France==1)liste6 = PostgresqlQueryExecution.Execute_query_6(liste6);
        if (Chine==1)liste6 = XmlQueryExecution.Execute_query_6(liste6);
        return liste6;
    }
    public static ArrayList<Query_7> mediate_query_7(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_7> liste7 = new ArrayList<Query_7>();
        if (USA==1)liste7 = MysqlQueryExecution.Execute_query_7(liste7);
        if(France==1)liste7 = PostgresqlQueryExecution.Execute_query_7(liste7);
        if(Chine==1)liste7 = XmlQueryExecution.Execute_query_7(liste7);

        return liste7;
    }
    public static ArrayList<Query_8> mediate_query_8(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_8> liste8 = new ArrayList<Query_8>();
        if(USA==1)liste8 = MysqlQueryExecution.Execute_query_8(liste8);
        if(France==1)liste8 = PostgresqlQueryExecution.Execute_query_8(liste8);
        if(Chine==1)liste8 = XmlQueryExecution.Execute_query_8(liste8);
        return liste8;
    }
    public static ArrayList<Query_9> mediate_query_9(Integer Chine, Integer France, Integer USA) throws SaxonApiException, SQLException {
        ArrayList<Query_9> liste9 = new ArrayList<Query_9>();
        if(USA==1)liste9 = MysqlQueryExecution.Execute_query_9(liste9);
        if(France==1)liste9 = PostgresqlQueryExecution.Execute_query_9(liste9);
        if(Chine==1)liste9 = XmlQueryExecution.Execute_query_9(liste9);

        return liste9;
    }

}
