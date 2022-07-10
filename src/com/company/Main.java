package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Base base = new Base();
        Entitled entitled =  new Entitled();
        LoginFrame x = new LoginFrame(base,entitled);
        //base.readEmp();
        base.readProjects();
    }
}
