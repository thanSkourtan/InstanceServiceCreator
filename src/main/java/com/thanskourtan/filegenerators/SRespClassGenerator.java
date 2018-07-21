package com.thanskourtan.filegenerators;


import com.thanskourtan.jclasses.JMainFileClassData;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

import java.io.IOException;
import java.util.Map;

import static com.thanskourtan.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class SRespClassGenerator extends MainFileGenerator{
    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public SRespClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName, Boolean isAltamira) throws JClassAlreadyExistsException, IOException, ClassNotFoundException, NoSuchMethodException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("com.ibm.gr.services");
    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("AbstractServiceResponse");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }
}
