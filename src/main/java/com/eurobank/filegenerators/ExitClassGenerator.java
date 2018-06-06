package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JExitClassData;
import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static com.eurobank.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class ExitClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private JPackage outerPackage2;
    private JPackage outerPackage3;
    private JPackage outerPackage4;
    private JPackage outerPackage5;
    private JPackage outerPackage6;
    private JPackage outerPackage7;
    private Map<String, JMainFileClassData> jClassesMap;

    public ExitClassGenerator(Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        generateAll();
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
    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {
        JExitClassData tempMainClass = (JExitClassData) mainclassdata;
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
            // Code specific only for first method
            JVar[] declaredJVars1 = new JVar[0];
            if (methodNames[i].equals("executeAfterCall")){
                declaredJVars1 = fillInBlock(executeBeforeCallMethod.body());
            }
            executeBeforeCallMethod.annotate(Override.class);
            executeBeforeCallMethod.param(Object.class, "mainBean");
            executeBeforeCallMethod._throws(brmExceptionClass);

            JTryBlock jTryBlock = executeBeforeCallMethod.body()._try();

            // Code specific only for first method
            if (methodNames[i].equals("executeAfterCall")){
                JClass arrayListClass = mainclassdata.getMainModel().ref(ArrayList.class);//narrow if I want generics
                JVar arrayListJVar = jTryBlock.body().decl(arrayListClass, "tempArrayList", JExpr._new(arrayListClass));



                String vectorMethodsName = null;
                for (Map.Entry<String, JMethod> temp: jClassesMap.get("BResp").getMethodsMap().entrySet()) {
                    if(temp.getValue().type().name().equals("Vector")){
                        vectorMethodsName = temp.getValue().name();
                    }
                }

                if(vectorMethodsName != null) {
                    // For Loop
                    JForLoop forLoop = jTryBlock.body()._for();
                    JVar ivar = forLoop.init(mainclassdata.getMainModel().INT, "i", JExpr.lit(0));

                    forLoop.test(ivar.lt(JExpr.invoke(declaredJVars1[1], vectorMethodsName).invoke("size")));
                    forLoop.update(ivar.assignPlus(JExpr.lit(1)));

                    //todo : see here what happens in case of two DTOs, IF SUCH CASE CAN EXIST
                    JType tempJtype = jClassesMap.get("BRMDTO").getjDefinedClass();
                    JVar entryJVar = forLoop.body().decl(tempJtype,"currentEntry");
                    entryJVar.init(JExpr.cast(tempJtype, JExpr.invoke(declaredJVars1[1], vectorMethodsName).invoke("get").arg(ivar)));
                    forLoop.body().invoke(arrayListJVar,"add").arg(entryJVar);


                    JVar tempDTOArray = jTryBlock.body().decl(tempJtype.array(),
                            makeFirstCharacterLowercase(tempJtype.name()) + "Array");
                    tempDTOArray.init(JExpr.cast(tempJtype.array() , arrayListJVar.invoke("toArray").arg(JExpr.newArray(tempJtype, arrayListJVar.invoke("size")))));


                    String tempMethodName = null;

                    for(Map.Entry<String, JMethod> temp : jClassesMap.get("BResp").getMethodsMap().entrySet()) {
                        if (temp.getValue().params().size()!=0 &&
                                temp.getValue().params().get(0).type().name()
                                        .startsWith(getClassName(jClassesMap.get("BRMDTO").getCanonicalName()))) {
                            tempMethodName = temp.getValue().name();
                        }
                    }
                    jTryBlock.body().invoke(declaredJVars1[1], tempMethodName).arg(tempDTOArray);
                }



            }

            // Code specific only for second method
            if(methodNames[i].equals("executeBeforeCall")){
                JVar[] declaredJVars2 = fillInBlock(jTryBlock.body());

                jTryBlock.body().invoke(declaredJVars2[0],"setActionCode").arg("00");
                jTryBlock.body().invoke(declaredJVars2[0],"setTransaction").arg(tempMainClass.getTransactionId());

                Map<String, JMethod> bReqMethodsMap = jClassesMap.get("BReq").getMethodsMap();
                bReqMethodsMap.forEach((k,v) -> {
                    if(k.startsWith("set"))
                        jTryBlock.body().invoke(declaredJVars2[0],k)
                                .arg(JExpr.invoke(declaredJVars2[0], k.replace("set", "get")));
                });
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

    public JVar[] fillInBlock (JBlock body) throws JClassAlreadyExistsException {

        JMethod tempMethod1 = jClassesMap.get("Bean").getjDefinedClass().method(JMod.PUBLIC, jClassesMap.get("BResp").getjDefinedClass(), "getSend");
        JMethod tempMethod2 = jClassesMap.get("Bean").getjDefinedClass().method(JMod.PUBLIC, jClassesMap.get("BResp").getjDefinedClass(), "getReceive");

        JVar myBeanJVar = body.decl(jClassesMap.get("Bean").getjDefinedClass(), "myBean");
        myBeanJVar.init(JExpr.cast(jClassesMap.get("Bean").getjDefinedClass() , JExpr.ref("mainBean")));

        JVar mySendJVar = body.decl(jClassesMap.get("BReq").getjDefinedClass(), "mySend");
        mySendJVar.init(myBeanJVar.invoke(tempMethod1));
        JVar myReceiveVar = body.decl(jClassesMap.get("BResp").getjDefinedClass(), "myReceive");
        myReceiveVar.init(myBeanJVar.invoke(tempMethod2));

        return new JVar[]{mySendJVar, myReceiveVar, myBeanJVar};
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass implementingInterface = outerPackage1._class("OperationStepExitInterface");
        mainclassdata.getjDefinedClass()._implements(implementingInterface);
    }
}
