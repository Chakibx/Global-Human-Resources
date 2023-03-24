package src.Chine;
import javax.xml.transform.*;
import java.util.ArrayList;
public class XmlQuery {
    private String XPATH0 = "sum(//formation[@idFormation = //seForme[contains(DateDebutFormation,\"2022\")]/@idFormation]/coutFormation/text())";
    private String XPATH1 = "declare namespace chine = \"http://example.com/chine\";\n" +
            "\n" +
            "for $e in /chine:chine/chine:employes/chine:employe,\n" +
            "    $cdi in /chine:chine/chine:contratsDureeIndeterminee/chine:contrat[@idEmploye = $e/@idEmploye],\n" +
            "    $p in /chine:chine/chine:postes/chine:poste[@idPoste = $e/@idPoste]\n" +
            "where xs:integer($cdi/chine:salaire) > 3500\n" +
            "return <resultat>\n" +
            "           <nom>{data($e/chine:nom)}</nom>\n" +
            "           <fonction>{data($p/chine:libelle)}</fonction>\n" +
            "           <salaire>{data($cdi/chine:salaire)}</salaire>\n" +
            "           <date_debut_contrat>{data($cdi/chine:dateDebutContrat)}</date_debut_contrat>\n" +
            "       </resultat>\n";
    private String XPATH2 = "//employe[../performances/performance/notePerformance &lt;= 12]/concat(nom/text(), ', ', ../@idDepartement, ', ', ../postes/poste/libelle/text(), ', ', ../performances/performance/notePerformance/text())";
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