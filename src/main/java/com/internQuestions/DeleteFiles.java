package com.internQuestions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteFiles {
	
 
	public static void main(String[] args) throws IOException {
		ArrayList<String> delList = new  ArrayList<String>();
		File delFile = new File("/home/trishalaswain/ClientData/SCP/Prod/1499/Output/filesToBeKept.txt");
		
		Scanner sc = new Scanner(delFile);
		 while(sc.hasNextLine()) {
		 	String line = sc.nextLine();
		     delList.add(line);
		 }
		 
     
	     File dir =new File("/home/trishalaswain/ClientData/SCP/Prod/1499/PCS");
	     File[] filesInDir = dir.listFiles();
	     
	     for(File f: filesInDir) {
	    	 if(!(delList.contains(f.getName()))) {
	    	     	f.delete();
	    	         System.out.println(f.getName() +" deleted");
	    	 }
	     }
	     sc.close();
	}


}

