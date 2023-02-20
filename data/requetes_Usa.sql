--le cout total des formations de l'annee derniere
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
