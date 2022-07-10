package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginFrame extends JFrame {
    Entitled entitled;
    LoginFrame(Base base,Entitled entitled){
        this.entitled=entitled;
        this.setSize(420, 420);
        setDefaultCloseOperation(3);
        JButton LOGIN = new JButton("CONFIRM");
        JLabel lg = new JLabel("Login:");
        JLabel psw = new JLabel("Password:");
        JTextField t1 = new JTextField();
        JTextField t2 = new JPasswordField();
        lg.setBounds(50,100,75,25);
        psw.setBounds(50,150,75,25);
        JLabel jl1 = new JLabel();
        t1.setBounds(125,100,200,25);
        t2.setBounds(125,150,200,25);
        LOGIN.setBounds(125,225,200,25);


        LOGIN.setFocusable(false);

        jl1.add(LOGIN);
        jl1.add(psw);
        jl1.add(lg);
        jl1.add(t1);
        jl1.add(t2);
        this.add(jl1);
        this.setVisible(true);

        LOGIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAttempt(base,t1,t2);
            }
        });
        KeyListener x = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int ENTER = e.getKeyCode();
                if (ENTER == KeyEvent.VK_ENTER) {
                    loginAttempt(base,t1,t2);
                }
            }
        };
        t1.addKeyListener(x);
        t2.addKeyListener(x);
        LOGIN.addKeyListener(x);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }
    protected void loginAttempt(Base base,JTextField t1,JTextField t2){
        if(base.getBase().containsKey(t1.getText())){
            if(base.getBase().get(t1.getText()).equals(t2.getText())){
                JOptionPane.showMessageDialog(null,"LOGGED IN SUCCESFULLY");
                dispose();
                if(entitled.getEntitled().get(t1.getText()).equals(workingAs.dev)){
                    new DevFrame(t1.getText());
                }
                else{
                    new ManFrame(t1.getText());
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"INCORRECT COMBINATION OF LOGIN AND PASSWORD");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"INCORRECT COMBINATION OF LOGIN AND PASSWORD");
        }
    }
}
