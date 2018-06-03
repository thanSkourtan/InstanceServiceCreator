package com.eurobank.filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JPackage;

import java.io.IOException;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class SPClassGenerator extends MainFileGenerator{
    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public SPClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(canonicalName));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {

    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {

    }
}
