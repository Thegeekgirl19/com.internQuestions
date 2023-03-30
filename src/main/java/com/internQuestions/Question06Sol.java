package com.internQuestions;
import java.io.File;
import java.util.HashMap;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Question06Sol {
   public static void main(String[] args) throws ParserConfigurationException {
       try {
           File[] file = new File("/home/trishalaswain/Downloads/Problem_Input/6").listFiles();
           
           for (File f : file) {
        	   System.out.println(f.getName());
               DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
               DocumentBuilder db = dbf.newDocumentBuilder();
               Document doc = db.parse(f);
               doc.getDocumentElement().normalize();
               HashMap<String, Integer> nodeMap = new HashMap<String, Integer>();
               NodeList nodeList = doc.getElementsByTagName("RelationSet");
               
               for (int i = 0; i < nodeList.getLength(); i++) {
                   Node childNode = nodeList.item(i);
                   if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                       Element element = (Element) childNode;
                       NodeList itemList = element.getChildNodes();
                       for (int j = 0; j < itemList.getLength(); j++) {
                            Node internalTag = itemList.item(j);
                           if (nodeMap.containsKey(internalTag.getNodeName())) {
                                nodeMap.put(internalTag.getNodeName(), nodeMap.get(internalTag.getNodeName()) + 1);
                            } else {
                                nodeMap.put(internalTag.getNodeName(), 1);
                            }
                        }
                    }
                }
                System.out.println(nodeMap);
                System.out.println();
            }



       } catch (Exception e) {
            e.printStackTrace();
        }
    }



}