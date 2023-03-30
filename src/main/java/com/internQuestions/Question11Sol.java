// Q11. Using regex, retrive all possible BMI value from given document
package com.internQuestions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question11Sol {
    public static void main(String[] args) throws FileNotFoundException {
    	File file = new File("/home/trishalaswain/Downloads/Problem_Input/11/TextDocument");
    	Scanner sc = new Scanner(file);
        final String regex = "([Bb][Mm][Ii]|[bB]o[ds]y mass index)([[a-zA-Z()=|][\\s\\:<>-]])*(\\d{1,2}[\\.]?\\d{1,2}+)(to|-)?(\\d{1,2}[\\.]?\\d{1,2}+)?";
        int count = 0;
        System.out.println("BMI matches Found are as follows: ");
        System.out.println();
        while(sc.hasNextLine()) {
        	String string = sc.nextLine();
        	final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            final Matcher matcher = pattern.matcher(string);
                  
            while (matcher.find()) {
                System.out.println(matcher.group(0));
            	count++;
            	
            	
            }
        }
        sc.close();
   
        System.out.println();
        System.out.println("Total " + count + " matches found.");
       
    }
}
