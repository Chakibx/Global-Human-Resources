package src.Gui;
import net.sf.saxon.s9api.SaxonApiException;
import src.Chine.XmlQueryExecution;
import src.France.PostgresqlQueryExecution;
import src.Mediator.Mediator;
import src.QueryClasses.*;
import src.Usa.MysqlQueryExecution;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gui implements ActionListener {
    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);
    private static final int FRAME_WIDTH = 1500;
    private static final int FRAME_HEIGHT = 900;

    private static final int TABLE_WIDTH = 20;
    private static final int TABLE_HEIGHT = 400;

    private static final int QUERY_LABEL_WIDTH = 20;
    private static final int QUERY_LABEL_HEIGHT = 100;

    private static final int EXECUTION_STEPS_WIDTH = 20;
    private static final int EXECUTION_STEPS_HEIGHT = 350;

    private JFrame frame;
    private JComboBox<Integer> queryComboBox;
    private JTextArea outputArea;

    private int chinaSelected = 0;
    private int franceSelected = 0;
    private int usaSelected = 0;

    private JTable table;

    private Font font = new Font("Arial", Font.PLAIN, 20);
    private Font QueryLabel_font = new Font("Arial", Font.PLAIN, 16);
    private Font font_tab = new Font("Arial", Font.PLAIN, 19);

    public Gui() {

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
        table.setModel(model);
        table.setEnabled(false);
        table.setCellSelectionEnabled(false);
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
                // Updating the selection variables.
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
                DefaultTableModel model = new DefaultTableModel();
                //executing the query and passing the model
                executeQuery(queryNumber,model,query_label,executionSteps);
            }
        });
    }

    private void executeQuery(int queryNumber , DefaultTableModel model, JLabel query_label, JTextPane executionSetps) {
        //Steps Area display
        //Creating string variables for each country.
        StringBuilder usaContent = new StringBuilder();
        StringBuilder franceContent = new StringBuilder();
        StringBuilder chinaContent = new StringBuilder();
        //Obtaining and dispalying results for each country.
        int etapes = 0;
        switch (queryNumber) {
            case 0:
                try {
                    ArrayList<Query_0> result;
                    result = Mediator.mediate_query_0(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Le cout total des formations de l'année derniere\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Departement");
                    model.addColumn("Cout total");
                    for (Query_0 obj : result) {
                        model.addRow(new Object[]{obj.getNom(), obj.getCoutTotal()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_0> liste0USA = new ArrayList<Query_0>();
                        Query_0 q1 = new Query_0("Ventes et marketing",0);
                        Query_0 q2 = new Query_0("Ressources humaines",0);
                        Query_0 q3 = new Query_0("IT",0);
                        Query_0 q4 = new Query_0("Finance",0);
                        liste0USA.add(q1);
                        liste0USA.add(q2);
                        liste0USA.add(q3);
                        liste0USA.add(q4);

                        liste0USA = MysqlQueryExecution.Execute_query_0(liste0USA);
                        usaContent.append("<h2> ETAPE N°"+etapes+": execution sur la base de données de USA </h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_0 obj : liste0USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        ArrayList<Query_0> liste0France = new ArrayList<Query_0>();
                        Query_0 q1 = new Query_0("Ventes et marketing",0);
                        Query_0 q2 = new Query_0("Ressources humaines",0);
                        Query_0 q3 = new Query_0("IT",0);
                        Query_0 q4 = new Query_0("Finance",0);
                        liste0France.add(q1);
                        liste0France.add(q2);
                        liste0France.add(q3);
                        liste0France.add(q4);
                        liste0France = PostgresqlQueryExecution.Execute_query_0(liste0France);
                        etapes ++;
                        franceContent.append("<h2>ETAPE N°"+etapes+": excution sur la base de données de France</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_0 obj : liste0France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        ArrayList<Query_0> liste0China = new ArrayList<Query_0>();
                        Query_0 q1 = new Query_0("Ventes et marketing",0);
                        Query_0 q2 = new Query_0("Ressources humaines",0);
                        Query_0 q3 = new Query_0("IT",0);
                        Query_0 q4 = new Query_0("Finance",0);
                        liste0China.add(q1);
                        liste0China.add(q2);
                        liste0China.add(q3);
                        liste0China.add(q4);
                        liste0China = XmlQueryExecution.Execute_query_0(liste0China);
                        etapes ++;

                        chinaContent.append("<h2>ETAPE N°"+etapes+": excution sur la base de données de Chine</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_0 obj : liste0China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2> Etapes d'obtention des résultats :</h2>" +
                            "<h2>La fonction mediate_query_0 utilise une approche de programmation modulaire pour exécuter une requête en fonction des paramètres \n" +
                            "d'entrée fournis (Chine, France et USA) et renvoyer une liste de résultats sous forme d'objet Query_0.<br>" +
                            "D'abord on commence par initialiser une liste d'objets Query_0 contenant les différentes départemetns: " +
                            "Ventes et marketing, Ressources humaines, IT et Finance, chacune avec une valeur initiale de 0.<br> " +
                            "Aprés sur chaque source de données on met à jour la valeur des objets Query_0 dans la liste initiale " +
                            "en fonction des résultats de la requête exécutée.</h2>" + content + "</html>");
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 1:
                try {
                    double result;
                    result = Mediator.mediate_query_1(chinaSelected, franceSelected, usaSelected);
                    // Display the results in the output area
                    query_label.setText("Moyenne du salaire des employés agés entre 20 et 30 ans.\n");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Moyenne");
                    model.addRow(new Object[]{result});
                    table.setModel(model);
                    table.setFont(font);

                    if (usaSelected == 1) {
                        etapes++;
                        Double moyenne1Usa = 0.0;
                        moyenne1Usa = MysqlQueryExecution.Execute_query_1(moyenne1Usa);
                        usaContent.append("<b> Etape N°" + etapes + ": Execution sur la base de données de USA</b><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        usaContent.append("&emsp;&emsp;").append(moyenne1Usa.toString()).append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        Double moyenne1France =0.0;
                        moyenne1France = PostgresqlQueryExecution.Execute_query_1(moyenne1France);
                        franceContent.append("<b>Etape N°"+etapes+": Execution sur la base de données de France/b><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        franceContent.append("&emsp;&emsp;").append(moyenne1France.toString()).append("<br>");
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        Double moyenne1China =0.0;
                        moyenne1China = XmlQueryExecution.Execute_query_1(moyenne1China);
                        chinaContent.append("<b>Etape N°"+etapes+": Execution sur la base de données de Chine</b><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        chinaContent.append("&emsp;&emsp;").append(moyenne1China.toString()).append("<br>");
                        chinaContent.append("<br>");
                    }
                    chinaContent.append("<b>Enfin, la fonction calcule la moyenne des résultats en divisant la variable moyenne par nbrCountrySelected " +etapes +"</b><br>");


                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2> Etapes d'obtention des résultats :</h2> "
                            + "<h2>La première étape consiste à initialiser une variable double moyenne à 0,0 et un compteur nbrCountrySelected à 0.<br>" +
                            "Ensuite, la fonction vérifie les paramètres d'entrée Chine, France et USA pour déterminer quelle base de données exécutera la requête. <br>" +
                            " Si le paramètre est égal à 1, la fonction appelle la méthode d'exécution correspondante pour cette base de données " +
                            "en passant la variable moyenne en paramètre.<br>" +
                            "Chaque méthode d'exécution ajoute le résultat de la requête à la variable moyenne et incrémente le compteur nbrCountrySelected.<br></h2>" + content + "</html>");
                } catch (SaxonApiException ex) {
                    throw new RuntimeException(ex);
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 2:
                try {
                    // Display the results in the output area
                    query_label.setText("La liste des noms,fonctions, salaires des employés qui ont un salaire plus que 3500");
                    query_label.setFont(QueryLabel_font);
                    //Creating the Table Header row
                    model.addColumn("Nom");
                    model.addColumn("Fonction");
                    model.addColumn("Salaire");
                    model.addColumn("Pays");
                    //Filling the table with the reasult of the Mediator
                    ArrayList<Query_2> result;
                    result = Mediator.mediate_query_2(chinaSelected, franceSelected, usaSelected);
                    for (Query_2 obj : result) {
                        model.addRow(new Object[]{obj.getNom(), obj.getFonction(),obj.getSalaire(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2USA = MysqlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        usaContent.append("<h2> Etape N°"+etapes+": execution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2France = PostgresqlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        franceContent.append("<h2> Etape N°"+etapes+": execution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_2> liste2China = XmlQueryExecution.Execute_query_2(new ArrayList<Query_2>());
                        chinaContent.append("<h2> Etape N°"+etapes+": execution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_2 obj : liste2China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2> <h2>Cette méthode Consiste à récupérer les résultats des différentes bases de données et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête. Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples.</h2>" + content + "</html>");

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
                    query_label.setText("Le poste le plus performant de chaque département avec le nom de l'employé et sa note");
                    query_label.setFont(QueryLabel_font);
                    model.addColumn("Department");
                    model.addColumn("Poste");
                    model.addColumn("Nom");
                    model.addColumn("Performance");
                    model.addColumn("Pays");
                    for (Query_3 obj : result) {
                        model.addRow(new Object[]{obj.getDepartement(), obj.getPoste(),obj.getNom(),obj.getNotePerformance(),obj.getPays()});
                        table.setModel(model);
                        table.setFont(font_tab);
                    }

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_3> liste3USA = new ArrayList<Query_3>();
                        Query_3 q1 = new Query_3("Ventes et marketing","","",0,0);
                        Query_3 q2 = new Query_3("Ressources humaines","","",0,0);
                        Query_3 q3 = new Query_3("IT","","",0,0);
                        Query_3 q4 = new Query_3("Finance","","",0,0);
                        liste3USA.add(q1);
                        liste3USA.add(q2);
                        liste3USA.add(q3);
                        liste3USA.add(q4);

                        liste3USA = MysqlQueryExecution.Execute_query_3(liste3USA);
                        usaContent.append("<h2> Etape N°"+etapes+": execution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_3 obj : liste3USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_3> liste3France = new ArrayList<Query_3>();
                        Query_3 q1 = new Query_3("Ventes et marketing","","",0,0);
                        Query_3 q2 = new Query_3("Ressources humaines","","",0,0);
                        Query_3 q3 = new Query_3("IT","","",0,0);
                        Query_3 q4 = new Query_3("Finance","","",0,0);
                        liste3France.add(q1);
                        liste3France.add(q2);
                        liste3France.add(q3);
                        liste3France.add(q4);
                              
                        liste3France = MysqlQueryExecution.Execute_query_3(liste3France);
                        franceContent.append("<h2> Etape N° "+etapes+ ": execution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_3 obj : liste3France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_3> liste3China = new ArrayList<Query_3>();
                        Query_3 q1 = new Query_3("Ventes et marketing","","",0,0);
                        Query_3 q2 = new Query_3("Ressources humaines","","",0,0);
                        Query_3 q3 = new Query_3("IT","","",0,0);
                        Query_3 q4 = new Query_3("Finance","","",0,0);
                        liste3China.add(q1);
                        liste3China.add(q2);
                        liste3China.add(q3);
                        liste3China.add(q4);

                        liste3China = MysqlQueryExecution.Execute_query_3(liste3China);
                        chinaContent.append("<h2> Etape N°"+etapes+": execution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_3 obj : liste3China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2>" +
                              "<h2>" +
                              "La première étape consiste à initialiser une ArrayList liste3 de Query_3 objets et à créer des objets Query_3 vides pour chaque\n" +
                              "départemets (Ventes et marketing, Ressources humaines,IT, Finance).<br>" +
                              "Ensuite, la fonction vérifie les paramètres d'entrée Chine, France et USA pour déterminer quelle base de données exécutera la requête.<br> \n" +
                              "Si le paramètre est égal à 1, la fonction appelle la méthode d'exécution correspondante pour cette base de données, \n" +
                              "en passant la liste d'objets Query_3 en paramètre. <br>" +
                              "Chaque méthode d'exécution extrait le nom du département, le titre du poste, le nom de l'employé, la note de performance et l'ID du pays. Elle utilise ensuite\n" +
                              "ces informations pour mettre à jour les objets Query_3 de l'ArrayList en vérifiant si la note de performance est supérieure à la note de performance\n" +
                              "actuelle pour le département en question. <br>"+
                              "</h2>" + content + "</html>");
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

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_4> liste4USA = MysqlQueryExecution.Execute_query_4(new ArrayList<Query_4>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2>");
                        usaContent.append("&emsp;<b>- Nombre d'absences :</b>");
                        for (Query_4 obj : liste4USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_4> liste4France = PostgresqlQueryExecution.Execute_query_4(new ArrayList<Query_4>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données  FRANCE</h2>");
                        franceContent.append("&emsp;<b>- Nombre d'absences :</b>");
                        for (Query_4 obj : liste4France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_4> liste4China = XmlQueryExecution.Execute_query_4(new ArrayList<Query_4>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2>");
                        chinaContent.append("&emsp;<b>- Nombre d'absences :</b>");
                        for (Query_4 obj : liste4China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h1>Etapes d'obtention des résultats: </h1>" +
                            "<h2>" +
                            "Cette méthode nous permet de récupérer pour chaque bases de données le nombre d'absences avec motif Formation " +
                            " et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de excuteQuery_4."+
                            "Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples." +
                            "</h2>" + content + "</html>");
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

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_5> liste5USA = MysqlQueryExecution.Execute_query_5(new ArrayList<Query_5>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_5 obj : liste5USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_5> liste5France = PostgresqlQueryExecution.Execute_query_5(new ArrayList<Query_5>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données  FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_5 obj : liste5France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_5> liste5China = XmlQueryExecution.Execute_query_5(new ArrayList<Query_5>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_5 obj : liste5China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2>" +
                            "<h2>" +
                            "On commence par créer une nouvelle ArrayList de Query_5. Ensuite, en fonction des valeurs de Chine" +
                            ", France et USA, on appelle les méthodes" +
                            "Execute_query_5 correspondantes de chaque base de données pour récupérer les résultats et " +
                            "les ajouter à la liste initiale." +
                            "Enfin, elle retourne la liste complète."+
                            "</h2>" + content + "</html>");
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

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_6> liste6USA = MysqlQueryExecution.Execute_query_6(new ArrayList<Query_6>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_6 obj : liste6USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_6> liste6France = PostgresqlQueryExecution.Execute_query_6(new ArrayList<Query_6>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_6 obj : liste6France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_6> liste5China = XmlQueryExecution.Execute_query_6(new ArrayList<Query_6>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_6 obj : liste5China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2> " +
                    "<h2>Cette méthode nous permet de récupérer les postes libres des différentes postes dans les differentes  bases de données " +
                    "et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête en ajoutant les postes libres en orrdonant la liste  ." +
                    " Cela offre une solution pratique et efficace pour traiter des données provenant de sources" +
                     " multiples." +
                    "</h2>" + content + "</html>");
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

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_7> liste7USA = MysqlQueryExecution.Execute_query_7(new ArrayList<Query_7>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_7 obj : liste7USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_7> liste7France = PostgresqlQueryExecution.Execute_query_7(new ArrayList<Query_7>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_7 obj : liste7France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_7> liste7China = XmlQueryExecution.Execute_query_7(new ArrayList<Query_7>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_7 obj : liste7China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2> <h2>Cette méthode nous permet de récupérer les résultats des différentes bases de données et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête. Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples.</h2>" + content + "</html>");
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
                    query_label.setText("Le nom, la derniere performance, et le poste de tous les employé avec leur augmentation de salaire  ");
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

                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_8> liste8USA = MysqlQueryExecution.Execute_query_8(new ArrayList<Query_8>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_8 obj : liste8USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_8> liste8France = PostgresqlQueryExecution.Execute_query_8(new ArrayList<Query_8>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_8 obj : liste8France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_8> liste8China = XmlQueryExecution.Execute_query_8(new ArrayList<Query_8>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_8 obj : liste8China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2> <h2>Cette méthode nous permet de récupérer les résultats des différentes bases de données et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête. Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples.</h2>" + content + "</html>");
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


                    if (usaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_9> liste9USA = MysqlQueryExecution.Execute_query_9(new ArrayList<Query_9>());
                        usaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données USA</h2><br>");
                        usaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_9 obj : liste9USA) {
                            usaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");}
                        usaContent.append("<br>");
                    }

                    if (franceSelected == 1) {
                        etapes ++;
                        ArrayList<Query_9> liste9France = PostgresqlQueryExecution.Execute_query_9(new ArrayList<Query_9>());
                        franceContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données FRANCE</h2><br>");
                        franceContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_9 obj : liste9France) {
                            franceContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        franceContent.append("<br>");
                    }

                    if (chinaSelected == 1) {
                        etapes ++;
                        ArrayList<Query_9> liste9China = XmlQueryExecution.Execute_query_9(new ArrayList<Query_9>());
                        chinaContent.append("<h2>Etape N°"+etapes+": Excution sur la base de données CHINE</h2><br>");
                        chinaContent.append("&emsp;<b>- Résultats :</b><br>");
                        for (Query_9 obj : liste9China) {
                            chinaContent.append("&emsp;&emsp;").append(obj.toString()).append("<br>");
                        }
                        chinaContent.append("<br>");
                    }
                    // Concatenating the results of each country into a single stringBuilder variable.
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder.append(usaContent.toString());
                    contentBuilder.append(franceContent.toString());
                    contentBuilder.append(chinaContent.toString());

                    // Affichage dans un JTextPane
                    String content = contentBuilder.toString();
                    executionSetps.setContentType("text/html");
                    executionSetps.setText("<html>" + "<h2>Etapes d'obtention des résultats : </h2><br> <h2>Cette méthode nous permet de récupérer les résultats des différentes bases de données et de les afficher dans une seule et même liste, qui se met à jour à chaque exécution de la requête. Cela offre une solution pratique et efficace pour traiter des données provenant de sources multiples.</h2>" + content + "</html>");
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