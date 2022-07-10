package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectAdder extends JFrame {
    String[][] projectsArray;
    ProjectAdder(String[][] projectsArray) {
        this.projectsArray=projectsArray;
        this.setTitle("MAKE A PROJECT");
        JButton jb = new JButton("Submit Project");
        jb.setFocusable(false);
        jb.setBounds(100, 20, 200, 25);
        JTextField jtf = new JTextField(25);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(jtf);
        p.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder builder = new StringBuilder();
                File f = new File("src/com/company/Projects.txt");
                f.delete();


                for (int i = 0; i < projectsArray.length+1; i++) {
                    if (i < projectsArray.length){
                        for (int j = 0; j < 7; j++) {
                            builder.append(projectsArray[i][j] + "");
                            if (j < 6) {
                                builder.append("_");
                            }
                        }
                    }
                    else{
                        builder.append(jtf.getText()+"_ _Planned_who;when;what,who;when;what_LOG1,LOG2,LOG3,_0_0");
                    }
                    builder.append("\n");
                }
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter("src/com/company/Projects.txt"));
                    writer.write(builder.toString());
                    writer.close();
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
