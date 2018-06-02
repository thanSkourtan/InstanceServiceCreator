package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BRespClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public BRespClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) {
        super(jClassesMap.get(canonicalName));
        this.jClassesMap = jClassesMap;
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateOuterFieldsAndMethods() {

//        fieldsGenerator.createFields(jDefinedClass, mainModel, dataFromXml, dataTypeClasses);



    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400OutputDataBean");
//        jDefinedClass._extends(superclass);
    }

}
