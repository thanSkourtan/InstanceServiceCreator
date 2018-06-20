package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */

import com.eurobank.exceptions.ApplicationException;
import com.eurobank.routing.CreatedClassesRouter;
import com.eurobank.routing.PlacementDirectory;
import com.eurobank.saxparser.SaxParserHandler;
import com.eurobank.util.ModelBuilder;
import com.eurobank.util.OptionsProcessor;
import com.eurobank.util.PropertiesGetter;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.eurobank.saxparser.SaxParserInitializer.*;
import static com.eurobank.util.ServiceClassesRemover.*;


public class InstantServiceCreatorMain {

    // private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {

        try{
            OptionsProcessor optionsProcessor = new OptionsProcessor(args);
            OptionsProcessor.CmdData cmdData = optionsProcessor.processOptions();

            PropertiesGetter propertiesGetter = new PropertiesGetter();

            // logger.log(Level.INFO, "Sample logging-------------");

            SaxParserHandler saxParserHandler = parseXmlFile(cmdData.getFilename(), cmdData.getServiceName(), propertiesGetter.props);

            optionsProcessor.setProjectAfterParsing(cmdData, saxParserHandler.getXmlFile());

            if(cmdData.isDelete()) {
                deleteServiceClasses(saxParserHandler.getAllClassesNames(), cmdData.getProject(), propertiesGetter.props);
            } else {
                ModelBuilder.createModelAndClasses(saxParserHandler.getAllClassesNames(), saxParserHandler.getRoot(), saxParserHandler.isAnAltamira());
                new CreatedClassesRouter().route(cmdData.getProject(), propertiesGetter.props);
            }
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
