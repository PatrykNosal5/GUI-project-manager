package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class CommentAdder extends JFrame {
    String[][] tabOfProjects;
    CommentAdder(String commentLogin, int projectIndex, String[][] tabOfProjects){
        this.setTitle("MAKE A COMMENT");
        this.tabOfProjects = tabOfProjects;
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
                String ProjectName = tabOfProjects[projectIndex][0];
                Date date = new Date();
                StringBuilder builder = new StringBuilder();
                tabOfProjects[projectIndex][3] += "," + commentLogin + ";" + date + ";" + jtf.getText();

                File f = new File("src/com/company/Projects.txt");
                f.delete();


                for(int i = 0; i < tabOfProjects.length; i++) {
                    for(int j = 0; j < 7; j++){
                         builder.append(tabOfProjects[i][j]+"");
                        if(j < 7) {
                        builder.append("_");
                        }
                    }
                 builder.append("\n");
                }
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("src/com/company/Projects.txt"));
                    writer.write(builder.toString());//save the string representation of the board
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                    StringBuilder builder2 = new StringBuilder();
                tabOfProjects[projectIndex][4] += commentLogin + " commented " + ProjectName+ " on " + date +",";

                    File ff = new File("src/com/company/Projects.txt");
                    ff.delete();


                    for (int i = 0; i < tabOfProjects.length; i++) {
                        for (int j = 0; j < 7; j++) {
                            builder2.append(tabOfProjects[i][j] + "");
                            if (j < 6) {
                                builder2.append("_");
                            }
                        }
                        builder2.append("\n");
                    }
                    BufferedWriter writer2 = null;
                    try {
                        writer2 = new BufferedWriter(new FileWriter("src/com/company/Projects.txt"));
                        writer2.write(builder2.toString());
                        writer2.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                dispose();
            }
        });

        this.add(p);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 100);
        this.setVisible(true);
    }
}

