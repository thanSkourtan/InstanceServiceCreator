package com.eurobank.util;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.jclasses.JMainFileClassData;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.eurobank.util.EsbClassesNamesCreator.addEsbClasses;
import static com.eurobank.util.UtilityMethods.getTypeofClass;
import static com.eurobank.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 5/6/2018. mailTo: thanskourtan@gmail.com
 */
public class ModelBuilder {

    public static void createModelAndClasses(Set<String> brmClassNamesSet, BusinessRequestType dataFromXml, Boolean isAltamira)
                            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
                                    InstantiationException, InvocationTargetException{
        Map<Integer, String> allClassesNamesSet = addEsbClasses(brmClassNamesSet);
        Map<String, JMainFileClassData> jClassesMap = new LinkedHashMap<>();

        /*Construct the model*/
        for (Map.Entry<Integer, String> entry : allClassesNamesSet.entrySet()) {
            Class<?> tempClass = Class.forName("com.eurobank.jclasses.J" + getTypeofClass(entry.getValue()) + "ClassData");
            JMainFileClassData tempjClassObject = (JMainFileClassData) tempClass.getConstructor(String.class, BusinessRequestType.class, Boolean.class)
                    .newInstance(entry.getValue(), dataFromXml, isAltamira);
            jClassesMap.put(getTypeofClassExpanded(entry.getValue()), tempjClassObject);
        }

        /*Construct the classes*/
        for (Map.Entry<Integer, String> entry : allClassesNamesSet.entrySet()) {
            Class<?> tempClass = Class.forName("com.eurobank.filegenerators." + getTypeofClass(entry.getValue()) + "ClassGenerator");
            tempClass.getConstructor(Map.class, String.class, Boolean.class).newInstance(jClassesMap, entry.getValue(), isAltamira);
        }


    }
}
