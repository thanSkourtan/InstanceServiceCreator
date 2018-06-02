package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DTOClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;

    public DTOClassGenerator( JRequestResponseObjectsClassData mainclassdata) {
        super(mainclassdata);
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("java.io");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {

    }

    @Override
    public void generateOuterFieldsAndMethods() {

//        fieldsGenerator.createFields(jDefinedClass, mainModel, dataFromXml, dataTypeClasses);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("Serializable");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }
}
