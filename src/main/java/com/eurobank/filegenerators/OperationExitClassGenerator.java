package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.ISERIESJ2CType;
import com.eurobank.generatedclassnamesprocessors.ClassesPackagesStore;
import com.sun.codemodel.*;


import java.util.Set;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class OperationExitClassGenerator extends MainFileGenerator{

    private JPackage helperPackage2;
    private JPackage helperPackage3;
    private JPackage helperPackage4;
    private JPackage helperPackage5;
    private JPackage helperPackage6;
    private JPackage helperPackage7;
    private JPackage helperPackage8;
    private JPackage helperPackage9;
    private JPackage helperPackage10;
    private JPackage jBRespPackage;
    private JDefinedClass jBRespClass;
    private JPackage jBReqPackage;
    private JDefinedClass jBReqClass;
    private JPackage jBeanPackage;
    private JDefinedClass jBeanClass;
    ClassesPackagesStore store;

    public OperationExitClassGenerator(ISERIESJ2CType dataFromXml, ClassesPackagesStore store) {
        super(dataFromXml.getUserExitClass());
        this.store = store;
    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(currentPackageName);
        helperPackage1 = secondaryModel._package("com.ibm.brm.server.operation");
        helperPackage2 = secondaryModel._package("com.ibm.brm.exception");
        helperPackage3 = secondaryModel._package("java.lang");
        helperPackage4 = secondaryModel._package("com.ibm.brm.efg.exception");
        helperPackage5 = secondaryModel._package("it.ibm.eurobank.bean.base");
        helperPackage6 = secondaryModel._package("com.efg.errors");
        helperPackage7 = secondaryModel._package("com.efg.errors");
        jBRespPackage = secondaryModel._package(store.getBRespPackage());
        jBReqPackage = secondaryModel._package(store.getBReqPackage());
        jBeanPackage = secondaryModel._package(store.getBeanPackage());
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        jDefinedClass = mainPackage._class(currentClassName);
        this.jBRespClass = helperPackage8._class(store.getBRespClass());
        this.jBReqClass = helperPackage9._class(store.getBReqClass());
        this.jBeanClass = helperPackage10._class(store.getBeanClass());
    }

    @Override
    public void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {
        JDefinedClass brmExceptionClass = helperPackage2._class("BRMException");
        JDefinedClass exceptionClass = helperPackage3._class("Exception");
        JDefinedClass efgBrmApplicationException = helperPackage4._class("EFGBrmApplicationException");
        JDefinedClass aBaseBeanClass = helperPackage5._class("ABaseBean");
        JMethod getBRMTransactionNameMethod = aBaseBeanClass.method(JMod.PUBLIC, mainModel.VOID, "getBRMTransactionName");
        JDefinedClass efgHostErrorClass = helperPackage6._class("EFGHostError");
        JMethod efgHostErrorStaticMethod = efgHostErrorClass.method(JMod.PUBLIC, efgHostErrorClass,"createEFGHostErrorForBRM");
        JDefinedClass BRMErrorTypeClass = helperPackage7._enum("BRMErrorType");
        JEnumConstant BRM_LAST_BEFORE_CONSTANT = BRMErrorTypeClass.enumConstant("BRM_LAST_BEFORE");


        String[] methodNames = new String[] {"executeAfterCall", "executeBeforeCall"};

        for(int i = 0 ; i < methodNames.length ; i++) {
            JMethod executeBeforeCallMethod = jDefinedClass.method(JMod.PUBLIC, mainModel.INT, methodNames[i]);
            executeBeforeCallMethod.annotate(Override.class);
            executeBeforeCallMethod.param(Object.class, "mainBean");
            executeBeforeCallMethod._throws(brmExceptionClass);

            JTryBlock jTryBlock = executeBeforeCallMethod.body()._try();
            fillInTryBlock(jTryBlock.body());

            JCatchBlock jCatchBlock =  jTryBlock._catch(exceptionClass);
            JVar exException = jCatchBlock.param("ex");
            JBlock catchBody = jCatchBlock.body();
            JVar myABaseBeanVar = catchBody.decl(aBaseBeanClass, "myABaseBean");
            myABaseBeanVar.init(JExpr.cast(aBaseBeanClass , JExpr.ref("mainBean")));
            JVar hostError = catchBody.decl(efgHostErrorClass, "hostError");

            JInvocation efgHostErrorInvocation = efgHostErrorClass.staticInvoke(efgHostErrorStaticMethod)
                                                                    .arg(BRM_LAST_BEFORE_CONSTANT)
                                                                    .arg(myABaseBeanVar.invoke(getBRMTransactionNameMethod))
                                                                    .arg(exException);

            hostError.init(efgHostErrorInvocation);
            catchBody._throw(JExpr._new(efgBrmApplicationException).arg(hostError));
            executeBeforeCallMethod.body()._return(JExpr.lit(0));
        }







    }

    public void fillInTryBlock (JBlock body) throws JClassAlreadyExistsException {

        JMethod tempMethod1 = jBeanClass.method(JMod.PUBLIC, jBRespClass, "getSend");
        JMethod tempMethod2 = jBeanClass.method(JMod.PUBLIC, jBRespClass, "getReceive");

        JVar tempVar = body.decl(jBeanClass, "myBean");
        tempVar.init(JExpr.cast(jBeanClass , JExpr.ref("mainBean")));

        body.decl(jBReqClass, "mySend").init(tempVar.invoke(tempMethod1));
        body.decl(jBRespClass, "myReceive").init(tempVar.invoke(tempMethod2));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass implementingInterface = helperPackage1._class("OperationStepExitInterface");
        jDefinedClass._implements(implementingInterface);
    }
}
