package src.Chine;

import java.util.ArrayList;
public class XmlQuery {
    private String XQUERY0 ="for $d in /chine/departements/departement\n" +
            "let $total := sum(\n" +
            "    for $e in /chine/employes/employe[@idDepartement = $d/@idDepartement]\n" +
            "    for $sf in /chine/seFormes/seForme[@idEmploye = $e/@idEmploye][contains(dateDebutFormation, '2022-')]\n" +
            "    let $f := /chine/formations/formation[@idFormation = $sf/@idFormation]\n" +
            "    return $f/coutFormation\n" +
            ")\n" +
            "return <resultat><departement>{$d/nomDepartement/text()}</departement><total>{$total}</total></resultat>";

    private String XQUERY1 = "let $employes := /chine/employes/employe[contains(dateDeNaissance,'1993-') or contains(dateDeNaissance,'1994-') or contains(dateDeNaissance,'1995-') or contains(dateDeNaissance,'1996-') or contains(dateDeNaissance,'1997-') ]/@idEmploye " +
                             "let $moyenneSalaires := avg(/chine/contratsDureeIndeterminee/contrat[@idEmploye=$employes]/salaire) " +
                             "return <resultat><moyenne>{$moyenneSalaires}</moyenne></resultat>";
    private String XQUERY2 = "for $e in /chine/employes/employe\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "where $c/salaire > 3500\n" +
            "return concat('Nom : ', $e/nom, '| Fonction : ', $p/libelle, '| Salaire : ', $c/salaire)";
    private String XQUERY3 = "for $departement in /chine/departements/departement\n" +
            "let $employe := /chine/employes/employe[@idDepartement = $departement/@idDepartement]\n" +
            "let $performances := /chine/performances/performance\n" +
            "let $maxNote := max($performances[@idEmploye = $employe/@idEmploye]/notePerformance)\n" +
            "for $employeP in $employe[@idEmploye = /chine/performances/performance[notePerformance = $maxNote]/@idEmploye]\n" +
            "let $poste := /chine/postes/poste[@idPoste = $employeP/@idPoste]\n" +
            "return concat('Departement: ', $departement/nomDepartement, ' Employe: ', $employeP/nom, ' Poste: ', $poste/libelle, ' Note: ', $maxNote)ß";
    private String XQUERY4 = "let $nombre_absences :=\n" +
            "count(for $a in /chine/absences/absence\n" +
            "let $e := /chine/employes/employe[@idEmploye = $a/@idEmploye]\n" +
            "let $d := /chine/departements/departement[@idDepartement = $e/@idDepartement]\n" +
            "where $d/nomDepartement = 'Ventes et marketing' and $a/motifAbsence = 'Formation'\n" +
            "return $a)\n" +
            "return $nombre_absences";
    private String XQUERY5 = "for $emp in /chine/employes/employe\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $emp/@idEmploye]/notePerformance\n" +
            "where avg($perf) <= 12\n" +
            "return concat('nom= ', $emp/nom, ' poste= ', /chine/postes/poste[@idPoste = $emp/@idPoste]/libelle, ' departement= ', /chine/departements/departement[@idDepartement = $emp/@idDepartement]/nomDepartement, ' moyenne_performance= ', avg($perf))";
    private String XQUERY6 = "for $p in /chine/postes/poste[occupation = 'false']\n" +
            "order by $p/salaireBase ascending\n" +
            "return concat('libelle= ', $p/libelle, ' salaire de Base= ', $p/salaireBase, ' nombreHeuresParSemaine= ', $p/nombreHeuresParSemaine)";
    private String XQUERY7 = "for $sf in /chine/seFormes/seForme\n" +
            "let $f := /chine/formations/formation[@idFormation = $sf/@idFormation]\n" +
            "let $e := /chine/employes/employe[@idEmploye = $sf/@idEmploye]\n" +
            "let $pf1 := /chine/performances/performance[@idEmploye = $e/@idEmploye and contains(datePerformance, '2021-')]\n" +
            "let $pf2 := /chine/performances/performance[@idEmploye = $e/@idEmploye and contains(datePerformance, '2022-')]\n" +
            "where $pf2/notePerformance > $pf1/notePerformance\n" +
            "order by $e/nom\n" +
            "return concat('Nom: ', $e/nom, '| Note avant: ', $pf1/notePerformance, '| Date note avant: ', $pf1/datePerformance, '| Note après: ', $pf2/notePerformance, '| Date note après: ', $pf2/datePerformance, '| Type formation: ', $f/typeFormation, '| Date formation: ', $sf/dateDebutFormation, '&#xa;')\n";
    private String XQUERY8 = "for $e in /chine/employes/employe\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $e/@idEmploye][contains(datePerformance,'2022-')]\n" +
            "let $augmentation := xs:integer($c/salaire) - xs:integer($p/salaireBase)\n" +
            "return concat('Nom : ', $e/nom, '| Dernière performance : ', $perf/notePerformance, '| Poste : ', $p/libelle, '| Augmentation : ', $augmentation)\n";
    private String XQUERY9 = "for $e in /chine/employes/employe\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $e/@idEmploye][contains(datePerformance, '2022-')]\n" +
            "let $augmentation := xs:integer($c/salaire) - xs:integer($p/salaireBase)\n" +
            "where $augmentation <= 700 and $perf/notePerformance >= 15\n" +
            "return concat('Nom : ', $e/nom, '| Dernière performance : ', $perf/notePerformance, '| Poste : ', $p/libelle, '| Augmentation : ', $augmentation)\n";

    private ArrayList<String> QueryList = new ArrayList<String>();

    public XmlQuery() {
        QueryList.add(this.XQUERY0);
        QueryList.add(this.XQUERY1);
        QueryList.add(this.XQUERY2);
        QueryList.add(this.XQUERY3);
        QueryList.add(this.XQUERY4);
        QueryList.add(this.XQUERY5);
        QueryList.add(this.XQUERY6);
        QueryList.add(this.XQUERY7);
        QueryList.add(this.XQUERY8);
        QueryList.add(this.XQUERY9);
    }

    public String GetQuery(Integer index){
        return QueryList.get(index);
    }
}
