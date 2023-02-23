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

--La liste des noms,fonctions, salaires des employes qui ont un salaire plus que 3500
SELECT e.nom, p.libelle, cdi.salaire, cdi.date_debut_contrat
FROM employe e
JOIN contrat_duree_indeterminee cdi ON e.id_employe = cdi.id_employe
JOIN poste p ON e.id_poste = p.id_poste
WHERE cdi.salaire > 3500;

-- Le poste le plus performant de chaque departement avec le nom de l'emplyé et sa note --
SELECT d.nom_departement, p.libelle, pf.note_performance, e.nom
FROM departement d
JOIN employe e ON e.id_departement = d.id_departement
JOIN performance pf ON pf.id_employe = e.id_employe
JOIN poste p ON e.id_poste = p.id_poste
WHERE (e.id_departement, pf.note_performance) IN (SELECT e2.id_departement, MAX(pf2.note_performance)
                                                  FROM performance pf2
                                                           JOIN employe e2 ON e2.id_employe = pf2.id_employe
                                                  GROUP BY e2.id_departement)
GROUP BY e.nom, d.nom_departement, p.libelle,pf.note_performance;

-- le nombre d'absences dans le departement de ventes et marketing avec motif "Formation"
SELECT COUNT(*) AS nombre_absences
FROM absence a
JOIN employe e ON a.id_employe = e.id_employe
JOIN departement d ON e.id_departement = d.id_departement
WHERE d.nom_departement = 'Ventes et marketing' AND a.motif_absence = 'Formation';

-- les employés qui ont comme moyenne de performance une note <= 12,avec leut noms, leur note, leur departement, et leur poste
SELECT e.nom, p.libelle AS poste, d.nom_departement AS departement, pf.note_performance
FROM employe e
JOIN performance pf ON e.id_employe = pf.id_employe
JOIN poste p ON e.id_poste = p.id_poste
JOIN departement d ON e.id_departement = d.id_departement
GROUP BY e.nom, p.libelle, d.nom_departement, pf.note_performance
HAVING AVG(pf.note_performance) <= 12;

-- l'impact des formations sur les performances
SELECT e.nom, pf1.note_performance as note_avant, pf1.date_performance AS date_note_avant, pf2.note_performance as note_apres,pf2.date_performance AS date_note_apres, f.type_formation, sf.date_debut_formation AS date_formation
FROM se_forme sf
JOIN formation f ON sf.id_formation = f.id_formation
JOIN employe e ON sf.id_employe = e.id_employe
JOIN performance pf1 ON e.id_employe = pf1.id_employe AND pf1.date_performance < sf.date_debut_formation
JOIN performance pf2 ON e.id_employe = pf2.id_employe AND pf2.date_performance > sf.date_debut_formation
WHERE pf2.note_performance > pf1.note_performance
ORDER BY e.nom;


--afficher le nom, la derniere performance, et le poste de tous les employé avec leur augmentation de salaire (différence entre salaire de base du poste et le salaire actuel de l'employé)--
SELECT e.nom, p.libelle, MAX(cdi.salaire - p.salaire_base) AS augmentation, perf.note_performance
FROM employe e
JOIN poste p ON e.id_poste = p.id_poste
JOIN contrat_duree_indeterminee cdi ON e.id_employe = cdi.id_employe
JOIN performance perf ON e.id_employe = perf.id_employe
WHERE perf.date_performance = (SELECT MAX(date_performance) FROM performance WHERE id_employe = e.id_employe)
GROUP BY e.nom, p.libelle, perf.note_performance
ORDER BY augmentation DESC;

--tous les employés qui on une augmentation de salaire <= 700, et qui on une performance >= 15
SELECT e.nom, e.prenom, p.libelle AS poste, d.nom_departement AS departement, (c.salaire - p.salaire_base) AS augmentation,perf.note_performance AS performance
FROM employe e
JOIN poste p ON e.id_poste = p.id_poste
JOIN contrat_duree_indeterminee c ON e.id_employe = c.id_employe
JOIN departement d ON e.id_departement = d.id_departement
JOIN performance perf ON e.id_employe = perf.id_employe
WHERE (c.salaire - p.salaire_base) < 1000
  AND perf.date_performance = (SELECT MAX(date_performance) FROM performance WHERE id_employe = e.id_employe)
  AND perf.note_performance >= 15;

