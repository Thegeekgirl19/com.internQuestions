package com.internQuestions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Question04Sol {

	public static void main(String[] args) throws IOException {
		File dir =  new File("/home/trishalaswain/Downloads/Problem_Input/4");
		File[] files = dir.listFiles();
		
		for(File file: files) {
			if(file.getName().contains(".csv")) {
				System.out.println(file.getName());
				Reader in = new FileReader(file);
				int sum = 0;
				Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
						for (CSVRecord record : records) {
						    String amount = record.get("Amount");
						    sum += Integer.parseInt(amount);
						    					    
						}
						System.out.println("Total sum : " + sum);
				
			} else {
				System.out.println(file.getName());
				Scanner sc = new Scanner(file);
				String[] header = sc.nextLine().split("\t");
				int count = 1;
				int index = -1;
				for(String str: header) {
					if(str.equals("Amount")) index = count;
					else count++;
				}
				double sum = 0;
				//System.out.println("Index of Amount: " + index);
				while(sc.hasNextLine()) {
					String[] data = sc.nextLine().split("\t");
					sum += Double.parseDouble(data[index]);
				}
				System.out.println("Total sum: " + sum);
				sc.close();
			}
		}

	}

}
