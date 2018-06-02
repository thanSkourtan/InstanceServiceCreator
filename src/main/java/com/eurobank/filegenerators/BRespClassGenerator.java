package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BRespClassGenerator extends MainFileGenerator{

    private List<DataSetType> dataFromXml;
    private FieldsGenerator fieldsGenerator;
    private Set<String> dataTypeClasses;
    private JPackage outerPackage1;

    public BRespClassGenerator(JRequestResponseObjectsClassData mainclassdata) {
        super(mainclassdata);

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
