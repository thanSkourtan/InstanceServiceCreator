package com.eurobank.aspectj;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.eurobank.saxparser.SaxParserHandler;

public aspect LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

//    public pointcut parserStartDocument():
//        execution (public void SaxParserHandler.startDocument());
//
//
//    before(): parserStartDocument() {
//        logger.log(Level.INFO, "Started parsing the xml document.");
//    }

    before() : execution (public void SaxParserHandler.startDocument()){
        logger.log(Level.INFO, "Started parsing the xml document.");
    }


}