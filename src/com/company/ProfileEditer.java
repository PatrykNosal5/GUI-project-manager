package com.company;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileEditer extends JFrame {
    ProfileEditer(String login) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(300, 140);
        this.setVisible(true);
        JPanel panel = new JPanel();
        JButton editNumber = new JButton("EDIT PHONE NUMBER");
        JButton editEmail = new JButton("EDIT EMAIL");
        JButton editNick = new JButton("EDIT NICK");
        editEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataEditor(1,login);
            }
        });
        editNick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataEditor(2,login);
            }
        });
        editNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataEditor(3,login);
            }
        });
        editEmail.setFocusable(false);
        editNick.setFocusable(false);
        editNumber.setFocusable(false);
        panel.add(editEmail);
        panel.add(editNick);
        panel.add(editNumber);
        this.add(panel);
        panel.setLayout(new FlowLayout());
        editEmail.setPreferredSize(new Dimension(270,25));
        editNumber.setPreferredSize(new Dimension(270,25));
        editNick.setPreferredSize(new Dimension(270,25));

    }
}
