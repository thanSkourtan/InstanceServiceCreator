package com.eurobank.aspectj;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.eurobank.saxparser.SaxParserHandler;
import com.sun.codemodel.JPackage;
import com.eurobank.filegenerators.BReqClassGenerator;

public aspect LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());


//    public pointcut parserStartDocument():
//        execution (public void SaxParserHandler.startDocument());
//
//
//    before(): parserStartDocument() {
//        logger.log(Level.INFO, "Started parsing the xml document.");
//    }


//    public pointcut callGeneratePackages (JPackage mainPackage):
//            call (public void BReqClassGenerator.generatePackages()) && target(mainPackage);
//
//    before(JPackage mainPackage) : callGeneratePackages(mainPackage) {
//        logger.log(Level.INFO, "Started generation of package " + mainPackage.toString());
//    }
//
//    after(JPackage mainPackage) : callGeneratePackages(mainPackage) {
//        logger.log(Level.INFO, "Finished generation of package " + mainPackage.toString());
//    }

    public pointcut callGeneratePackages (JPackage mainPackage):
            execution (public void BReqClassGenerator.generatePackages()) && args(mainPackage);

    before(JPackage mainPackage) : callGeneratePackages(mainPackage) {
        logger.log(Level.INFO, "Started generation of package "+ mainPackage.toString());
    }

    after(JPackage mainPackage) : callGeneratePackages(mainPackage) {
        logger.log(Level.INFO, "Finished generation of package ");
    }



    before() : execution (public void SaxParserHandler.startDocument()){
        logger.log(Level.INFO, "Started parsing the xml document.");
    }


}