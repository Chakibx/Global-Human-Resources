--le cout total des formations de l'annee derniere
SELECT D.nom_departement, SUM(F.cout_formation) as CoutTotalFormation
FROM departement D
         JOIN employe E ON E.id_departement = D.id_departement
         JOIN se_forme SF ON SF.id_employe = E.id_employe
         JOIN formation F ON F.id_formation = SF.id_formation
WHERE EXTRACT(YEAR FROM SF.date_debut_formation) = EXTRACT(YEAR FROM CURRENT_DATE) - 1
GROUP BY D.nom_departement;

--Moyenne du salaire des employes ages entre 20 et 30 ans.
SELECT AVG(c.salaire) AS moyenne_salaires
FROM employe e
         JOIN contrat_duree_indeterminee c ON e.id_employe = c.id_employe
WHERE (CURRENT_DATE - e.date_de_naissance) BETWEEN 7300 AND 10950;
