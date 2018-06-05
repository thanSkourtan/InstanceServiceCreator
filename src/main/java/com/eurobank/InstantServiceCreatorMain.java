package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */


import javax.xml.parsers.*;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.exceptions.exceptionhandlers.SaxParserErrorHandler;
import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.saxparser.SaxParserHandler;
import org.apache.commons.cli.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import static com.eurobank.util.UtilityMethods.*;
import static com.eurobank.util.EsbClassesNamesCreator.*;
import static com.eurobank.routing.PlacementDirectory.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InstantServiceCreatorMain extends DefaultHandler{


    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    private static void usage() {
        System.err.println("Usage: java jar <name_of_jar> <file.xml> <service_name>");
        System.exit(1);
    }

    public static SaxParserHandler parseXmlFile(String filename, String serviceName) throws IOException, SAXException, ParserConfigurationException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SaxParserHandler saxParserHandler = new SaxParserHandler(serviceName);
        xmlReader.setContentHandler(saxParserHandler);
        xmlReader.parse(convertToFileURL(filename));
        xmlReader.setErrorHandler(new SaxParserErrorHandler(System.err));

        return saxParserHandler;
    }



    public static void main(String[] args) throws Exception{


        OptionsProcessor optionsProcessor = new OptionsProcessor(args);
        OptionsProcessor.CmdData cmdData = optionsProcessor.processOptions();

        logger.log(Level.INFO, "Sample logging-------------");
        //TODO: Logging and Exception system

        // TODO: test the parser here
        /*First Part: Parsing*/
        SaxParserHandler saxParserHandler = parseXmlFile(cmdData.getFilename(), cmdData.getServiceName());
        BusinessRequestType dataFromXml = saxParserHandler.getRoot();
        Set<String> brmClassNamesSet = saxParserHandler.getAllClassesNames();

/********************************************************************************************************/

        /*Second Part: Build the JObjects model. */
        Map<Integer, String> allClassesNamesSet = addEsbClasses(brmClassNamesSet);
        //keeps the insertion order
        Map<String, JMainFileClassData> jClassesMap = new LinkedHashMap<>(); //add the jclasses

        allClassesNamesSet.forEach((k,v) -> {
            try {
                Class<?> tempClass = Class.forName("com.eurobank.jclasses.J" + getTypeofClass(v) + "ClassData");
                JMainFileClassData tempjClassObject = (JMainFileClassData) tempClass.getConstructor(String.class, BusinessRequestType.class)
                        .newInstance(v, dataFromXml);
                jClassesMap.put(getTypeofClassExpanded(v), tempjClassObject);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

/********************************************************************************************************/
        /*Third part: construct the classes*/

        allClassesNamesSet.forEach((k,v) -> {
            try {
                Class<?> tempClass = Class.forName("com.eurobank.filegenerators." + getTypeofClass(v) + "ClassGenerator");
                Object tempClassObject = tempClass.getConstructor(Map.class, String.class).newInstance(jClassesMap, v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

/********************************************************************************************************/


        JMainFileClassData.getBrmMessagesCodeModel().build(new File("src//main//resources//resources2"));
        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File("src//main//resources//resources3"));
        JMainFileClassData.getEsbMessagesCodeModel().build(new File("src//main//resources//resources4"));
        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File("src//main//resources//resources1"));


    }
}
