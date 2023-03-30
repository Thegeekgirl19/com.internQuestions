// Q9. Compare CM files prepare Match, Miss and Extra code list and count
// Hint : Prepare code list using only value of code tag"
package com.internQuestions;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Question09Sol {

	public static void main(String[] args) {
		File dir1 = new File("/home/trishalaswain/Downloads/Problem_Input/9/CM1");
		File[] cm1 = dir1.listFiles();
		File dir2 = new File("/home/trishalaswain/Downloads/Problem_Input/9/CM2");
		File[] cm2 = dir2.listFiles();
		
		for(File f1: cm1) {
			for(File f2: cm2) {
				if(f1.getName().substring(0, 7).equals(f2.getName().substring(0, 7))) {
					if(f1.getName().equals(f2.getName())) {
						System.out.println("File Name: " + f1.getName());
						
						ArrayList<String> f1List;
						ArrayList<String> f1ListTemp;
						ArrayList<String> f2List;
						ArrayList<String> f2ListTemp;
						try {
							f1List = getCodeValList(f1);
							f1ListTemp = getCodeValList(f1);
							f2List = getCodeValList(f2);
							f2ListTemp = getCodeValList(f2);
							writeMatch(f1ListTemp,f2ListTemp);
							writeNoMatch(f1List, f2List);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println();
						System.out.println();
					} else {
						
						System.out.println("No match found between: \n" + f1.getName() + "\n" + f2.getName());
						System.out.println();
						System.out.println();
					}
					
		 
				} 
			}
		}

	}

	public static void writeMatch(ArrayList<String> f1ListTemp, ArrayList<String> f2ListTemp) throws IOException {
		
		Set<String> Am = new HashSet<String>(f1ListTemp);
		Set<String> Bm = new HashSet<String>(f2ListTemp);
		Am.retainAll(Bm);
		System.out.println( "Match Found: "+ Am );
		System.out.println("Total match count: " + Am.size());
	}	
	
	public static void writeNoMatch(ArrayList<String> f1List, ArrayList<String> f2List) throws IOException {
		
		Set<String> A = new HashSet<String>(f1List);
		Set<String> B = new HashSet<String>(f2List);

		Set<String> copyA = new HashSet<String>(A);
		Set<String> copyB = new HashSet<String>(B);
		// B-A --> missing
		B.removeAll(A);
		System.out.println("Missing: " + B);
		System.out.println("Total missing count: "+B.size());
		// A-B --> extra
		copyA.removeAll(copyB);
		System.out.println("Extra: " + copyA);
		System.out.println("Total extra count: "+copyA.size());

	}
	
	public static ArrayList<String> getCodeValList(File file){
		//an instance of factory that gives a document builder  
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		//an instance of builder to parse the specified xml file  
		DocumentBuilder builder;
		ArrayList<String> codeValList = new ArrayList<String>();
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file); 
			doc.getDocumentElement().normalize();
			NodeList codeList = doc.getElementsByTagName("code");
			//System.out.println(codeList);
			
				
			for(int count = 0; count <codeList.getLength(); count++) {
				Node node1 = codeList.item(count);
				if(node1.getNodeType()== Node.ELEMENT_NODE) {
					Element codeVal = (Element) node1;
					codeValList.add(codeVal.getAttribute("value"));
				}
				
			}
			
			/*NodeList parentList = doc.getElementsByTagName("Icd10CmCode") ;
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
								//System.out.print("Value: "+ code.getAttribute("value"));
								codeValList.add(code.getAttribute("value"));
							}
							
						}
						
					}
					
					
				}*/
				
			//}
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return codeValList;
		
		
		

	}

}

