package com.company;

import javax.swing.*;
import java.awt.*;

public class ShowDataWindow extends JFrame {
    ShowDataWindow(String[][]user){
        this.setTitle("Your Data");
        String[] ColNames = {"Name","Surname","Job","Nick","Pesel","Phone number","Email"};
        JTable data = new JTable(user,ColNames);
        JScrollPane Scroll = new JScrollPane(data);
        Scroll.setPreferredSize(new Dimension(380,78));
        data.setDefaultEditor(Object.class, null);
        this.add(Scroll);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 78);
        this.setVisible(true);
    }
}
