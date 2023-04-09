package src.Chine;

import java.util.ArrayList;
public class XmlQuery {
    //le cout total des formations de l'annee derniere
    private String XQUERY0 ="for $d in /chine/departements/departement\n" +
            "let $total := sum(\n" +
            "    for $e in /chine/employes/employe[@idDepartement = $d/@idDepartement]\n" +
            "    for $sf in /chine/seFormes/seForme[@idEmploye = $e/@idEmploye][contains(dateDebutFormation, '2022-')]\n" +
            "    let $f := /chine/formations/formation[@idFormation = $sf/@idFormation]\n" +
            "    return $f/coutFormation\n" +
            ")\n" +
            "return <resultat><departement>{$d/nomDepartement/text()}</departement><total>{$total}</total></resultat>";

    //Moyenne du salaire des employes agés entre 20 et 30 ans.
    private String XQUERY1 = "for $employes in /chine/employes/employe "+
                             "let $e := $employes[contains(dateDeNaissance,'1993-')]/@idEmploye " +
                             "let $salaire := /chine/contratsDureeIndeterminee/contrat[@idEmploye=$e/@idEmploye] "+
                             "return <resultat> <employe> {$salaire/@idEmploye} </employe> <salaire> {$salaire/salaire} </salaire> </resultat>";

    //La liste des noms,fonctions, salaires des employes qui ont un salaire plus que 3500
    private String XQUERY2 = "for $e in /chine/employes/employe\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "where $c/salaire > 3500\n" +
            "return <resultat> <nom> {$e/nom} </nom> <fonction> {$p/libelle}  </fonction> <salaire> {$c/salaire} </salaire> </resultat>";

    //Le poste le plus performant de chaque departement avec le nom de l'employé et sa note
    private String XQUERY3 = "for $departement in /chine/departements/departement\n" +
            "let $employe := /chine/employes/employe[@idDepartement = $departement/@idDepartement]\n" +
            "let $performances := /chine/performances/performance\n" +
            "let $maxNote := max($performances[@idEmploye = $employe/@idEmploye]/notePerformance)\n" +
            "for $employeP in $employe[@idEmploye = /chine/performances/performance[notePerformance = $maxNote]/@idEmploye]\n" +
            "let $poste := /chine/postes/poste[@idPoste = $employeP/@idPoste]\n" +
            "return <resultat> <departement>{$departement/nomDepartement/text()} </departement><nom> {$employeP/nom/text()} </nom> <poste> {$poste/libelle/text()} </poste> <notePerformance> {$maxNote} </notePerformance> </resultat>";

    //le nombre d'absences dans le departement de ventes et marketing avec motif "Formation"
    private String XQUERY4 = "let $nombre_absences :=\n" +
            "count(for $a in /chine/absences/absence\n" +
            "let $e := /chine/employes/employe[@idEmploye = $a/@idEmploye]\n" +
            "let $d := /chine/departements/departement[@idDepartement = $e/@idDepartement]\n" +
            "where $d/nomDepartement = 'Ventes et marketing' and $a/motifAbsence = 'Formation'\n" +
            "return $a)\n" +
            "return <resultat><absences> {$nombre_absences} </absences></resultat>";

    //les employés qui ont comme moyenne de performance une note <= 12,avec leut noms, leur note, leur departement, et leur poste
    private String XQUERY5 = "for $emp in /chine/employes/employe\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $emp/@idEmploye]/notePerformance\n" +
            "where avg($perf) <= 12\n" +
            "return <resultat><nom> {$emp/nom} </nom> <poste> {/chine/postes/poste[@idPoste = $emp/@idPoste]/libelle} </poste> <departement> {/chine/departements/departement[@idDepartement = $emp/@idDepartement]/nomDepartement} </departement> <moyennePerformance> {avg($perf)} </moyennePerformance></resultat> ";

    //la liste des postes libres classés par ordre croissant les salaires de bases
    private String XQUERY6 = "for $p in /chine/postes/poste[occupation = 'false']\n" +
            "order by $p/salaireBase ascending\n" +
            "return <resultat><poste> {$p/libelle}</poste> <salaire> {$p/salaireBase} </salaire><nombreHeures> {$p/nombreHeuresParSemaine} </nombreHeures></resultat>";

    //l'impact des formations sur les performances
    private String XQUERY7 = "for $sf in /chine/seFormes/seForme\n" +
            "let $f := /chine/formations/formation[@idFormation = $sf/@idFormation]\n" +
            "let $e := /chine/employes/employe[@idEmploye = $sf/@idEmploye]\n" +
            "let $pf1 := /chine/performances/performance[@idEmploye = $e/@idEmploye and contains(datePerformance, '2021-')]\n" +
            "let $pf2 := /chine/performances/performance[@idEmploye = $e/@idEmploye and contains(datePerformance, '2022-')]\n" +
            "where $pf2/notePerformance > $pf1/notePerformance\n" +
            "order by $e/nom\n" +
            "return <resultat> <nom>{$e/nom}</nom><notePerformanceAvant> {$pf1/notePerformance}</notePerformanceAvant> <datePerformanceAvant>{$pf1/datePerformance}</datePerformanceAvant> <notePerformanceApres>{$pf2/notePerformance}</notePerformanceApres><datePerformanceApres> {$pf2/datePerformance}</datePerformanceApres><typeFormation> {$f/typeFormation}</typeFormation><dateDebutFormation> {$sf/dateDebutFormation}</dateDebutFormation></resultat> ";

    //afficher le nom, la derniere performance, et le poste de tous les employés avec leur augmentation de salaire (différence entre salaire de base du poste et le salaire actuel de l'employé)
    private String XQUERY8 = "for $e in /chine/employes/employe\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $e/@idEmploye][contains(datePerformance,'2022-')]\n" +
            "let $augmentation := (xs:integer($c/salaire) - xs:integer($p/salaireBase))\n" +
            "return <resultat> <nom> {$e/nom} </nom> <notePerformance> {$perf/notePerformance} </notePerformance> <poste> {$p/libelle} </poste> <augmentation> {$augmentation} </augmentation> </resultat>";

    //tous les employés qui on une augmentation de salaire <= 700, et qui on une performance >= 15
    private String XQUERY9 = "for $e in /chine/employes/employe\n" +
            "let $p := /chine/postes/poste[@idPoste = $e/@idPoste]\n" +
            "let $c := /chine/contratsDureeIndeterminee/contrat[@idEmploye = $e/@idEmploye]\n" +
            "let $perf := /chine/performances/performance[@idEmploye = $e/@idEmploye][contains(datePerformance, '2022-')]\n" +
            "let $augmentation := xs:integer($c/salaire) - xs:integer($p/salaireBase)\n" +
            "where $augmentation <= 700 and $perf/notePerformance >= 15\n" +
            "return <resultat> <nom>{$e/nom} </nom> <prenom>{$e/prenom} </prenom> <poste>{$p/libelle} </poste> <departement> {/chine/department/departement[@idDepartement=$e/@idDepartement]/nomDepartement} </departement><augmentation> {$augmentation} </augmentation> <notePerformance> {$perf/notePerformance} </notePerformance>  </resultat>";

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
