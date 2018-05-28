package com.eurobank.saxparser;

/**
 * Created by v-askourtaniotis on 18/5/2018. mailTo: thanskourtan@gmail.com
 */

import com.eurobank.JAXBmodel.BusinessRequestType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaxParserHandler extends DefaultHandler {

    public SaxParserHandler (String serviceName) {
        this.serviceName = serviceName;
    }

    private String serviceName;
    private boolean serviceFound;
    private BusinessRequestType root;// the root is a tree with pointers only from parents to children. so we cannot go backwards
    private Deque<Object> orderedXmlElementsStack; // this is why we need a second structrure, which is a stack


    public void startDocument() throws SAXException {
        orderedXmlElementsStack = new ArrayDeque<>();
        //logger.log(Level.INFO, "Started parsing the xml document.");
    }

    public void startElement(String namespaceURI,String localName,
                             String qName, Attributes atts) throws SAXException {

        if((localName.equals("BusinessRequest") && atts.getValue("Name").equals(serviceName)) || serviceFound) {
            serviceFound = true;
//            logger.log(Level.INFO, "Parsing " + localName + " element.");
            try{
                Class<?> xmlElementClass = Class.forName("com.eurobank.JAXBmodel." + localName + "Type");
                Constructor<?> xmlElementConstructor = xmlElementClass.getConstructor();
                Object xmlElementObject = xmlElementConstructor.newInstance();

                //Fills the attributes of the instance
                for(int i = 0; i < atts.getLength(); i++){
                    String tempAttributeName = atts.getLocalName(i).substring(0, 1).toLowerCase() + atts.getLocalName(i).substring(1);
                    Field tempAttribute = xmlElementClass.getDeclaredField(tempAttributeName);
                    tempAttribute.setAccessible(true);
                    tempAttribute.set(xmlElementObject, atts.getValue(i));
                }

                //Attaches directly or indirectly the current instance to the root element(keeping the state)
                if(root == null) {
                    root = (BusinessRequestType) xmlElementObject;
                    orderedXmlElementsStack.push(root);
                    return;
                } else {
                    // Attach the object to the previous xml element. So we need a reference to it. If the previous
                    // element does not have a field for the object, then go one level upper and so on.

                    attachCurrentElementToTree(localName, xmlElementClass, xmlElementObject);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public void attachCurrentElementToTree(String localName,
                                           Class<?> currentElementClass,
                                           Object xmlElementObject)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {

        Class<?> previousElementClass = null;
        Object previousElementObject = null;
        try {
            previousElementObject = orderedXmlElementsStack.peek();
            previousElementClass = previousElementObject.getClass();
        } catch (EmptyStackException e) {
            System.err.println("Bad structure in the xml file!");
            e.printStackTrace();
            System.exit(-1);
        }

        Field[] previousElementFields = previousElementClass.getDeclaredFields();

        Optional<Field> previousElementAttribute = Arrays.asList(previousElementFields)
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(localName))
                .findAny();

        if(previousElementAttribute.isPresent()){
            // attaches current xmlElement to previous element
            Field previousElementField = previousElementAttribute.get();
//            Class<?> previousElementFieldType = previousElementField.getType();

            if (previousElementField.getType().getName().equals("java.util.List")) {

                previousElementField.setAccessible(true);
                List<Object> myList =  (java.util.List) previousElementField.get(previousElementObject);
                if(myList == null){
                    myList = new ArrayList<>();
                }
                myList.add(xmlElementObject);

                previousElementField.setAccessible(true);
                previousElementField.set(previousElementObject, myList);

            } else {
                previousElementField.setAccessible(true);
                previousElementField.set(previousElementObject, xmlElementObject);
            }

            orderedXmlElementsStack.push(xmlElementObject);
            return;
        } else {
            // recursively search in which element to place the current element

            orderedXmlElementsStack.pop();
            attachCurrentElementToTree(localName, currentElementClass, xmlElementObject);

        }
    }


    public void endElement (String uri, String localName, String qName) throws SAXException
    {
        if(serviceFound && localName.equals("BusinessRequest")) {
            serviceFound = false;
        }
    }


    public void endDocument() throws SAXException {
//        logger.log(Level.INFO, "Finished parsing the xml document.");
    }

    public BusinessRequestType getRoot() {
        return root;
    }

    public void setRoot(BusinessRequestType root) {
        this.root = root;
    }
}
