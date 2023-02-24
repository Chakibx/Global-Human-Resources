package src.Chine;
import javax.xml.transform.*;
import java.util.ArrayList;
public class XmlQuery {
    private String XPATH0 = "//seForme[starts-with(DateDebutFormation, '2022')]/@idFormation/parent::*/formation/coutFormation/text()";
    private String XPATH1 = "//employe[@idEmploye=//contrat[salaire>3500]/@idEmploye]/nom/text()";
    private String XPATH2 = "//employe[number(substring(dateDeNaissance, 1, 4)) >= 1993 and number(substring(dateDeNaissance, 1, 4)) <= 2003]/@idEmploye/ancestor::contrat/salaire";
    private ArrayList<String> QueryList = new ArrayList<String>();

    public XmlQuery() {
        QueryList.add(this.XPATH0);
        QueryList.add(this.XPATH1);
        QueryList.add(this.XPATH2);
    }

    public String GetQuery(Integer index){
        return QueryList.get(index);
    }
}
