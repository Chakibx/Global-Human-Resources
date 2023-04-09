package src.Gui;
import src.*;
import net.sf.saxon.s9api.SaxonApiException;
import src.Chine.Dom;
import src.France.PostgresqlQueryExecution;
import src.Mediator.Mediator;
import src.QueryClasses.*;
import src.Usa.MysqlQueryExecution;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gui_2 implements ActionListener {
    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 900;

    private static final int TABLE_WIDTH = 20;
    private static final int TABLE_HEIGHT = 350;

    private static final int QUERY_LABEL_WIDTH = 20;
    private static final int QUERY_LABEL_HEIGHT = 100;

    private static final int EXECUTION_STEPS_WIDTH = 20;
    private static final int EXECUTION_STEPS_HEIGHT = 450;

    private JFrame frame;
    private JComboBox<Integer> queryComboBox;
    private JTextArea outputArea;

    private int chinaSelected = 0;
    private int franceSelected = 0;
    private int usaSelected = 0;

    private JTable table;
    private DefaultTableModel model = new DefaultTableModel();

    private Font font = new Font("Arial", Font.PLAIN, 20);
    private Font QueryLabel_font = new Font("Arial", Font.PLAIN, 16);
    private Font font_tab = new Font("Liberation-Serif", Font.PLAIN, 19);

    public Gui_2() {

        // Set up the JFrame
        frame = new JFrame("Query Executor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Ajouter les boutons de sélection
        JCheckBox chinaButton = new JCheckBox("Chine");
        JCheckBox franceButton = new JCheckBox("France");
        JCheckBox usaButton = new JCheckBox("USA");
        chinaButton.setFont(font);
        franceButton.setFont(font);
        usaButton.setFont(font);

        // Creating the selection pannel items
        //Label
        JLabel queryLabel = new JLabel("Choose the query!");
        queryLabel.setFont(font);
        //Query combo Box
        queryComboBox = new JComboBox<Integer>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        queryComboBox.setFont(font);
        queryComboBox.setPreferredSize(new Dimension(10, 10));
        //Execution button
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        executeButton.setFont(font);


        //creating the selection pannel
        JPanel selectionPanel = new JPanel(new BorderLayout());
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        selectionPanel.setPreferredSize(new Dimension(250, 300));

        //adding the pannel items to selectionPanel
        //Query label
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(queryLabel);
        //ComboBox
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(queryComboBox);
        //Check box buttons
        selectionPanel.add(Box.createVerticalGlue());
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(chinaButton);
        selectionPanel.add(franceButton);
        selectionPanel.add(usaButton);
        //Execution button
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(executeButton);
        //Empty space at the bottom to equalise
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 1000)));

        //Creating central panel items
        //query label
        JLabel query_label = new JLabel("Queries will be displayed here !");
        query_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        query_label.setAlignmentY(Component.CENTER_ALIGNMENT);
        query_label.setHorizontalAlignment(JLabel.CENTER);
        Dimension d = new Dimension(QUERY_LABEL_WIDTH, QUERY_LABEL_HEIGHT);
        query_label.setPreferredSize(d);

        //the table containing results
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        table.setRowHeight(50);
        DefaultTableModel model = new DefaultTableModel();

        //Execution steps
        JTextPane executionSteps = new JTextPane();
        executionSteps.setEditable(false);
        executionSteps.setPreferredSize(new Dimension(EXECUTION_STEPS_WIDTH, EXECUTION_STEPS_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(executionSteps);
        scrollPane.setPreferredSize(new Dimension(EXECUTION_STEPS_WIDTH, EXECUTION_STEPS_HEIGHT));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // Add the components to the center Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JScrollPane(query_label), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add the centerPanel and selectionPanel to the whole JFrame
        frame.add(selectionPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Display the JFrame
        frame.setVisible(true);

        // Adding events listeners to buttons
        ItemListener selectionListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Mettre à jour les variables de sélection
                chinaSelected = chinaButton.isSelected() ? 1 : 0;
                franceSelected = franceButton.isSelected() ? 1 : 0;
                usaSelected = usaButton.isSelected() ? 1 : 0;
            }
        };
        chinaButton.addItemListener(selectionListener);
        franceButton.addItemListener(selectionListener);
        usaButton.addItemListener(selectionListener);

        // Adding action listener to the execution button
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get query number from ComboBox choice
                int queryNumber = (int)queryComboBox.getSelectedItem();

                //executing the query and passing the model
                executeQuery(queryNumber,query_label,executionSteps);
            }
        });
    }

    private void executeQuery(int queryNumber, JLabel query_label, JTextPane executionSetps) {

        switch (queryNumber) {
            case 0:
                try {
                    ArrayList<Query_0> result;
                    result = Mediator.mediate_query_0(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("le cout total des formations de l'annee derniere\n");
                    query_label.setFont(QueryLabel_font);

                    model.addColumn("Departement");
                    model.addColumn("Cout total");
                    for (Query_0 obj : result) {
                        model.addRow(new Object[]{obj.getNom(), obj.getCoutTotal()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                    StringBuilder labelText = new StringBuilder();

                    if (usaSelected ==1) {
                        ArrayList<Query_0> liste0 = new ArrayList<Query_0>();
                        Query_0 q1 = new Query_0("Ventes et marketing",0);
                        Query_0 q2 = new Query_0("Ressources humaines",0);
                        Query_0 q3 = new Query_0("IT",0);
                        Query_0 q4 = new Query_0("Finance",0);
                        liste0.add(q1);
                        liste0.add(q2);
                        liste0.add(q3);
                        liste0.add(q4);
                        liste0 = MysqlQueryExecution.Execute_query_0(liste0);
                        labelText.append("<br><h1> EXECUTION SUR LA BASE USA </h1><br>");
                        for (Query_0 obj : liste0) {
                            labelText.append(obj.toString()).append("<br>");
                        }
                        executionSetps.setText("<html>" + labelText.toString() + "</html>");
                    }
                    if (franceSelected==1) {
                        ArrayList<Query_0> liste0 = new ArrayList<Query_0>();
                        Query_0 q1 = new Query_0("Ventes et marketing",0);
                        Query_0 q2 = new Query_0("Ressources humaines",0);
                        Query_0 q3 = new Query_0("IT",0);
                        Query_0 q4 = new Query_0("Finance",0);
                        liste0.add(q1);
                        liste0.add(q2);
                        liste0.add(q3);
                        liste0.add(q4);
                        liste0 = PostgresqlQueryExecution.Execute_query_0(liste0);
                        labelText.append("<br><h1> EXECUTION SUR LA BASE France</h1><br>");
                        for (Query_0 obj : liste0) {
                            labelText.append(obj.toString()).append("<br>");
                        }
                        executionSetps.setText("<html>" + labelText.toString() + "</html>");
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 1:
                try {
                    double result;
                    result = Mediator.mediate_query_1();
                    // Display the results in the output area
                    query_label.setText("Moyenne du salaire des employes agés entre 20 et 30 ans.\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Moyenne");
                    model.addColumn("Fonction");
                    model.addColumn("Salaire");
                    model.addColumn("Pays");

                    model.addRow(new Object[]{result});
                    table.setModel(model);
                    table.setFont(font);
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 2:
                try {

                    ArrayList<Query_2> result;
                    result = Mediator.mediate_query_2(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("La liste des noms,fonctions, salaires des employes\n qui ont un salaire plus que 3500\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Fonction");
                    model.addColumn("Salaire");
                    model.addColumn("Pays");
                    for (Query_2 obj : result) {
                        model.addRow(new Object[]{obj.getNom(), obj.getFonction(),obj.getSalaire(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                    // Création des chaînes de caractères pour chaque pays
                    StringBuilder usaContent = new StringBuilder();
                    StringBuilder franceContent = new StringBuilder();
                    StringBuilder chinaContent = new StringBuilder();
                    int etapes = 0;
                    // Récupération des résultats pour chaque pays
                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2USA = MysqlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        usaContent.append("<b> MISE À JOUR N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE USA</b><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2France = PostgresqlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        franceContent.append("<b>MISE À JOUR N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE FRANCE</b><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2China = Dom.Execute_query_2(new ArrayList<Query_2>());
                        chinaContent.append("<b>MISE À JOUR N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE CHINE</b><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }

// Concaténation des résultats de chaque pays dans une seule chaîne de caractères
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

// Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h1>ETAPES D'OBTENTION DES RÉSULTATS : </h1><br> <h2>Cette méthode nous permet de récupérer les résultats des différentes bases de données et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête. Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples.</h2>" + content + "</html>");


                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);}
                break;
            case 3:
                try {
                    ArrayList<Query_3> result;
                    result = Mediator.mediate_query_3(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Le poste le plus performant de chaque departement\n avec le nom de l'emplyé et sa note\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Department");
                    model.addColumn("Poste");
                    model.addColumn("Nom");
                    model.addColumn("Performance");
                    model.addColumn("Pays");
                    for (Query_3 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getDepartement(), obj.getPoste(),obj.getNom(),obj.getNotePerformance(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 4:
                try {
                    ArrayList<Query_4> result;
                    result = Mediator.mediate_query_4(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Le nombre d'absences dans le departement de ventes et marketing\n avec motif 'Formation'\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nombre Absences");
                    model.addColumn("Pays");
                    for (Query_4 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getNombreAbsences(), obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 5:
                try {
                    ArrayList<Query_5> result;
                    result = Mediator.mediate_query_5(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Les employés qui ont comme moyenne de performance une note <= 12,\n avec leurs noms, leur note, leur departement, et leur poste\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Poste");
                    model.addColumn("Departement");
                    model.addColumn("Moyenne Performance");
                    model.addColumn("Pays");
                    for (Query_5 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getNom(), obj.getPoste(),obj.getDepartement(),obj.getMoyennePerformance(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 6:
                try {
                    ArrayList<Query_6> result;
                    result = Mediator.mediate_query_6(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("la liste des postes libres \n classé par ordre croissant des salaires de bases\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Poste");
                    model.addColumn("Salaire Base");
                    model.addColumn("NB Heures par Semaine");
                    model.addColumn("Pays");
                    for (Query_6 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getPoste(),obj.getSalaireBase(),obj.getNombreHeuresParSemaine(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 7:
                try {
                    ArrayList<Query_7> result;
                    result = Mediator.mediate_query_7(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("L'impact des formations sur les performances\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Note Avant");
                    model.addColumn("Date Note Avant");
                    model.addColumn("Note Apres");
                    model.addColumn("Date Note Apres");
                    model.addColumn("Type Formation");
                    model.addColumn("Date Debut Formation");
                    model.addColumn("Pays");
                    for (Query_7 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getNom(), obj.getNoteAvant(),obj.getDateNoteAvant(),obj.getNoteApres(),obj.getDateNoteApres(),obj.getTypeFormation(),obj.getDateDebutFormation(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 8:
                try {
                    ArrayList<Query_8> result;
                    result = Mediator.mediate_query_8(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Le nom, la derniere performance,\n et le poste de tous les employé avec leur augmentation de salaire \n (différence entre salaire de base du poste et le salaire actuel de l'employé)\n\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Derniere Performance");
                    model.addColumn("Poste");
                    model.addColumn("Augmentation");
                    model.addColumn("Pays");
                    for (Query_8 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getNom(), obj.getDernierePerformance(),obj.getPoste(),obj.getAugmentation(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 9:
                try {
                    ArrayList<Query_9> result;
                    result = Mediator.mediate_query_9(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Tous les employés qui on une augmentation de salaire <= 700\n et qui on une performance >= 15\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Derniere Performance");
                    model.addColumn("Poste");
                    model.addColumn("Augmentation");
                    model.addColumn("Pays");
                    for (Query_9 obj : result) {
                        //query_label.append(obj.toString() + "\n");
                        model.addRow(new Object[]{obj.getNom(), obj.getPerformance(),obj.getPoste(),obj.getAugmentation(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            default:
                query_label.setText("Invalid query number: " + queryNumber);
                query_label.setFont(font);
                return;
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}


