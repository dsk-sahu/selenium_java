package com.uiautomaiton.web.Helpers;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;


public class TestDataLoader {
    public static HashMap<String, String> config = null;
    private String userDirectory;
    private static Logger logger = null;
    public TestDataLoader()
    {
        logger = Logger.getLogger(this.getClass());

    }
    public static HashMap<String, String> getConfigData() throws Exception {
        if (config == null) {
            HashMap<String, String> hashMap = null;
            hashMap = new TestDataLoader().propertiesLoader("config.properties");
            config = hashMap;
            return hashMap;
        } else {
            return config;
        }
    }

    public HashMap<String, String> propertiesLoader(String filePath) throws Exception {
        HashMap<String, String> HMap = new HashMap<String, String>();
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(filePath);
            prop.load(input);
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                HMap.put(key, value);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return HMap;

    }

    public static HashMap<String, String> xmlTestDataLoader(String fileName) {
        HashMap testData = new HashMap();
        String testDataKey = null;
        String testDataValue = null;
        File e ;
        try {

            e = new File(System.getProperty("user.dir") + "\\TestData\\"+fileName+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(e);
            NodeList elementNodeList = doc.getElementsByTagName("Elements");

            for(int temp = 0; temp < elementNodeList.getLength(); ++temp) {
                Node elementNode = elementNodeList.item(temp);
                NodeList paramNodeList = elementNode.getChildNodes();

                for(int temp1 = 0; temp1 < paramNodeList.getLength(); ++temp1) {
                    Element ele = null;
                    Node paramNode = paramNodeList.item(temp1);
                    if(paramNodeList.item(temp1).getNodeType() == 1) {
                        ele = (Element)paramNode;
                        testDataKey = ele.getAttribute("key");
                        testDataValue = ele.getAttribute("value");
                        testData.put(testDataKey, testDataValue);
                    }
                }
            }
        }catch (FileNotFoundException FNFE) {
            FNFE.printStackTrace();
            logger.info("Test Data file not found : "+FNFE.getMessage());
        }

        catch (Exception var16) {
            var16.printStackTrace();
        }

        return testData;
    }

}
