package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DevFrame extends JFrame {
    DevFrame(String loginDevFrame) {
        this.setTitle("PROJECT MANAGER");
        Base base = new Base();
        base.readMyProjects(loginDevFrame);

        DataReader rootReader = new DataReader();
        String[][] empTab = rootReader.read("com\\company\\empData.txt");
        String[][] projectTab = rootReader.read("com\\company\\Projects.txt");
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
        mainPanel.setLayout(new FlowLayout());
        JMenuBar mb = new JMenuBar();
        JMenu x = new JMenu("Profile");
        JMenuItem x1 = new JMenuItem("Edit");
        JMenuItem x2 = new JMenuItem("ShowData");
        x.add(x2);
        x.add(x1);
        mb.add(x);
        mb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        x1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileEditer(loginDevFrame);
            }
        });
        x2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int projectIndex ;
                String[][]user = new String[1][7];
                for(int i = 0; i <empTab.length;i++){
                    if(empTab[i][4].equals(loginDevFrame)){
                        projectIndex = i;
                        user[0] = empTab[projectIndex];
                        new ShowDataWindow(user);
                    }
                }
            }
        });

        JPanel scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(base.jlist);
        scroll.setPreferredSize(new Dimension(380,250));
        scroll.setCursor(new Cursor(Cursor.HAND_CURSOR));



        JButton choose = new JButton("OPEN PROJECT");
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProject = base.jlist.getSelectedValue().toString();
                new ProjectWindow(selectedProject,loginDevFrame,projectTab);
            }
        });
        choose.setFocusable(false);
        scrollPanel.add(scroll);
        sidePanel.add(scrollPanel);
        sidePanel.add(choose);
        this.setJMenuBar(mb);
        this.add(sidePanel);
        this.add(mainPanel);
    }
}