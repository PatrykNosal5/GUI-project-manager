package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataEditor extends JFrame {
    DataEditor(int whatToEdit,String login){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(300, 110);
        this.setVisible(true);
        JPanel panel = new JPanel();
        JTextField jtf = new JTextField("", 20);
        JButton confirm = new JButton("CONFIRM CHANGES");
        panel.setLayout(new FlowLayout());
        panel.add(jtf);
        panel.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        DataReader rootReader = new DataReader();
                        String[][] empTab = rootReader.read("com\\company\\empData.txt");
                        int index = 0;
                        for (int i = 0; i < empTab.length; i++) {
                            if (empTab[i][4].equals(login)) {
                                index = i;
                            }
                        }
                            int number = switch (whatToEdit){
                                case 1 -> 7;
                                case 2 -> 4;
                                case 3 -> 6;
                            default -> throw new IllegalStateException("Unexpected value: " + whatToEdit);
                        };
                        empTab[index][number] = jtf.getText();
                        StringBuilder builder = new StringBuilder();
                        File f = new File("com\\company\\empData.txt");
                        f.delete();
                        for (int i = 0; i < empTab.length; i++) {
                                for (int j = 0; j < 8; j++) {
                                    builder.append(empTab[i][j] + "");
                                    if (j < 7) {
                                        builder.append("_");
                                    }
                                }
                            builder.append("\n");
                            }
                        BufferedWriter writer = null;
                        try {
                            writer = new BufferedWriter(new FileWriter("com\\company\\empData.txt"));
                            writer.write(builder.toString());
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        dispose();
            }
        });
        this.add(panel);
    }
}
