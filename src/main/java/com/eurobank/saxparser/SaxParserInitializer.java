package com.eurobank.saxparser;

import com.eurobank.exceptions.exceptionhandlers.SaxParserErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by v-askourtaniotis on 5/6/2018. mailTo: thanskourtan@gmail.com
 */
public class SaxParserInitializer {

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

    public static SaxParserHandler parseXmlFile(String filename, String serviceName) throws IOException, SAXException, ParserConfigurationException {
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
}
