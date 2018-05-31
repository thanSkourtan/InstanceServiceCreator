package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */


import javax.xml.parsers.*;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.filegenerators.*;
import com.eurobank.exceptions.exceptionhandlers.SaxParserErrorHandler;
import com.eurobank.saxparser.SaxParserHandler;
import com.sun.codemodel.JClassAlreadyExistsException;
import org.apache.commons.cli.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import static com.eurobank.util.DataSetTypesMerger.*;
import static com.eurobank.util.UtilityMethods.*;
import static com.eurobank.util.EsbClassesNamesCreator.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InstantServiceCreatorMain extends DefaultHandler{

    private static String serviceName;
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

    public static SaxParserHandler parseXmlFile(String filename) throws IOException, SAXException, ParserConfigurationException{
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
        logger.log(Level.INFO, "Sample logging-------------");
        //TODO: Logging and Exception system

        /*String filename = null;

        if(args.length != 2) {
            usage();
        }

        filename = args[0];
        serviceName = args[1];


        */

        // TODO: test the parser here
        SaxParserHandler saxParserHandler = parseXmlFile(filename);
        BusinessRequestType dataFromXml = saxParserHandler.getRoot();
        Set<String> brmClassNamesSet = saxParserHandler.getAllClassesNames();

        /*Second Part*/
        /*Creates BReq, BResp and DTOs*/


        Set<String> allClassNamesSet = addEsbClasses(brmClassNamesSet);
        Map<String, List<DataSetType>> mergedDataSetTypes = mergeDataSets(dataFromXml.getDataSet());

        mergedDataSetTypes.forEach((k, v) -> {
            MainFileGenerator tempClass = null;
            if (isABReqClassName(getClassName(k))){
                tempClass = new BReqClassGenerator(k, v, allClassNamesSet);
            } else if (isABRespClassName(getClassName(k))){
                tempClass = new BRespClassGenerator(k, v, allClassNamesSet);
            } else {
                tempClass = new DTOClassGenerator(k, v, allClassNamesSet);
            }

            try {
                tempClass.generateAll();

            } catch (JClassAlreadyExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        /*Creates Bean*/
        if(allClassNamesSet.size() < 2){
            // Throw some exception
        }

        MainFileGenerator beanClass = new BeanClassGenerator(dataFromXml.getBean(), allClassNamesSet);
        beanClass.generateAll();

        //Create bean
//        MainFileGenerator beancg = new BeanClassGenerator(dataFromXml.getBean());
//        beancg.generateAll();

        // MainFileGenerator breqcg = new DataSetTypesClassGenerator();
//        breqcg.generateAll();


    }
}
