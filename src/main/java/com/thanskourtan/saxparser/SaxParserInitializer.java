package com.thanskourtan.saxparser;

import com.thanskourtan.exceptions.ApplicationException;
import com.thanskourtan.exceptions.exceptionhandlers.SaxParserErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.thanskourtan.util.UtilityMethods.*;

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

    public static SaxParserHandler parseXmlFile(String filename, String serviceName, Properties props) throws ApplicationException{
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = null;
        XMLReader xmlReader = null;
        SaxParserHandler saxParserHandler = null;
        try {
            saxParser = spf.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            saxParserHandler = new SaxParserHandler(serviceName);
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
                if(saxParserHandler.getRoot() == null) {
                    throw new ApplicationException("No service with the name " + serviceName + " found.");
                }
            }

        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not find the xml file.");
            System.exit(-1);
        }
        xmlReader.setErrorHandler(new SaxParserErrorHandler(System.err));

        return saxParserHandler;
    }
}
