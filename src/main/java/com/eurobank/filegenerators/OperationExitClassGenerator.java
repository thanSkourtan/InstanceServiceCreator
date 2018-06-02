package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.JAXBmodel.ISERIESJ2CType;
import com.eurobank.generatedclassnamesprocessors.ClassesPackagesStore;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class OperationExitClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private JPackage outerPackage2;
    private JPackage outerPackage3;
    private JPackage outerPackage4;
    private JPackage outerPackage5;
    private JPackage outerPackage6;
    private JPackage outerPackage7;
    private JPackage jBRespPackage;
    private JDefinedClass jBRespClass;
    private JPackage jBReqPackage;
    private JDefinedClass jBReqClass;
    private JPackage jBeanPackage;
    private JDefinedClass jBeanClass;
    private ClassesPackagesStore store;
    private List<DataSetType> ReqDataSets;
    private List<DataSetType> RespDataSets;

    public OperationExitClassGenerator( JRequestResponseObjectsClassData mainclassdata) {
        super(mainclassdata);
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("com.ibm.brm.server.operation");
        outerPackage2 = outerModel._package("com.ibm.brm.exception");
        outerPackage3 = outerModel._package("java.lang");
        outerPackage4 = outerModel._package("com.ibm.brm.efg.exception");
        outerPackage5 = outerModel._package("it.ibm.eurobank.bean.base");
        outerPackage6 = outerModel._package("com.efg.errors");
        outerPackage7 = outerModel._package("com.efg.errors");
        jBRespPackage = outerModel._package(store.getBRespPackage());
        jBReqPackage = outerModel._package(store.getBReqPackage());
        jBeanPackage = outerModel._package(store.getBeanPackage());
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {
        this.jBRespClass = jBRespPackage._class(store.getBRespClass());
        this.jBReqClass = jBReqPackage._class(store.getBReqClass());
        this.jBeanClass = jBeanPackage._class(store.getBeanClass());
    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {
        JDefinedClass brmExceptionClass = outerPackage2._class("BRMException");
        JDefinedClass exceptionClass = outerPackage3._class("Exception");
        JDefinedClass efgBrmApplicationException = outerPackage4._class("EFGBrmApplicationException");
        JDefinedClass aBaseBeanClass = outerPackage5._class("ABaseBean");
        JMethod getBRMTransactionNameMethod = aBaseBeanClass.method(JMod.PUBLIC, mainclassdata.getMainModel().VOID, "getBRMTransactionName");
        JDefinedClass efgHostErrorClass = outerPackage6._class("EFGHostError");
        JMethod efgHostErrorStaticMethod = efgHostErrorClass.method(JMod.PUBLIC, efgHostErrorClass,"createEFGHostErrorForBRM");
        JDefinedClass BRMErrorTypeClass = outerPackage7._enum("BRMErrorType");
        JEnumConstant BRM_LAST_BEFORE_CONSTANT = BRMErrorTypeClass.enumConstant("BRM_LAST_BEFORE");





        String[] methodNames = new String[] {"executeAfterCall", "executeBeforeCall"};

        for(int i = 0 ; i < methodNames.length ; i++) {
            JMethod executeBeforeCallMethod = mainclassdata.getjDefinedClass().method(JMod.PUBLIC, mainclassdata.getMainModel().INT, methodNames[i]);
            /* Order of calls matters.*/
            if (methodNames[i].equals("executeAfterCall")){
                fillInBlock(executeBeforeCallMethod.body());
            }
            executeBeforeCallMethod.annotate(Override.class);
            executeBeforeCallMethod.param(Object.class, "mainBean");
            executeBeforeCallMethod._throws(brmExceptionClass);

            JTryBlock jTryBlock = executeBeforeCallMethod.body()._try();

            // Code specific only for second method
            if(methodNames[i].equals("executeBeforeCall")){
                JVar beanVar = fillInBlock(jTryBlock.body());



            }

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

    public JVar fillInBlock (JBlock body) throws JClassAlreadyExistsException {

        JMethod tempMethod1 = jBeanClass.method(JMod.PUBLIC, jBRespClass, "getSend");
        JMethod tempMethod2 = jBeanClass.method(JMod.PUBLIC, jBRespClass, "getReceive");

        JVar myBeanVar = body.decl(jBeanClass, "myBean");
        myBeanVar.init(JExpr.cast(jBeanClass , JExpr.ref("mainBean")));

        body.decl(jBReqClass, "mySend").init(myBeanVar.invoke(tempMethod1));
        body.decl(jBRespClass, "myReceive").init(myBeanVar.invoke(tempMethod2));

        return myBeanVar;
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass implementingInterface = outerPackage1._class("OperationStepExitInterface");
        mainclassdata.getjDefinedClass()._implements(implementingInterface);
    }
}
