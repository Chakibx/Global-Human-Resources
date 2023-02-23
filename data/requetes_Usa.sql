--le cout total des formations de l'annee derniere(2)
SELECT d.nomDepartement, SUM(f.coutFormation) AS total_cout_formation
FROM Departement d
INNER JOIN Employe e ON d.idDepartement = e.idDepartement
INNER JOIN SeForme s ON e.idEmploye = s.idEmploye
INNER JOIN Formation f ON s.idFormation = f.idFormation
WHERE YEAR(s.dateDebutFormation) = YEAR(CURDATE()) - 1
GROUP BY d.nomDepartement;

--Moyenne du salaire des employes ages entre 20 et 30 ans.
SELECT AVG(c.salaire) AS moyenne_salaires
FROM Employe e
JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye
WHERE DATEDIFF( CURDATE(),e.dateDeNaissance) BETWEEN 7300 AND 10950;

--La liste des noms,fonctions, salaires des employes qui ont un salaire plus que 3500
SELECT e.nom, p.libelle, cdi.salaire, cdi.dateDebutContrat
FROM Employe e
JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye
JOIN Poste p ON e.idPoste = p.idPoste
WHERE cdi.salaire > 3500;



--Le poste le plus performant de chaque departement avec le nom de l'emplyes et sa note (3)
SELECT d.nomDepartement, p.libelle,e.nom, pf.notePerformance
FROM Departement d
JOIN Employe e ON e.idDepartement = d.idDepartement
JOIN Performance pf ON pf.idEmploye = e.idEmploye
JOIN Poste p ON e.idPoste = p.idPoste
WHERE pf.notePerformance = (SELECT MAX(pf2.notePerformance)
                            FROM Performance pf2
                            JOIN Employe e2 ON e2.idEmploye = pf2.idEmploye
                            WHERE e2.idDepartement = e.idDepartement)
GROUP BY d.nomDepartement;


--le nombre d'absence dans le departement ventes et marketing avec motif formation
SELECT COUNT(*) as nb_absences
FROM Absence a
JOIN Employe e ON a.idEmploye = e.idEmploye
JOIN Departement d ON e.idDepartement = d.idDepartement
WHERE d.nomDepartement = 'Ventes et marketing' AND a.motifAbsence = 'Formation';

--les employes avec leur poste et departement qui possede comme moyenne de performance <=12 pour effectuer des formations
SELECT e.nom, p.libelle AS poste, d.nomDepartement AS departement ,pf.notePerformance
FROM Employe e
JOIN Performance pf ON e.idEmploye = pf.idEmploye
JOIN Poste p ON e.idPoste = p.idPoste
JOIN Departement d ON e.idDepartement = d.idDepartement
GROUP BY e.nom, p.libelle, d.nomDepartement
HAVING AVG(pf.notePerformance) <= 12;

--la liste des postes libres classé par ordre croissant des salaires de bases
SELECT p.libelle, p.salaireBase, p.nombreHeuresParSemaine
FROM Poste p
WHERE p.occupation = FALSE
ORDER BY p.salaireBase ASC;

--l'impact des formations sur les performances
SELECT e.nom, pf1.notePerformance as note_avant, pf1.datePerformance AS date_note_avant, pf2.notePerformance as note_apres,pf2.datePerformance AS date_note_apres, f.typeFormation, sf.dateDebutFormation AS date_formation
FROM SeForme sf
JOIN Formation f ON sf.idFormation = f.idFormation
JOIN Employe e ON sf.idEmploye = e.idEmploye
JOIN Performance pf1 ON e.idEmploye = pf1.idEmploye AND pf1.datePerformance < sf.dateDebutFormation
JOIN Performance pf2 ON e.idEmploye = pf2.idEmploye AND pf2.datePerformance > sf.dateDebutFormation
WHERE pf2.notePerformance > pf1.notePerformance
ORDER BY e.nom;


--le nom, la derniere performance, et le poste de l'employé qui a eu la plus grosse augmentation de salaire

SELECT e.nom, p.libelle, MAX(cdi.salaire - p.salaireBase) AS augmentation, perf.notePerformance
FROM Employe e
JOIN Poste p ON e.idPoste = p.idPoste
JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye
JOIN Performance perf ON e.idEmploye = perf.idEmploye
WHERE perf.datePerformance = (SELECT MAX(datePerformance) FROM Performance WHERE idEmploye = e.idEmploye)
GROUP BY e.nom, p.libelle, perf.notePerformance
ORDER BY augmentation DESC;

--tous les employes qui ont une aaugmentation de salaire <=700 et une performance <=15
SELECT e.nom, e.prenom, p.libelle AS poste, d.nomDepartement AS departement, (c.salaire - p.salaireBase) AS augmentation,perf.notePerformance AS performance
FROM Employe e
JOIN Poste p ON e.idPoste = p.idPoste
JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye
JOIN Departement d ON e.idDepartement = d.idDepartement
JOIN Performance perf ON e.idEmploye = perf.idEmploye
WHERE (c.salaire - p.salaireBase) < 500
AND perf.datePerformance = (SELECT MAX(datePerformance) FROM Performance WHERE idEmploye = e.idEmploye)
AND perf.notePerformance >= 15;

--