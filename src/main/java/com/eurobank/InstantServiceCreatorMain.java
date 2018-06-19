package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */

import com.eurobank.routing.CreatedClassesRouter;
import com.eurobank.saxparser.SaxParserHandler;
import com.eurobank.util.ModelBuilder;
import com.eurobank.util.OptionsProcessor;
import com.eurobank.util.PropertiesGetter;

import static com.eurobank.saxparser.SaxParserInitializer.*;
import static com.eurobank.util.ServiceClassesRemover.*;


public class InstantServiceCreatorMain {

//    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws Exception{

        OptionsProcessor optionsProcessor = new OptionsProcessor(args);
        OptionsProcessor.CmdData cmdData = optionsProcessor.processOptions();

        PropertiesGetter propertiesGetter = new PropertiesGetter();

        // logger.log(Level.INFO, "Sample logging-------------");

        SaxParserHandler saxParserHandler = parseXmlFile(cmdData.getFilename(), cmdData.getServiceName(), propertiesGetter.props);

        ModelBuilder.createModelAndClasses(saxParserHandler.getAllClassesNames(), saxParserHandler.getRoot(), saxParserHandler.isAnAltamira());

        if(cmdData.isDelete()) {
            deleteServiceClasses(saxParserHandler.getAllClassesNames(), cmdData.getProject(), propertiesGetter.props);
            System.exit(-1);
        }

        // Place the created classes
        new CreatedClassesRouter().route(cmdData.getProject(), propertiesGetter.props);


    }
}
