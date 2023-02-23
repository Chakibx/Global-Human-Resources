package src.UsaDAO;

public class MysqlQuery {
    private String MSQL1 = "SELECT d.nomDepartement, SUM(f.coutFormation) AS total_cout_formation FROM Departement d INNER JOIN Employe e ON d.idDepartement = e.idDepartement INNER JOIN SeForme s ON e.idEmploye = s.idEmploye INNER JOIN Formation f ON s.idFormation = f.idFormation WHERE YEAR(s.dateDebutFormation) = YEAR(CURDATE()) - 1 GROUP BY d.nomDepartement;";
    private String MSQL2 = "SELECT AVG(c.salaire) AS moyenne_salaires FROM Employe e JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye WHERE DATEDIFF( CURDATE(),e.dateDeNaissance) BETWEEN 7300 AND 10950;";
    private String MSQL3 = "SELECT e.nom, p.libelle, cdi.salaire, cdi.dateDebutContrat FROM Employe e JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye JOIN Poste p ON e.idPoste = p.idPoste WHERE cdi.salaire > 3500;";
    private String MSQL4 = "SELECT d.nomDepartement, p.libelle,e.nom, pf.notePerformance FROM Departement d JOIN Employe e ON e.idDepartement = d.idDepartement JOIN Performance pf ON pf.idEmploye = e.idEmploye JOIN Poste p ON e.idPoste = p.idPoste WHERE pf.notePerformance = (SELECT MAX(pf2.notePerformance) FROM Performance pf2 JOIN Employe e2 ON e2.idEmploye = pf2.idEmploye WHERE e2.idDepartement = e.idDepartement) GROUP BY d.nomDepartement;";
    private String MSQL5 = "SELECT COUNT(*) as nb_absences FROM Absence a JOIN Employe e ON a.idEmploye = e.idEmploye JOIN Departement d ON e.idDepartement = d.idDepartement WHERE d.nomDepartement = 'Ventes et marketing' AND a.motifAbsence = 'Formation';";
    private String MSQL6 = "SELECT e.nom, p.libelle AS poste, d.nomDepartement AS departement ,pf.notePerformance FROM Employe e JOIN Performance pf ON e.idEmploye = pf.idEmploye JOIN Poste p ON e.idPoste = p.idPoste JOIN Departement d ON e.idDepartement = d.idDepartement GROUP BY e.nom, p.libelle, d.nomDepartement HAVING AVG(pf.notePerformance) <= 12;";
    private String MSQL7 = "SELECT p.libelle, p.salaireBase, p.nombreHeuresParSemaine FROM Poste p WHERE p.occupation = FALSE ORDER BY p.salaireBase ASC;";
    private String MSQL8 = "SELECT e.nom, pf1.notePerformance as note_avant, pf1.datePerformance AS date_note_avant, pf2.notePerformance as note_apres,pf2.datePerformance AS date_note_apres, f.typeFormation, sf.dateDebutFormation AS date_formation FROM SeForme sf JOIN Formation f ON sf.idFormation = f.idFormation JOIN Employe e ON sf.idEmploye = e.idEmploye JOIN Performance pf1 ON e.idEmploye = pf1.idEmploye AND pf1.datePerformance < sf.dateDebutFormation JOIN Performance pf2 ON e.idEmploye = pf2.idEmploye AND pf2.datePerformance > sf.dateDebutFormation WHERE pf2.notePerformance > pf1.notePerformance ORDER BY e.nom;";
    private String MSQL9 = "SELECT e.nom, p.libelle, MAX(cdi.salaire - p.salaireBase) AS augmentation, perf.notePerformance FROM Employe e JOIN Poste p ON e.idPoste = p.idPoste JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye JOIN Performance perf ON e.idEmploye = perf.idEmploye WHERE perf.datePerformance = (SELECT MAX(datePerformance) FROM Performance WHERE idEmploye = e.idEmploye) GROUP BY e.nom, p.libelle, perf.notePerformance ORDER BY augmentation DESC;";
    private String MSQL10 = "SELECT e.nom, e.prenom, p.libelle AS poste, d.nomDepartement AS departement, (c.salaire - p.salaireBase) AS augmentation,perf.notePerformance AS performance FROM Employe e JOIN Poste p ON e.idPoste = p.idPoste JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye JOIN Departement d ON e.idDepartement = d.idDepartement JOIN Performance perf ON e.idEmploye = perf.idEmploye WHERE (c.salaire - p.salaireBase) < 500 AND perf.datePerformance = (SELECT MAX(datePerformance) FROM Performance WHERE idEmploye = e.idEmploye) AND perf.notePerformance >= 15;";

    public String getMSQL1() {
        return MSQL1;
    }

    public String getMSQL2() {
        return MSQL2;
    }

    public String getMSQL3() {
        return MSQL3;
    }

    public String getMSQL4() {
        return MSQL4;
    }

    public String getMSQL5() {
        return MSQL5;
    }

    public String getMSQL6() {
        return MSQL6;
    }

    public String getMSQL7() {
        return MSQL7;
    }

    public String getMSQL8() {
        return MSQL8;
    }

    public String getMSQL9() {
        return MSQL9;
    }

    public String getMSQL10() {
        return MSQL10;
    }
}
