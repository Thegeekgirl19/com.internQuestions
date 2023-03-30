package com.internQuestions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question07Sol {

    public static void main(String args[]) throws IOException {
    	File file = new File("/home/trishalaswain/Downloads/Problem_Input/7/demoFile");
    	Scanner sc =new Scanner(file);
        ArrayList<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\t");
        while(sc.hasNextLine()) {     
        	String line = sc.nextLine();
            String[] array = line.split("@@");
            Matcher matcher = pattern.matcher(array[0]);
            int tabCount = 0;
            while (matcher.find()) {
                tabCount++;
            }
            if(tabCount == 0) {
                result.clear();
                result.add(array[0]);
            }
            else {
                try {
                    result.set(tabCount,array[0].trim());
                }
                catch(Exception e) {   
                	//e.printStackTrace();
                    result.add(array[0].trim());
                }
            }
            String content = result.get(tabCount);
            if(content.contains("~")) {
                printTabular(result,content,tabCount);
            }
           
           
         }
        sc.close();
    }
    
    public static void printTabular(ArrayList<String> output,String content, int tabCount) {
        String[] split = content.split("~");
        output.set(tabCount, split[0]);
        System.out.print(split[1] + " ");
        for(String str : output) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}

