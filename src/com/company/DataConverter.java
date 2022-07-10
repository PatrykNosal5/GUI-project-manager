package com.company;

import java.util.ArrayList;

public class DataConverter {
    ArrayList<ArrayList<String>> list1;
    ArrayList<ArrayList<String>> list2;

    DataConverter(){

        DataReader rootReader = new DataReader();
        String[][] empTab = rootReader.read("src/com/company/empData.txt");
        int howManyEmp = empTab.length;
        String[][] projectTab = rootReader.read("src/com/company/Projects.txt");
        int howManyProjects = projectTab.length;

        ArrayList<ArrayList<String>> superEmpList = new ArrayList<>();
        for(int i = 0; i<howManyEmp;i++){
            ArrayList<String> employee = new ArrayList<>();
            for(int j = 0;j<7;j++){
                employee.add(empTab[i][j]);
            }
            superEmpList.add(employee);
        }
        list2=superEmpList;

        ArrayList<ArrayList<String>> superProjectList = new ArrayList<>();
        for(int i = 0; i<howManyProjects;i++){
            ArrayList<String> project = new ArrayList<>();
            for(int j = 0;j<7;j++){
                project.add(projectTab[i][j]);
            }
            superProjectList.add(project);
        }
        list1=superProjectList;
    }
    public ArrayList<ArrayList<String>>  getProjectList(){
        return list1;
    }
    public ArrayList<ArrayList<String>>  getEmpList(){
        return list2;
    }
}
