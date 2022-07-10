package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManFrame extends JFrame {
        JList modelList;
        String[][] projectTab;
    ManFrame(String loginManFrame){
            this.setTitle("PROJECT MANAGER");
            Base base = new Base();
            base.readProjects();

            DataReader rootReader = new DataReader();
            String[][] empTab = rootReader.read("src/com/company/empData.txt");
            projectTab = rootReader.read("src/com/company/Projects.txt");


            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            JPanel scrollPanel = new JPanel();
            modelList = base.jlist;
            JScrollPane scroll = new JScrollPane(modelList);
            scroll.setPreferredSize(new Dimension(380,250));
            sidePanel.add(scroll);
            scroll.setCursor(new Cursor(Cursor.HAND_CURSOR));

            String[] columnNames = {
                    "Name",
                    "Last Name",
                    "Job",
                    "Status"
            };
            DataReader files  = new DataReader();
            String[][] data = files.read("src/com/company/empData.txt");
            JTable workers = new JTable(data, columnNames);
            JScrollPane workersScroll = new JScrollPane(workers);
            workersScroll.setPreferredSize(new Dimension(630,170));
            workers.setDefaultEditor(Object.class, null);
            mainPanel.setLayout(new FlowLayout());
            JMenuBar mb = new JMenuBar();
            JMenu x = new JMenu("Profile");
            JMenuItem x1 = new JMenuItem("Edit");
            JMenu y = new JMenu("Project");
            JMenuItem y1 = new JMenuItem("Add");
            JMenu z = new JMenu("FilterProjects:");
            JMenuItem z1 = new JMenuItem("Planned");
            JMenuItem z2 = new JMenuItem("Active");
            JMenuItem z3 = new JMenuItem("Completed");
            JMenuItem x2 = new JMenuItem("ShowData");
            x.add(x2);
            x2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            int projectIndex ;
                            String[][]user = new String[1][7];
                            for(int i = 0; i <empTab.length;i++){
                                    if(empTab[i][4].equals(loginManFrame)){
                                            projectIndex = i;
                                            user[0] = empTab[projectIndex];
                                            new ShowDataWindow(user);
                                    }
                            }
                    }
            });

            z1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            projectFilter("Planned");
                    }
            });
            z2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            projectFilter("Active");
                    }
            });
            z3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            projectFilter("Completed");
                    }
            });
            y1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            new ProjectAdder(projectTab);
                    }
            });
            x1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            new ProfileEditer(loginManFrame);
                    }
            });
            z.add(z1);
            z.add(z2);
            z.add(z3);
            y.add(y1);
            x.add(x1);
            mb.add(x);
            mb.add(y);
            mb.add(z);
            mb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            scroll.setPreferredSize(new Dimension(380,250));
            sidePanel.add(scroll);
            scroll.setCursor(new Cursor(Cursor.HAND_CURSOR));



            JButton choose = new JButton("OPEN PROJECT");
            choose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            String selectedProject = base.jlist.getSelectedValue().toString();
                            new ManagerProjectWindow(selectedProject,loginManFrame,projectTab);
                    }
            });

            JButton add_project = new JButton("ADD PROJECT");
            add_project.addActionListener(new ActionListener() {
                    // przepisac caly plik i po ostatniej linijce dodac text z wprowadzonymi danymi
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            new ProjectAdder(projectTab);
                            }
            });
            add_project.setFocusable(false);

            JButton delete = new JButton("DELETE WORKER PERMANENTLY");
            delete.setFocusable(false);
            delete.setPreferredSize(new Dimension(300,25));
            delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                           int y = workers.getSelectedRow();
                           String nickname = data[y][4];
                           if(!data[y][2].equals("MAN")) {
                                   StringBuilder builder = new StringBuilder();
                                   File f = new File("src/com/company/empData.txt");
                                   f.delete();
                                   for (int i = 0; i < empTab.length; i++) {
                                           if (i != y) {
                                                for (int j = 0; j < 8; j++) {
                                                        builder.append(empTab[i][j] + "");
                                                           if (j < 7) {
                                                                   builder.append("_");
                                                           }
                                                }
                                                builder.append("\n");
                                           }

                                   }
                                   BufferedWriter writer;
                                   try {
                                           writer = new BufferedWriter(new FileWriter("src/com/company/empData.txt"));
                                           writer.write(builder.toString());//save the string representation of the board
                                           writer.close();
                                   } catch (IOException ex) {
                                           ex.printStackTrace();
                                   }
                                   //==========================================================================
                                   StringBuilder builder2 = new StringBuilder();
                                   File f2 = new File("src/com/company/Projects.txt");
                                   f2.delete();
                                   for (int i = 0; i < projectTab.length; i++) {
                                           String[] array1 = projectTab[i][1].split(",");
                                           for (int j = 0; j < array1.length; j++) {
                                                   if (array1[j].equals(nickname)) {
                                                           projectTab[i][1] = "";
                                                           array1[j] = "";
                                                           for (int k = 0; k < array1.length; k++) {
                                                                   if (k < array1.length - 2) {
                                                                           projectTab[i][1] += array1[k] + ",";
                                                                   } else {
                                                                           projectTab[i][1] += array1[k];
                                                                   }
                                                           }
                                                   }
                                           }
                                   }
                                   for (int i = 0; i < projectTab.length + 1; i++) {
                                           if (i < projectTab.length) {
                                                   for (int j = 0; j < 7; j++) {
                                                           builder2.append(projectTab[i][j] + "");
                                                           if (j < 6) {
                                                                   builder2.append("_");
                                                           }
                                                   }
                                           }
                                           builder2.append("\n");
                                   }
                                   BufferedWriter writer2;
                                   try {
                                           writer2 = new BufferedWriter(new FileWriter("src/com/company/Projects.txt"));
                                           writer2.write(builder2.toString());
                                           writer2.close();
                                   } catch (IOException ex) {
                                           ex.printStackTrace();
                                   }
                           }
                           else{
                                   JOptionPane.showMessageDialog(null,"YOU CAN NOT DELETE MANAGERS!!!");
                           }
                    }
            });

            choose.setFocusable(false);
            scrollPanel.add(scroll);
            sidePanel.add(scrollPanel);
            sidePanel.add(choose);
            sidePanel.add(add_project);
            mainPanel.add(workersScroll);
            mainPanel.add(delete);
            this.setJMenuBar(mb);
            this.add(sidePanel);
            this.add(mainPanel);
            
    }
    public void projectFilter(String filter){
            DefaultListModel model = new DefaultListModel();
            String[] projectArray = new String[projectTab.length];
            int counter = 0;
            for (int i = 0; i < projectTab.length; i++) {
                    if( projectTab[i][2].equals(filter)) {
                            projectArray[counter] = projectTab[i][0];
                            counter++;
                    }
            }
            for(int i  = 0; i <counter ; i++){
                    model.addElement(projectArray[i]);
            }
            modelList.setModel(model);
    }
}
