package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CommentEditer extends JFrame {
    CommentEditer(String loginProjectWindow,String[][] projectTab,int finalProjectIndex,String[][] arrayOfComments,int row){
        this.setTitle("EDIT THE COMMENT");
        this.setVisible(true);
        JButton jb = new JButton("Submit Comment");
        jb.setFocusable(false);
        jb.setBounds(100,20,200,25);
        JTextField jtf = new JTextField(25);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(jtf);
        p.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayOfComments[row][0].equals(loginProjectWindow)) {
                    arrayOfComments[row][2] = jtf.getText();
                    StringBuilder builder = new StringBuilder();
                    File f = new File("com\\company\\Projects.txt");
                    String[] newLine = projectTab[finalProjectIndex];
                    newLine[3]="";
                    for (int k = 0; k < arrayOfComments.length; k++) {
                            newLine[3] += arrayOfComments[k][0];
                            newLine[3] += ";";
                            newLine[3] += arrayOfComments[k][1];
                            newLine[3] += ";";
                            newLine[3] += arrayOfComments[k][2];
                            newLine[3] += ",";
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
                    projectTab[finalProjectIndex][4] += loginProjectWindow + " edited a comment at " + projectTab[finalProjectIndex][0]+ " on " + date +",";

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
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"THIS IS NOT YOUR COMMENT!");
                }
            }
        });
        this.add(p);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 100);
        this.setVisible(true);
    }
}

