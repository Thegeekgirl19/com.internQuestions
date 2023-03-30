// Q2. Count the number of line start with "The" word from the given text files 
package com.internQuestions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Question02Sol {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File dir = new File("/home/trishalaswain/Downloads/Problem_Input/2");
        File[] file = dir.listFiles();
        String str = "^[tT][hH][eE]\\b";
        Pattern pat = Pattern.compile(str);
        int count = 0;

        for(File f: file) {
        	int indCount = 0;
        	System.out.print("File name: "+ f.getName() + "\t");
        	Scanner sc = new Scanner(f);
              
                while(sc.hasNextLine()) 
                {
                	String str1 = sc.nextLine();
                    Matcher mat = pat.matcher(str1);
                    if(mat.find())
                    {
                        indCount++;
                    }

                } 
                System.out.println("count: " + indCount);
                count += indCount;
                sc.close();
        }
       

        System.out.println("Total sum --> "+count);
    }

}