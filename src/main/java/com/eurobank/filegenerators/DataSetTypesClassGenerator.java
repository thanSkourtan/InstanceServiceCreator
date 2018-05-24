package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.BeanType;
import com.eurobank.JAXBmodel.DataSetType;
import com.sun.codemodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.util.stream.Collectors.*;

import static com.eurobank.util.UtilityMethods.getClassName;
import static com.eurobank.util.UtilityMethods.getPackageName;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 *
 * BReq, BResp and all DTOs are created in here
 *
 *
 */
public class DataSetTypesClassGenerator extends MainFileGenerator{


    public DataSetTypesClassGenerator(Object key, Object dataFromXml){

        this.dataFromXml = dataFromXml;


    }

    @Override
    public void dataProcessing() {
        if(!(dataFromXml instanceof List)) {
            System.exit(-1);
        }
        List<DataSetType> data = (List<DataSetType>) dataFromXml;




//        String classFullName = data.getBeanClass();
        String classFullName = "";
        packageName = getPackageName(classFullName);
        className = getClassName(classFullName);
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
        JDefinedClass superclass = secondaryPackage._class("ABaseAS400InputDataBean");
        jDefinedClass._extends(superclass);
    }

    @Override
    public void generateJavadocs() {
        jDefinedClass.javadoc().add("Automatically created by Instant Service Creator.");
    }
}
