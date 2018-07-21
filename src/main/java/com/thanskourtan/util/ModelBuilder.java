package com.thanskourtan.util;

import com.thanskourtan.JAXBmodel.BusinessRequestType;
import com.thanskourtan.jclasses.JMainFileClassData;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.thanskourtan.util.EsbClassesNamesCreator.addEsbClasses;
import static com.thanskourtan.util.UtilityMethods.getTypeofClass;
import static com.thanskourtan.util.UtilityMethods.getTypeofClassExpanded;

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
            Class<?> tempClass = Class.forName("com.thanskourtan.jclasses.J" + getTypeofClass(entry.getValue()) + "ClassData");
            JMainFileClassData tempjClassObject = (JMainFileClassData) tempClass.getConstructor(String.class, BusinessRequestType.class, Boolean.class)
                    .newInstance(entry.getValue(), dataFromXml, isAltamira);
            jClassesMap.put(getTypeofClassExpanded(entry.getValue()), tempjClassObject);
        }

        /*Construct the classes*/
        for (Map.Entry<Integer, String> entry : allClassesNamesSet.entrySet()) {
            Class<?> tempClass = Class.forName("com.thanskourtan.filegenerators." + getTypeofClass(entry.getValue()) + "ClassGenerator");
            tempClass.getConstructor(Map.class, String.class, Boolean.class).newInstance(jClassesMap, entry.getValue(), isAltamira);
        }


    }
}
