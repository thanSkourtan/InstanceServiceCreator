package com.eurobank.as400filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import static com.eurobank.util.UtilityMethods.*;
import com.sun.codemodel.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DTOClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public DTOClassGenerator(Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
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