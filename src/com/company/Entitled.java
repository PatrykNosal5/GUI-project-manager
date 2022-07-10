package com.company;
import java.util.HashMap;

enum workingAs{
            dev,
            man
        }

public class Entitled {
    HashMap<String,workingAs> logininfo = new HashMap<>();
    Entitled(){
            logininfo.put("MARIAN",workingAs.dev);
            logininfo.put("TOMASZ",workingAs.man);
            logininfo.put("ANDRZEJ",workingAs.dev);
    }
    public HashMap getEntitled(){
        return logininfo;
    }
    public void addEntitled(String login,int workingAs){
        switch(workingAs) {
            case 1->logininfo.put(login, com.company.workingAs.dev);
            case 2->logininfo.put(login, com.company.workingAs.man);
            default->
                System.out.println("Invalid number.");
        }
    }
}
