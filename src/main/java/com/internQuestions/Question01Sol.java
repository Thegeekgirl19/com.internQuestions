// Q1. Remove Files which are present in list from given folder
package com.internQuestions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question01Sol {
    static ArrayList<String> delList = new  ArrayList<String>();
    
    public static void main(String[] args) throws IOException {
    	
        // reading and storing deletefiles.txt in a list line by line
    	File delFile = new File("/home/trishalaswain/Downloads/Problem_Input/1/DeleteFiles.txt");
    	Scanner sc = new Scanner(delFile);
        while(sc.hasNextLine()) {
        	String line = sc.nextLine();
            delList.add(line);
        }
        
        File dir =new File("/home/trishalaswain/Downloads/Problem_Input/1/InputFolder");
        clean(dir);
        sc.close();
    }
   
    public static void deleteFile (File f) {
        if(delList.contains(f.getName())) {
        	f.delete();
            System.out.println(f.getName() +" deleted");
        }
        
    } 
    
    public static void clean(File dir) { 	
        File[] filesInDir = dir.listFiles();
        
        for(File f: filesInDir) {
            
            if(f.isDirectory()) {
            	String newPath = f.getPath() ;
            	File subDir = new File(newPath);
                clean(subDir);
            } else {
                deleteFile(f);
                
            }
        }
    }
}
