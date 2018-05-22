package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */


import javax.xml.parsers.*;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.filegenerators.BeanClassGenerator;
import com.eurobank.filegenerators.MainFileGenerator;
import com.eurobank.saxparser.MyErrorHandler;
import com.eurobank.saxparser.SaxParserHandler;
import org.xml.sax.*;
import org.xml.sax.helpers.*;


import java.io.*;




public class InstantServiceCreatorMain extends DefaultHandler{

    private static String serviceName;

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

    public static BusinessRequestType parseXmlFile(String filename) throws IOException, SAXException, ParserConfigurationException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SaxParserHandler saxParserHandler = new SaxParserHandler(serviceName);
        xmlReader.setContentHandler(saxParserHandler);
        xmlReader.parse(convertToFileURL(filename));
        xmlReader.setErrorHandler(new MyErrorHandler(System.err));
        return saxParserHandler.getRoot();
    }



    public static void main(String[] args) throws Exception{

        String filename = null;

        if(args.length != 2) {
            usage();
        }

        filename = args[0];
        serviceName = args[1];
        // TODO: test the parser here
        BusinessRequestType dataFromXml = parseXmlFile(filename);

        System.out.println("lala");

        MainFileGenerator bcg = new BeanClassGenerator(dataFromXml.getBean());
        bcg.generateAll();
//      WebServiceGenerator wsg = new WebServiceGenerator();
//      wsg.get().buildData().writeFile();

    }
}
