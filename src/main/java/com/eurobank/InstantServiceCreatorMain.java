package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */


import javax.xml.parsers.*;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.filegenerators.BReqClassGenerator;
import com.eurobank.filegenerators.BRespClassGenerator;
import com.eurobank.filegenerators.DTOClassGenerator;
import com.eurobank.filegenerators.MainFileGenerator;
import com.eurobank.saxparser.MyErrorHandler;
import com.eurobank.saxparser.SaxParserHandler;
import com.sun.codemodel.JClassAlreadyExistsException;
import org.apache.commons.cli.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import static com.eurobank.util.DataSetTypesMerger.*;
import static com.eurobank.util.UtilityMethods.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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


        Options options = new Options();
        options.addOption("x", "xml",true,"The path of the xml file to be parsed.");
        options.addOption("s", "service",true,"The name of the service to be handled.");
        options.addOption("q", "Suppress ISC output");

        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = cmdParser.parse(options, args);

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("isc", "",options, "\nex: isc -x " +
                        ".\\EuroBankiSeriesJCA.xml -s GetPropertyFireInsurance\n",
                true);

        if (!cmd.hasOption("xml") && !cmd.hasOption("service")){
            System.out.println("Please enter lala");
            System.exit(-1);
        }

        String filename = cmd.getOptionValue("xml");
        serviceName = cmd.getOptionValue("service");

        //test
        System.out.println(filename);
        System.out.println(serviceName);

        //TODO: Logging and Exception system

        /*String filename = null;

        if(args.length != 2) {
            usage();
        }

        filename = args[0];
        serviceName = args[1];


        */

        // TODO: test the parser here
        BusinessRequestType dataFromXml = parseXmlFile(filename);

        // a list to hold the references to the datatypes classes that will be created
        List<MainFileGenerator> dataTypeClasses = new ArrayList<>();

        mergeDataSets(dataFromXml.getDataSet()).forEach((k, v) -> {
            MainFileGenerator tempClass = null;
            if (getClassName(k).endsWith("BReq")){
                tempClass = new BReqClassGenerator(k, v);
            } else if (getClassName(k).endsWith("BResp")) {
                tempClass = new BRespClassGenerator(k, v);
            } else {
                tempClass = new DTOClassGenerator(k, v);
            }

            try {
                tempClass.generateAll();
                dataTypeClasses.add(tempClass);
            } catch (JClassAlreadyExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Create bean
//        MainFileGenerator beancg = new BeanClassGenerator(dataFromXml.getBean());
//        beancg.generateAll();

        // MainFileGenerator breqcg = new DataSetTypesClassGenerator();
//        breqcg.generateAll();


    }
}
