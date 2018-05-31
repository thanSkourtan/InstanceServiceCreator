package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.ISERIESJ2CType;
import com.eurobank.JAXBmodel.OperationType;
import com.sun.codemodel.*;
import jdk.nashorn.internal.scripts.JD;

import java.util.Set;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class OperationExitClassGenerator extends MainFileGenerator{

    private JPackage helperPackage2;

    public OperationExitClassGenerator(ISERIESJ2CType dataFromXml, Set<String> allClassNamesSet) {
        super(dataFromXml.getUserExitClass());
    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(currentPackageName);
        helperPackage1 = secondaryModel._package("com.ibm.brm.server.operation");
        helperPackage2 = secondaryModel._package("com.ibm.brm.exception");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        jDefinedClass = mainPackage._class(currentClassName);
    }

    @Override
    public void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {
        JDefinedClass methodExceptionClass = helperPackage2._class("BRMException");

        JMethod executeAfterCallMethod = jDefinedClass.method(JMod.PUBLIC, mainModel.INT, "executeAfterCall");
        executeAfterCallMethod.annotate(Override.class);
        executeAfterCallMethod.param(Object.class, "mainBean");
        executeAfterCallMethod._throws(methodExceptionClass);
        executeAfterCallMethod.body()._return(JExpr.lit(0));

        JMethod executeBeforeCallMethod = jDefinedClass.method(JMod.PUBLIC, mainModel.INT, "executeBeforeCall");
        executeBeforeCallMethod.annotate(Override.class);
        executeBeforeCallMethod.param(Object.class, "mainBean");
        executeAfterCallMethod._throws(methodExceptionClass);
        executeBeforeCallMethod.body()._return(JExpr.lit(0));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass implementingInterface = helperPackage1._class("OperationStepExitInterface");
        jDefinedClass._implements(implementingInterface);
    }
}
