package com.internQuestions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Question12Sol {
    public static void main(String[] args) throws IOException {
      
            File file = new File("/home/trishalaswain/Downloads/Problem_Input/12/Inputfile");
            FileReader fr=new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line="";
            ArrayList<String[]> list=new ArrayList<String[]>();
            ArrayList<String> unique=new ArrayList<String>();
            ArrayList<String> conflict=new ArrayList<String>();
            while ((line = br.readLine()) != null) 
            {
                String[] array = line.split("\t");
                String[] temp= {array[0],  array[1].toLowerCase()+"\t"+array[2].toLowerCase()};
                list.add(temp);
            }
            
           
            for(String[] s:list) 
            {
                String first=s[0];
                String value=s[1];
                boolean flag=false;
                for(String[] strArray:list) 
                {
                    if(first.equals(strArray[0]))
                    {
                        if(strArray[1].equals(value)) 
                        {
                          flag=true;
                        }
                        else 
                        {
                          flag=false;
                          break;
                        }
                    }
                }
                
                if(flag==false) 
                {
                    conflict.add(first+"\t"+value);
                }
                else 
                {
                    unique.add(first+"\t"+value);
                }
            }         
            System.out.println("Conflict Output");
            System.out.println("--------------------------------");
            for(String s: conflict) {
            	System.out.println(s);
            }
            System.out.println();
            
            System.out.println("Unique Output");
            System.out.println("--------------------------------");
            for(String s: unique) {
            	System.out.println(s);
            }
            br.close();
          

    }

}

