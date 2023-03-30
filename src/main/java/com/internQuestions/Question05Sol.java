/*
 * Q5. "List out code values from xml files. (If there are multiple codes in <Icd10CmCode>
 *	tag then, Consider highest rank codes only
 *	- if same rank for more than one code then choose any random code)"
 */
package com.internQuestions;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Question05Sol {
	public static void main (String[] args) {
		File dir = new File("/home/trishalaswain/Downloads/Problem_Input/5"); 
		File[] files = dir.listFiles();
		for (File file:files) {
			try {
				 System.out.println(file.getName());
				//an instance of factory that gives a document builder  
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
				//an instance of builder to parse the specified xml file  
				DocumentBuilder builder = factory.newDocumentBuilder();  
				Document doc = builder.parse(file); 
				doc.getDocumentElement().normalize();
				NodeList parentList = doc.getElementsByTagName("Icd10CmCode") ;
				//System.out.println(icd10List);
				//System.out.println();
				
				for(int i=0; i<parentList.getLength();i++) {
					Node nNode = parentList.item(i);
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						NodeList codeList = eElement.getElementsByTagName("code");
						//System.out.println(codeList.getLength());
						int max = -1;
						//int index = -1;
						for(int count = 0; count <codeList.getLength(); count++) {
							Node node1 = codeList.item(count);
							if(node1.getNodeType()==Node.ELEMENT_NODE) {
								Element code = (Element) node1;
								int currentRank = Integer.parseInt(code.getAttribute("rank"));
								//System.out.println("current rank: " + currentRank);
								if(currentRank > max) {
									
									max = currentRank;
									//index = count;
									
									
								}
								if(count == codeList.getLength()-1) {
									System.out.print("Value: "+ code.getAttribute("value"));
									System.out.println("\t max: " + max);
								}
								
							}
							
						}
						
						
					}
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println();
		}
		
		
	}

}


  

