package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BReqClassGenerator extends MainFileGenerator{

    private List<DataSetType> dataFromXml;
    private FieldsGenerator fieldsGenerator;
    private Set<String> dataTypeClasses;

    public BReqClassGenerator(String fullClassName, List<DataSetType> dataFromXml, Set<String> dataTypeClasses) {
        super(fullClassName);
        this.dataFromXml = dataFromXml;
        this.fieldsGenerator = new FieldsGenerator();
        this.dataTypeClasses = dataTypeClasses;
    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(currentPackageName);
        helperPackage1 = secondaryModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        jDefinedClass = mainPackage._class(currentClassName);
    }

    @Override
    public void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {

        fieldsGenerator.createFields(jDefinedClass, mainModel, dataFromXml, dataTypeClasses);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = helperPackage1._class("ABaseAS400InputDataBean");
        jDefinedClass._extends(superclass);
    }


}
