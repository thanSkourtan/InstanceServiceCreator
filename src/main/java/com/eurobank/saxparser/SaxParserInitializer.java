package com.eurobank.saxparser;

import com.eurobank.exceptions.exceptionhandlers.SaxParserErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import static com.eurobank.util.UtilityMethods.*;

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

    public static SaxParserHandler parseXmlFile(String filename, String serviceName, Properties props) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        SaxParserHandler saxParserHandler = new SaxParserHandler(serviceName);
        xmlReader.setContentHandler(saxParserHandler);

        if(filename != null) {
            xmlReader.parse(convertToFileURL(filename));
        } else {

            File xmlDirectory = new File((String) props.get("xml_dir"));

            File[] xmlFiles = xmlDirectory.listFiles(x -> x.getName().endsWith("xml"));

            for(int i = 0; i < xmlFiles.length; i++) {
                xmlReader.parse(convertToFileURL(xmlFiles[i].getAbsolutePath()));
                if(saxParserHandler.getRoot() != null) {
                    saxParserHandler.setXmlFile(getXmlFileName(xmlFiles[i].getName()));
                    break;
                }
            }
            //todo: error handling here in case we do not find the service
        }



        xmlReader.setErrorHandler(new SaxParserErrorHandler(System.err));



        return saxParserHandler;
    }
}
