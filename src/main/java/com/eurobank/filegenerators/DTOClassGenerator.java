package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DTOClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public DTOClassGenerator(Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(canonicalName));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("java.io");
    }

    @Override
    public void generateOuterFieldsAndMethods() {
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("Serializable");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }
}
