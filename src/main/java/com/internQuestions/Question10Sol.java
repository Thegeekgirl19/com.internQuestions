// Q10. Expand given eqn.s using codeList
package com.internQuestions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question10Sol {
	public static ArrayList<String> codeList = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		File codeFile = new File("/home/trishalaswain/Downloads/Problem_Input/10/LatestIcd10Codes");
		File eqnFile = new File("/home/trishalaswain/Downloads/Problem_Input/10/Equations");
		try {
			Scanner sc = new Scanner(codeFile);
			Scanner sc2 = new Scanner(eqnFile);
			String line ="";
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				codeList.add(line);
			}
			sc.close();
			
			String eqn = "";
			int count = 1;
			while(sc2.hasNextLine()) {
				System.out.println("Equation no. " + count);
				eqn = sc2.nextLine();
				ArrayList<String> termList = new ArrayList<String>();
				termList = getTerms(eqn);
				getResult(termList, codeList, eqn);
				count++;
				System.out.println();
				System.out.println();
			}
			
			sc2.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> getTerms(String eqn) {
		ArrayList<String> termList = new ArrayList<String>();
		String[] array = eqn.split(" ");
		for(String str: array) {
			if(str.endsWith("*") && !termList.contains(str)) {
				termList.add(str);
			}
		}
		return termList;
	}
	
	public static void getResult(ArrayList<String> termList, ArrayList<String> codeList, String eqn) {
		
		for(String term: termList) {
			for(String code: codeList) {
				if(code.startsWith((term.substring(0, 4)))) {
					String tempEqn = eqn.replace(term, code);
					ArrayList<String> tempList = getTerms(tempEqn);
					//System.out.println(tempList);
					if(tempList.isEmpty()) {
						System.out.println(tempEqn);
					} else {
						getResult(tempList, codeList, tempEqn);
					}
					
				}
			}
		}
	}
}
