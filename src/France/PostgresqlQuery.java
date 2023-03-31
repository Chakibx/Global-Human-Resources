package src.France;
import java.util.ArrayList;

public class PostgresqlQuery {
    private String PSQL0 = "SELECT D.nom_departement, SUM(F.cout_formation) as CoutTotalFormation FROM departement D JOIN employe E ON E.id_departement = D.id_departement JOIN se_forme SF ON SF.id_employe = E.id_employe JOIN formation F ON F.id_formation = SF.id_formation WHERE EXTRACT(YEAR FROM SF.date_debut_formation) = EXTRACT(YEAR FROM CURRENT_DATE) - 1 GROUP BY D.nom_departement;";
    private String PSQL1 = "SELECT AVG(c.salaire) AS moyenne_salaires FROM employe e JOIN contrat_duree_indeterminee c ON e.id_employe = c.id_employe WHERE (CURRENT_DATE - e.date_de_naissance) BETWEEN 9131 AND 10950;";
    private String PSQL2 = "SELECT e.nom, p.libelle, cdi.salaire, cdi.date_debut_contrat FROM employe e JOIN contrat_duree_indeterminee cdi ON e.id_employe = cdi.id_employe JOIN poste p ON e.id_poste = p.id_poste WHERE cdi.salaire > 3500;";
    private String PSQL3 = "SELECT d.nom_departement, p.libelle, pf.note_performance, e.nom FROM departement d JOIN employe e ON e.id_departement = d.id_departement JOIN performance pf ON pf.id_employe = e.id_employe JOIN poste p ON e.id_poste = p.id_poste WHERE (e.id_departement, pf.note_performance) IN (SELECT e2.id_departement, MAX(pf2.note_performance) FROM performance pf2 JOIN employe e2 ON e2.id_employe = pf2.id_employe GROUP BY e2.id_departement) GROUP BY e.nom, d.nom_departement, p.libelle,pf.note_performance;";
    private String PSQL4 = "SELECT COUNT(*) AS nombre_absences FROM absence a JOIN employe e ON a.id_employe = e.id_employe JOIN departement d ON e.id_departement = d.id_departement WHERE d.nom_departement = 'Ventes et marketing' AND a.motif_absence = 'Formation'";
    private String PSQL5 = "SELECT e.nom, p.libelle AS poste, d.nom_departement AS departement, pf.note_performance FROM employe e JOIN performance pf ON e.id_employe = pf.id_employe JOIN poste p ON e.id_poste = p.id_poste JOIN departement d ON e.id_departement = d.id_departement GROUP BY e.nom, p.libelle, d.nom_departement, pf.note_performance HAVING AVG(pf.note_performance) <= 12;";
    private String PSQL6 = "SELECT p.libelle, p.salaire_base, p.nombre_heures_par_semaine FROM poste p WHERE p.occupation = FALSE ORDER BY p.salaire_base ASC;";
    private String PSQL7 = "SELECT e.nom, pf1.note_performance as note_avant, pf1.date_performance AS date_note_avant, pf2.note_performance as note_apres,pf2.date_performance AS date_note_apres, f.type_formation, sf.date_debut_formation AS date_formation FROM se_forme sf JOIN formation f ON sf.id_formation = f.id_formation JOIN employe e ON sf.id_employe = e.id_employe JOIN performance pf1 ON e.id_employe = pf1.id_employe AND pf1.date_performance < sf.date_debut_formation JOIN performance pf2 ON e.id_employe = pf2.id_employe AND pf2.date_performance > sf.date_debut_formation WHERE pf2.note_performance > pf1.note_performance ORDER BY e.nom;";
    private String PSQL8 = "SELECT e.nom, p.libelle, MAX(cdi.salaire - p.salaire_base) AS augmentation, perf.note_performance FROM employe e JOIN poste p ON e.id_poste = p.id_poste JOIN contrat_duree_indeterminee cdi ON e.id_employe = cdi.id_employe JOIN performance perf ON e.id_employe = perf.id_employe WHERE perf.date_performance = (SELECT MAX(date_performance) FROM performance WHERE id_employe = e.id_employe) GROUP BY e.nom, p.libelle, perf.note_performance ORDER BY augmentation DESC;";
    private String PSQL9 = "SELECT e.nom, e.prenom, p.libelle AS poste, d.nom_departement AS departement, (c.salaire - p.salaire_base) AS augmentation,perf.note_performance AS performance FROM employe e JOIN poste p ON e.id_poste = p.id_poste JOIN contrat_duree_indeterminee c ON e.id_employe = c.id_employe JOIN departement d ON e.id_departement = d.id_departement JOIN performance perf ON e.id_employe = perf.id_employe WHERE (c.salaire - p.salaire_base) < 1000 AND perf.date_performance = (SELECT MAX(date_performance) FROM performance WHERE id_employe = e.id_employe) AND perf.note_performance >= 15;";

    private ArrayList<String> QueryList = new ArrayList<String>();

    public PostgresqlQuery() {
        QueryList.add(this.PSQL0);
        QueryList.add(this.PSQL1);
        QueryList.add(this.PSQL2);
        QueryList.add(this.PSQL3);
        QueryList.add(this.PSQL4);
        QueryList.add(this.PSQL5);
        QueryList.add(this.PSQL6);
        QueryList.add(this.PSQL7);
        QueryList.add(this.PSQL8);
        QueryList.add(this.PSQL9);
    }

    public String GetQuery(Integer index ){
        return QueryList.get(index);
    }
}
