package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DTOClassGenerator extends MainFileGenerator{

    private List<DataSetType> dataFromXml;
    private FieldsGenerator fieldsGenerator;
    private Set<String> dataTypeClasses;

    public DTOClassGenerator(String fullClassName, List<DataSetType> dataFromXml, Set<String> dataTypeClasses) {
        super(fullClassName);
        this.dataFromXml = dataFromXml;
        this.fieldsGenerator = new FieldsGenerator();
        this.dataTypeClasses = dataTypeClasses;
    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(packageName);
        secondaryPackage = secondaryModel._package("java.io");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        jDefinedClass = mainPackage._class(className);
    }

    @Override
    public void generateConstructors() {

    }

    @Override
    public void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {

        fieldsGenerator.createFields(jDefinedClass, mainModel, dataFromXml, dataTypeClasses);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = secondaryPackage._class("Serializable");
        jDefinedClass._extends(superclass);
    }
}
