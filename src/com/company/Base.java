package com.company;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Base {
    DataReader rootReader = new DataReader();
    String[][] projectTab = rootReader.read("src/com/company/Projects.txt");
    HashMap<String, String> logininfo = new HashMap<>();
    JList jlist = new JList();

    Base() {
        logininfo.put("MARIAN", "123");
        logininfo.put("TOMASZ", "PASSWORD");
        logininfo.put("ANDRZEJ", "abc123");
    }

    public HashMap getBase() {
        return logininfo;
    }
    public void readProjects() {

        File f = new File("src/com/company/Projects.txt");
        try {
            String[] Res = new String[100];

            int counter = 0;

            int pos = 0;
            String read;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while ((read = br.readLine()) != null) {
                String[] splited = read.split("_",2);
                for (int i = 0; i < splited.length; i = i + 2) {
                    counter++;
                    Res[pos] = splited[i];
                    pos++;
                }
            }
            String[] res = new String[counter];
            for (int i = 0; i < counter; i++) {
                res[i] = Res[i];
            }
            JList jlist2 = new JList(res);
            jlist = jlist2;
        } catch (Exception e) {
            System.out.println(e);
        }

    }
        public void readMyProjects(String nickname) {

            File f = new File("src/com/company/Projects.txt");
            try {
                //CHCEMY dostac sie tylko do tych projektow ktore zawieraja w sobie w wierszu nasz nick
                String[] Res = new String[100];

                int counter = 0;

                int pos = 0;
                String read;
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                while ((read = br.readLine()) != null) {
                    String[] splited = read.split("_",2);
                    for (int i = 0; i < splited.length; i = i + 2) {
                        if(splited[1].contains(nickname)) {
                            Res[pos] = splited[i];
                            pos++;
                            counter++;
                        }
                    }
                }
                String[] res = new String[counter];
                for (int i = 0; i < counter; i++) {
                    res[i] = Res[i];
                }
                JList jlist2 = new JList(res);
                jlist = jlist2;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

}

