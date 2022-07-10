package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ManagerProjectWindow extends JFrame{
    ManagerProjectWindow(String ProjectName,String loginProjectWindow,String[][] PROJECTSTAB){
    ArrayList<String> ProjectDataList = new ArrayList<>();
    int projectIndex = 0;
        this.setTitle("PROJECT MANAGER");
    Base base = new Base();
        base.readProjects();
    DataReader rootReader = new DataReader();
    String[][] empTab = rootReader.read("com\\company\\empData.txt");
    String[][] projectTab = rootReader.read("com\\company\\Projects.txt");
    DataConverter dc1 = new DataConverter();
    ArrayList<ArrayList<String>> LISTOFPROJECTS = dc1.getProjectList();
    ArrayList<ArrayList<String>> LISTOFEMP = dc1.getEmpList();



        for(int i = 0; i < LISTOFPROJECTS.size();i++){
        if(LISTOFPROJECTS.get(i).get(0).equals(ProjectName)){
            projectIndex = i;
            ProjectDataList = LISTOFPROJECTS.get(i);
            //znalezlismy liste  z danymi prjektu wieec ja otwieramy
        }
    }
    final int finalProjectIndex = projectIndex;

    String statusOfProject = ProjectDataList.get(2);
    JLabel status = new JLabel("Project Status: " +statusOfProject);
        status.setFont(new Font("Verdana", Font.BOLD, 18));

    String[] ListOfEmpPeselsFromProject = ProjectDataList.get(1).split(",");

    final int numberOfEmpsinProject = ListOfEmpPeselsFromProject.length;
    String[][] ARRAYOFEMPOFPROJECT = new String[numberOfEmpsinProject][6];
    //powstaje tablica zdolna do pomieszczenia inf o pracownikach
    int counter = 0;
        for(int i = 0; i<LISTOFEMP.size();i++){
        if(counter<numberOfEmpsinProject) {
            if (LISTOFEMP.get(i).get(4).equals(ListOfEmpPeselsFromProject[counter])) {
                for (int j = 0; j < 6; j++) {
                    ARRAYOFEMPOFPROJECT[counter][j] = LISTOFEMP.get(i).get(j);
                }
                counter++;
            }
        }

    }
    //powwinismy miec tu juz gotowa tablice to wstawiena w tabelke
    String[] colnam = {"Name",
            "Last Name",
            "Job",
            "Status"
    };
    JTable workers = new JTable(ARRAYOFEMPOFPROJECT, colnam);
    JScrollPane workersScroll = new JScrollPane(workers);
    workersScroll.setPreferredSize(new Dimension(630,140));
    workers.setDefaultEditor(Object.class, null);
    String commentsInString = ProjectDataList.get(3);
    String[] array1 = commentsInString.split(",");
    int numberOfComments = array1.length;
    String[][] arrayOfComments= new String[array1.length][3];
        for(int i = 0; i < numberOfComments;i++){
        arrayOfComments[i] = array1[i].split(";");
    }


    String[] commColNames = {"Name","Date","Comment"};
    JTable comments = new JTable(arrayOfComments, commColNames);
    JScrollPane commeentsScroll = new JScrollPane(comments);
    commeentsScroll.setPreferredSize(new Dimension(630,140));
    comments.setDefaultEditor(Object.class, null);


    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(1080, 400);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setLayout(new FlowLayout());

    JPanel sidePanel = new JPanel();
    JPanel mainPanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(400,460));
        sidePanel.setBackground(new Color(200,200,200));
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setBackground(new Color(210,210,210));
        mainPanel.setPreferredSize(new Dimension(650,460));
        sidePanel.setLayout(new FlowLayout());
        mainPanel.setLayout(new FlowLayout());
        JMenuBar mb = new JMenuBar();
        JMenu x = new JMenu("Profile");
        JMenuItem x1 = new JMenuItem("Edit");
        x.add(x1);
        mb.add(x);
        mb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        x1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileEditer(loginProjectWindow);
            }
        });
        JMenuItem x2 = new JMenuItem("ShowData");
        x.add(x2);
        x2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int projectIndex ;
                String[][]user = new String[1][7];
                for(int i = 0; i <empTab.length;i++){
                    if(empTab[i][4].equals(loginProjectWindow)){
                        projectIndex = i;
                        user[0] = empTab[projectIndex];
                        new ShowDataWindow(user);
                    }
                }
            }
        });

    JPanel scrollPanel = new JPanel();
    String[] pName = new String[1];
    pName[0] = ProjectName;
    JList<String> elementChosen = new JList<>(pName);
    JScrollPane scroll = new JScrollPane(elementChosen);
        scroll.setPreferredSize(new Dimension(380,220));
        sidePanel.add(scroll);
        scroll.setCursor(new Cursor(Cursor.HAND_CURSOR));


    ActionListener al = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            new CommentAdder(loginProjectWindow,finalProjectIndex,PROJECTSTAB);
        }
    };

        JButton add_comment = new JButton("ADD COMMENT");
        add_comment.addActionListener(al);
        add_comment.setFocusable(false);
        JButton delete_comment = new JButton("DELETE COMMENT");
        JButton edit_comment = new JButton("EDIT COMMENT");
        edit_comment.setFocusable(false);
        delete_comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = comments.getSelectedRow();
                    arrayOfComments[row][0] = "";
                    arrayOfComments[row][1] = "";
                    arrayOfComments[row][2] = "";
                    StringBuilder builder = new StringBuilder();
                    File f = new File("com\\company\\Projects.txt");
                    String[] newLine = projectTab[finalProjectIndex];
                    newLine[3]="";
                    for (int k = 0; k < arrayOfComments.length; k++) {
                        if(k!=row) {
                            newLine[3] += arrayOfComments[k][0];
                            newLine[3] += ";";
                            newLine[3] += arrayOfComments[k][1];
                            newLine[3] += ";";
                            newLine[3] += arrayOfComments[k][2];
                            newLine[3] += ",";
                        }
                    }
                    String x = newLine[3].replaceFirst(".$","");
                    newLine[3]= x;
                    System.out.println(newLine[3]);

                    projectTab[finalProjectIndex] = newLine;
                    f.delete();
                    for (int i = 0; i < projectTab.length; i++) {
                        for (int j = 0; j < 7; j++) {
                            builder.append(projectTab[i][j] + "");
                            if (j < 6) {
                                builder.append("_");
                            }
                        }
                        builder.append("\n");
                    }
                    BufferedWriter writer;
                    try {
                        writer = new BufferedWriter(new FileWriter("com\\company\\Projects.txt"));
                        writer.write(builder.toString());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Date date = new Date();
                    StringBuilder builder2 = new StringBuilder();
                    projectTab[finalProjectIndex][4] += loginProjectWindow + " deleted a comment at " + ProjectName+ " on " + date +",";

                    File ff = new File("com\\company\\Projects.txt");
                    ff.delete();


                    for (int i = 0; i < projectTab.length; i++) {
                        for (int j = 0; j < 7; j++) {
                            builder2.append(projectTab[i][j] + "");
                            if (j < 6) {
                                builder2.append("_");
                            }
                        }
                        builder2.append("\n");
                    }
                    BufferedWriter writer2 = null;
                    try {
                        writer2 = new BufferedWriter(new FileWriter("com\\company\\Projects.txt"));
                        writer2.write(builder2.toString());
                        writer2.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                }
            }
        });
        delete_comment.setFocusable(false);
        edit_comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommentEditer(loginProjectWindow,projectTab,finalProjectIndex,arrayOfComments,comments.getSelectedRow());
            }
        });

        JButton EDIT = new JButton("EDIT PROJECT MEMBERS");
        add_comment.setFocusable(false);
        EDIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditMembersWindow(finalProjectIndex,numberOfEmpsinProject,projectTab,loginProjectWindow);
            }
        });

        scrollPanel.add(scroll);
        sidePanel.add(scrollPanel);
        sidePanel.add(add_comment);
        sidePanel.add(EDIT);
        sidePanel.add(status);
        mainPanel.add(workersScroll);
        mainPanel.add(commeentsScroll);
        mainPanel.add(delete_comment);
        mainPanel.add(edit_comment);
        this.setJMenuBar(mb);
        this.add(sidePanel);
        this.add(mainPanel);
}
}

