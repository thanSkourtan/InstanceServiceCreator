package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.sun.codemodel.*;

import java.util.List;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BRespClassGenerator extends MainFileGenerator{

    private List<DataSetType> dataFromXml;
    private FieldsGenerator fieldsGenerator;

    public BRespClassGenerator(String fullClassName, List<DataSetType> dataFromXml) {
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
    public void generateFieldsAndMethods() {

        //JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));
        JFieldVar sendField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "send");
        JFieldVar receiveField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "receive");
        JMethod getVar = jDefinedClass.method(JMod.PUBLIC, sendField.type(), "getSend");
        getVar.body()._return(sendField);

        JMethod setVar = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "setSend");
        setVar.param(sendField.type(), sendField.name());
        setVar.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = secondaryPackage._class("ABaseAS400OutputDataBean");
        jDefinedClass._extends(superclass);
    }

}
