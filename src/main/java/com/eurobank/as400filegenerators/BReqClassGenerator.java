package com.eurobank.as400filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;

import java.io.IOException;
import java.util.Map;

import static com.eurobank.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BReqClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public BReqClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateOuterFieldsAndMethods()  {


    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400InputDataBean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }


}
