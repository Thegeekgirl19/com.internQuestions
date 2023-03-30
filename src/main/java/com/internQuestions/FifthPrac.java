//package com.internQuestions;
//
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//
//class Xmlread extends DefaultHandler {
//	
//	private StringBuilder currentValue = new StringBuilder();
//	
//	public void startDocument() {
//	      System.out.println("Start Document");
//	  }
//
//	  public void endDocument() {
//	      System.out.println("End Document");
//	  }
//	  	  
//	  public void startElement(
//	          String uri,
//	          String localName,
//	          String qName,
//	          Attributes attributes) {
//
//	      // reset the tag value
//	      currentValue.setLength(0);
//
//	      System.out.printf("Start Element : %s%n", qName);
//	      Set srank = new HashSet();
//
////	      if (qName.equalsIgnoreCase("Icd10CmCode")) {
//	          // get tag's attribute by name
//	    	  
//	    	  if (qName.equalsIgnoreCase("code")) {
//		          String id = attributes.getValue("value");
//		          String rank = attributes.getValue("rank");
//		          srank.add(rank);
//		         String maxrank =  Collections.max(srank);
//		         if(rank == maxrank) {
//		          System.out.println("max code value : "+ id + "Rank:" + rank);
//		         }
//	    	  }
////	      }
//	      
////	      if (qName.equalsIgnoreCase("code")) {
////	           get tag's attribute by index, 0 = first attribute
////	          String currency = attributes.getValue(0);
////	          String rank = attributes.getValue(1);
////	          System.out.printf("Currency :%s%n", currency);
////	          System.out.println(rank);
////	      }
////	      
////	      for(int i=0; i<attributes.getLength(); i++) {
////     	     System.out.println(attributes.getQName(0)+"=\""+attributes.getValue(0)+"\"");
////       }
////	      
////	      int max = -1;
////	      for(int count=0;count<attributes.getLength();count++) {
//	    	  
////	    	  if (qName.equalsIgnoreCase("Icd10CmCode")) {
//	    		  // get tag's attribute by name
////	    		  String codevalue = attributes.getValue("code");
////	    		  System.out.println(codevalue);
////	    		  if (qName.equalsIgnoreCase("code")) {
////	    			  String cvalue = attributes.getValue("value");
////	    			  int codecurrentrank = Integer.parseInt(attributes.getValue("rank"));
////	    			  System.out.println("Code value:" + cvalue + "Rank:" + codecurrentrank);
////	    		  }
////	    		  if(codecurrentrank>max) {
////	    			  max=codecurrentrank;
////	    			  System.out.println(max);
////	    		  }
////	    		  if(count == attributes.getLength()-1) {
////	    			  System.out.print("Value: "+ codevalue);
////						System.out.println("\t Rank: " + max);
////	    		  }
//	    	  }
////	      }
////	          for(int i=0; i<attributes.getLength(); i++) {
////	        	     System.out.print(attributes.getQName(i)+"=\""+attributes.getValue(i)+"\"");
////	          }
//	         
////	  }
//
//	  
//	  
//	  public void endElement(String uri,
//              String localName,
//              String parentName) {
//
//		  System.out.printf("End Element : %s%n", parentName);
//		 
////		  if (parentName.equalsIgnoreCase("value")) {
////	          System.out.printf("code : %s%n", currentValue.toString());
////	      }
//	  }
//	  
//	  public void characters(char ch[], int start, int length) {
//		  currentValue.append(ch, start, length);
//	  }
//}
//
//public class FifthPrac
//{
//	static String FILENAME = "/home/trishalaswain/eclipse-workspace-photon/com.internQuestions/Initial Programming Question Input/5/Carlos O Romero_168978_9.5.19_Las Cruces.txt.xml";
//	
//	public static void main(String[] args)
//	{
//		SAXParserFactory factory = SAXParserFactory.newInstance();
//		try {
//
//            SAXParser saxParser = factory.newSAXParser();
//
//            Xmlread xr = new Xmlread();
//
//            saxParser.parse(FILENAME, xr);
//
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//	}
//
//}
