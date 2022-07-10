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

public class EditMembersWindow extends JFrame {
    String[] projectTab;
    int numberOfEmps;
    String[][] allProjectsArray;
    EditMembersWindow(int projectIndex,int numberOfEmps,String[][] allProjectsArray,String login){
        this.allProjectsArray =allProjectsArray;
        this.projectTab = allProjectsArray[projectIndex];
        this.numberOfEmps = numberOfEmps;
        this.setTitle("EDIT MEMBERS PANEL");
        this.setSize(new Dimension(650,210));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setVisible(true);


          JPanel workersPanel= new JPanel();
        String[] columnNames = {
                "Name",
                "Last Name",
                "Job",
                "Status",
                "Nickname"
        };
        DataReader files  = new DataReader();
        String[][] data = files.read("src/com/company/empData.txt");
        JTable workers = new JTable(data, columnNames);
        JScrollPane workersScroll = new JScrollPane(workers);
        workers.setDefaultEditor(Object.class, null);
        JButton add = new JButton("ADD");
       add.setFocusable(false);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numberOfEmps>=2){
                    JOptionPane.showMessageDialog(null,"THIS PROJECT HAS ALREADY 2 MEMBERS!");
                }
                else{
                    String[] selectedWorkerData  = data[workers.getSelectedRow()];
                    String[] listOfEmpsInProject = projectTab[1].split(",");
                    boolean isInProject = false;
                    if(selectedWorkerData[2].equals("MAN")){
                        JOptionPane.showMessageDialog(null,"THIS MEMBER IS A MANAGER AND CAN NOT BE ADDED TO THE PROJECT");
                        isInProject = true;
                    }
                    for(int i = 0; i<listOfEmpsInProject.length;i++){
                        if(listOfEmpsInProject[i].equals(selectedWorkerData[4])){
                            JOptionPane.showMessageDialog(null,"THIS MEMBER IS ALREADY ASSIGNED TO THIS PROJECT");
                            isInProject = true;
                        }
                    }
                    if(isInProject==false){
                        StringBuilder builder = new StringBuilder();
                        Date date = new Date();
                        allProjectsArray[projectIndex][4] +=date + "-" + login + " has added:" +selectedWorkerData[4] + " to:"+  allProjectsArray[projectIndex][0]+",";
                        allProjectsArray[projectIndex][1]+=","+selectedWorkerData[4];
                        File f = new File("src/com/company/Projects.txt");
                        f.delete();
                        for(int k = 0; k < allProjectsArray.length; k++) {
                            for(int j = 0; j < 7; j++){
                                builder.append(allProjectsArray[k][j]+"");
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
                        JOptionPane.showMessageDialog(null,"MEMBER ADDED SUCCESSFULLY");
                        dispose();
                    }
                }
            }
        });
        JButton remove = new JButton("REMOVE");
        remove.setFocusable(false);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numberOfEmps<=1){
                    JOptionPane.showMessageDialog(null,"IMPOSSIBLE TO REMOVE THE MEMBER DUE TO NUMBER OF MEMBERS IN THIS PROJECT");
                }
                else{
                    String[] selectedWorkerData  = data[workers.getSelectedRow()];
                    String[] listOfEmpsInProject = projectTab[1].split(",");
                    int removedIndex = 0;
                    boolean gotremoved = false;
                    for(int i = 0; i<listOfEmpsInProject.length;i++){
                        if(listOfEmpsInProject[i].equals(selectedWorkerData[4])){
                            JOptionPane.showMessageDialog(null,"MEMBER HAS BEEN REMOVED SUCCESSFULLY!");
                            listOfEmpsInProject[i]="999";
                            gotremoved = true;
                            removedIndex = i;
                        }
                    }
                    if(gotremoved) {
                        Date date = new Date();
                        allProjectsArray[projectIndex][4] +=date + "-" + login + " has removed:" +selectedWorkerData[4] + " from:"+  allProjectsArray[projectIndex][0]+",";
                        allProjectsArray[projectIndex][1]="";
                        for (int i = 0; i < listOfEmpsInProject.length; i++) {
                            if (listOfEmpsInProject[i].equals("999")) {
                            }
                            else{
                                if(i!=removedIndex) {
                                    allProjectsArray[projectIndex][1] += listOfEmpsInProject[i]+",";
                                }
                            }
                        }
                        allProjectsArray[projectIndex][1] = allProjectsArray[projectIndex][1].substring(0, allProjectsArray[projectIndex][1].length() - 1);
                    }
                        StringBuilder builder = new StringBuilder();
                        File f = new File("src/com/company/Projects.txt");
                        f.delete();
                        for(int k = 0; k < allProjectsArray.length; k++) {
                            for(int j = 0; j < 7; j++){
                                builder.append(allProjectsArray[k][j]+"");
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
                        dispose();
                }
            }
        });
        add.setPreferredSize(new Dimension(210,25));
        remove.setPreferredSize(new Dimension(210,25));
        workersPanel.setSize(new Dimension(650,150));
        workersScroll.setPreferredSize(new Dimension(620,120));
        workers.setSize(new Dimension(650,150));
        workersPanel.add(workersScroll);
        this.add(workersPanel);
        this.add(add);
        this.add(remove);
    }
}
