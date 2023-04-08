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
    private static final int TABLE_WIDTH = 1300;
    private static final int TABLE_HEIGHT = 500;
    private JFrame frame;
    private JComboBox<Integer> queryComboBox;
    private JTextArea outputArea;
    private int chinaSelected = 0;
    private int franceSelected = 0;
    private int usaSelected = 0;
    private JTable table;
    Font font = new Font("Arial", Font.PLAIN, 20);
    Font QueryLabel_font = new Font("Arial", Font.PLAIN, 16);

    public Gui_2() {


        table = new JTable();

// Set up the JFrame
        // Set up the JFrame
        frame = new JFrame("Query Executor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 900);

// Ajouter les boutons de sélection
        JCheckBox chinaButton = new JCheckBox("Chine");
        JCheckBox franceButton = new JCheckBox("France");
        JCheckBox usaButton = new JCheckBox("USA");

        // Create the input and output components
        JLabel queryLabel = new JLabel("Choose the query!");
        queryLabel.setFont(font);

        queryComboBox = new JComboBox<Integer>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        queryComboBox.setFont(font);
        queryComboBox.setPreferredSize(new Dimension(10, 10));

        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        executeButton.setFont(font);

        JPanel selectionPanel = new JPanel(new BorderLayout());
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        selectionPanel.setPreferredSize(new Dimension(250, 300));
        chinaButton.setFont(font);
        franceButton.setFont(font);
        usaButton.setFont(font);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(queryLabel);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(queryComboBox);
        selectionPanel.add(Box.createVerticalGlue());
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(chinaButton);
        selectionPanel.add(franceButton);
        selectionPanel.add(usaButton);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        selectionPanel.add(executeButton);
        selectionPanel.add(Box.createRigidArea(new Dimension(0, 1000)));
        frame.add(selectionPanel, BorderLayout.WEST);

        JLabel outputArea = new JLabel("Queries will be displayed here !");
        outputArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        outputArea.setHorizontalAlignment(JLabel.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(1300, 500));
        table.setRowHeight(50);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 500));

        // Add the components to the JFrame
        JPanel centerPanel = new JPanel(new BorderLayout());
        //
        centerPanel.add(new JScrollPane(outputArea), BorderLayout.NORTH);
        Dimension d = new Dimension(50, 100);
        outputArea.setPreferredSize(d);
        //
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);


        JTextPane executionSteps = new JTextPane();
        executionSteps.setPreferredSize(new Dimension(20, 500));
        JScrollPane scrollPane = new JScrollPane(executionSteps);
        scrollPane.setPreferredSize(new Dimension(20, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Display the JFrame
        frame.setVisible(true);

        // Ajouter un écouteur d'événements pour les boutons
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

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int queryNumber = (int)queryComboBox.getSelectedItem();
                DefaultTableModel model = new DefaultTableModel();
                executeQuery(queryNumber,model,outputArea,executionSteps);
            }
        });
    }

    private void executeQuery(int queryNumber,DefaultTableModel model,JLabel outputArea, JTextPane executionSetps) {
        Font font = new Font("Liberation-Serif", Font.PLAIN, 25);
        Font font_tab = new Font("Liberation-Serif", Font.PLAIN, 19);
        switch (queryNumber) {
            case 0:
                try {
                    ArrayList<Query_0> result;
                    result = Mediator.mediate_query_0(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    outputArea.setText("le cout total des formations de l'annee derniere\n");
                    outputArea.setFont(QueryLabel_font);

                    model.addColumn("Departement");
                    model.addColumn("Cout total");
                    for (Query_0 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("Moyenne du salaire des employes agés entre 20 et 30 ans.\n");
                    outputArea.setFont(QueryLabel_font);
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
                    outputArea.setText("La liste des noms,fonctions, salaires des employes\n qui ont un salaire plus que 3500\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Fonction");
                    model.addColumn("Salaire");
                    model.addColumn("Pays");
                    for (Query_2 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                        usaContent.append("<b> ETAPE N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE USA</b><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2France = PostgresqlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        franceContent.append("<b>ETAPE N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE FRANCE</b><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2China = Dom.Execute_query_2(new ArrayList<Query_2>());
                        chinaContent.append("<b>ETAPE N°"+etapes+": EXECUTION SUR LA BASE DE DONNÉES DE CHINE</b><br>");
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
                    executionSetps.setText("<html>" + "<b>ETAPES D'OBTENTION DES RÉSULTATS : </b><br>" + content + "</html>");


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
                    outputArea.setText("Le poste le plus performant de chaque departement\n avec le nom de l'emplyé et sa note\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Department");
                    model.addColumn("Poste");
                    model.addColumn("Nom");
                    model.addColumn("Performance");
                    model.addColumn("Pays");
                    for (Query_3 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("Le nombre d'absences dans le departement de ventes et marketing\n avec motif 'Formation'\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nombre Absences");
                    model.addColumn("Pays");
                    for (Query_4 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("Les employés qui ont comme moyenne de performance une note <= 12,\n avec leurs noms, leur note, leur departement, et leur poste\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Poste");
                    model.addColumn("Departement");
                    model.addColumn("Moyenne Performance");
                    model.addColumn("Pays");
                    for (Query_5 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("la liste des postes libres \n classé par ordre croissant des salaires de bases\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Poste");
                    model.addColumn("Salaire Base");
                    model.addColumn("NB Heures par Semaine");
                    model.addColumn("Pays");
                    for (Query_6 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("L'impact des formations sur les performances\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Note Avant");
                    model.addColumn("Date Note Avant");
                    model.addColumn("Note Apres");
                    model.addColumn("Date Note Apres");
                    model.addColumn("Type Formation");
                    model.addColumn("Date Debut Formation");
                    model.addColumn("Pays");
                    for (Query_7 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("Le nom, la derniere performance,\n et le poste de tous les employé avec leur augmentation de salaire \n (différence entre salaire de base du poste et le salaire actuel de l'employé)\n\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Derniere Performance");
                    model.addColumn("Poste");
                    model.addColumn("Augmentation");
                    model.addColumn("Pays");
                    for (Query_8 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                    outputArea.setText("Tous les employés qui on une augmentation de salaire <= 700\n et qui on une performance >= 15\n");
                    outputArea.setFont(QueryLabel_font);
                    model.addColumn("Nom");
                    model.addColumn("Derniere Performance");
                    model.addColumn("Poste");
                    model.addColumn("Augmentation");
                    model.addColumn("Pays");
                    for (Query_9 obj : result) {
                        //outputArea.append(obj.toString() + "\n");
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
                outputArea.setText("Invalid query number: " + queryNumber);
                outputArea.setFont(font);
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


