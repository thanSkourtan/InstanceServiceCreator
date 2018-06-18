package com.eurobank;

/**
 * Created by v-askourtaniotis on 16/5/2018. mailTo: thanskourtan@gmail.com
 */

import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.saxparser.SaxParserHandler;
import com.eurobank.util.ModelBuilder;
import static com.eurobank.saxparser.SaxParserInitializer.*;
import static com.eurobank.routing.PlacementDirectory.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class InstantServiceCreatorMain {

//    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws Exception{

        OptionsProcessor optionsProcessor = new OptionsProcessor(args);
        OptionsProcessor.CmdData cmdData = optionsProcessor.processOptions();
        // logger.log(Level.INFO, "Sample logging-------------");

        SaxParserHandler saxParserHandler = parseXmlFile(cmdData.getFilename(), cmdData.getServiceName());

        ModelBuilder.createModelAndClasses(saxParserHandler.getAllClassesNames(), saxParserHandler.getRoot(), saxParserHandler.isAnAltamira());

        /****************************************************************/
        Properties props = new Properties();
        String resourceName = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        }

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + ".*"
                + cmdData.getProject() + ".*");

        File directory = new File((String) props.get("root"));

        File[] files = directory.listFiles(File::isDirectory);
        List<File> projectNames =  Arrays.asList(files).stream()
                .map(File::toPath)
                .filter(matcher::matches)
                .map(Path::toFile)
                .collect(Collectors.toList());

        System.out.println("projectNames: " + projectNames);
        //directory placement logic goes here
//        JMainFileClassData.getBrmMessagesCodeModel().build(new File("src//main//resources//resources2"));
//        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File("src//main//resources//resources3"));
//        JMainFileClassData.getEsbMessagesCodeModel().build(new File("src//main//resources//resources4"));
//        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File("src//main//resources//resources1"));

        JMainFileClassData.getBrmMessagesCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().startsWith("BRM") && !x.getName().endsWith("Exits"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getEsbBusinessLogicCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().endsWith("Impl"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getEsbMessagesCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().startsWith("ESB") && !x.getName().endsWith("Impl"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));
        JMainFileClassData.getBrmBusinessLogicCodeModel().build(new File(projectNames.stream()
                .filter(x->x.getName().endsWith("Exits"))
                .findFirst()
                .orElseThrow(NullPointerException::new), "src"));


    }
}
