package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.sun.codemodel.*;

import java.util.List;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BReqClassGenerator extends MainFileGenerator{

    private List<DataSetType> dataFromXml;
    private FieldsGenerator fieldsGenerator;

    public BReqClassGenerator(String fullClassName, List<DataSetType> dataFromXml) {
        super(fullClassName);
        this.dataFromXml = dataFromXml;
        this.fieldsGenerator = new FieldsGenerator();
    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(packageName);
        secondaryPackage = secondaryModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        jDefinedClass = mainPackage._class(className);
    }

    @Override
    public void generateConstructors() {

    }

    @Override
    public void generateFieldsAndMethods() {

        fieldsGenerator.createFields(jDefinedClass, mainModel, dataFromXml);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = secondaryPackage._class("ABaseAS400InputDataBean");
        jDefinedClass._extends(superclass);
    }


}
