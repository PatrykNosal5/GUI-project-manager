package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    //rozbija na tablice wierszy i rzedow
    public String[][] read(String FILE) {
        long lines = 0;
        try {
            List<String> List = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader(FILE));
            String line = bf.readLine();
            while (line != null) {
                List.add(line);
                line = bf.readLine();
                lines++;
            }
            bf.close();
            String[] array = List.toArray(new String[0]);
            String[][]result = new String[array.length][6];

            for(int i=0;i<array.length;i++){
                result[i] = array[i].split("_");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    }
