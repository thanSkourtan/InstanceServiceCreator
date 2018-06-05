package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.saxparser.SaxParserHandler;
import com.eurobank.util.ModelBuilder;

import static com.eurobank.util.UtilityMethods.*;
import static com.eurobank.util.EsbClassesNamesCreator.*;
import static com.eurobank.saxparser.SaxParserInitializer.*;
import static com.eurobank.routing.PlacementDirectory.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InstantServiceCreatorMain {

//    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws Exception{

        OptionsProcessor optionsProcessor = new OptionsProcessor(args);
        OptionsProcessor.CmdData cmdData = optionsProcessor.processOptions();
        // logger.log(Level.INFO, "Sample logging-------------");

        // TODO: test the parser here
        /*First Part: Parsing*/
        SaxParserHandler saxParserHandler = parseXmlFile(cmdData.getFilename(), cmdData.getServiceName());
        BusinessRequestType dataFromXml = saxParserHandler.getRoot();
        Set<String> brmClassNamesSet = saxParserHandler.getAllClassesNames();

        ModelBuilder.createModel(brmClassNamesSet, dataFromXml);

        JMainFileClassData.getBrmMessagesCodeModel().build(new File("src//main//resources//resources2"));
        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File("src//main//resources//resources3"));
        JMainFileClassData.getEsbMessagesCodeModel().build(new File("src//main//resources//resources4"));
        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File("src//main//resources//resources1"));


    }
}
