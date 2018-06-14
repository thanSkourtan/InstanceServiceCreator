package com.eurobank.as400filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.io.IOException;
import java.util.Map;

import static com.eurobank.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class SPClassGenerator extends MainFileGenerator{
    private JPackage outerPackage1;
    private JPackage outerPackage2;
    private JPackage outerPackage3;
    private JPackage outerPackage4;
    private JPackage outerPackage5;
    private JPackage outerPackage6;
    private Map<String, JMainFileClassData> jClassesMap;

    public SPClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("com.efg.esb.baseServices");
        outerPackage2 = outerModel._package("com.ibm.gr.component.logger");
        outerPackage3 = outerModel._package("com.efg.brm");
        outerPackage4 = outerModel._package("it.ibm.eurobank.bean.base");
        outerPackage5 = outerModel._package("com.ibm.gr.services");
        outerPackage6 = outerModel._package("com.efg.esb.core");
    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {

        //class level annotation
        mainclassdata.getjDefinedClass().annotate(JMainFileClassData.getMainModel().ref("com.efg.esb.annotations.ESBService"))
                .param("defaultFlow", outerPackage6._class("ESBServiceFlows").staticRef("NoMonetaryReadOnlyFlow"));
        JMethod constructor = mainclassdata.getjDefinedClass().constructor(JMod.PUBLIC);
        JVar var1 = constructor.param(outerPackage2._class("Logger"), "pLogger");
        JVar var2 = constructor.param(outerPackage3._class( "BrmHelper"), "pBrmHelper");
        constructor.body().invoke("super").arg(var1).arg(var2);

        /*1st method*/
        JDefinedClass aBaseBeanJDefinedClass = outerPackage4._class( "ABaseBean");
        JMethod createBRMBeanJMethod = mainclassdata.getjDefinedClass()
                .method(JMod.PROTECTED,aBaseBeanJDefinedClass , "createBRMBean");
        JVar brmRequestJVar = createBRMBeanJMethod.param(Object.class, "brmRequest");
        createBRMBeanJMethod._throws(Exception.class);
        createBRMBeanJMethod.body()._return(JExpr._new(getJModelClass(jClassesMap, "Bean"))
                .arg(JExpr.cast(getJModelClass(jClassesMap, "BReq"),brmRequestJVar )));
        createBRMBeanJMethod.annotate(Override.class);

        /*2nd method*/
        JMethod createBRMReqJMethod = mainclassdata.getjDefinedClass()
                .method(JMod.PROTECTED, Object.class, "createBRMreq");
        JVar serviceRequestJVar = createBRMReqJMethod.param(outerPackage5._class("IServiceRequest"), "serviceRequest");
        createBRMReqJMethod._throws(Exception.class);
        createBRMReqJMethod.annotate(Override.class);


        JVar reqJVar = createBRMReqJMethod.body().decl(getJModelClass(jClassesMap, "SReq"), "req");
        reqJVar.init(JExpr.cast(getJModelClass(jClassesMap, "SReq") , JExpr.ref("serviceRequest")));

        JVar brmReqJVar = createBRMReqJMethod.body().decl(getJModelClass(jClassesMap, "BReq"), "brmReq");
        brmReqJVar.init(JExpr._new(getJModelClass(jClassesMap, "BReq")));

        Map<String, JMethod> bReqMethodsMap = jClassesMap.get("SReq").getMethodsMap();
        bReqMethodsMap.forEach((k,v) -> {
            if(k.startsWith("set"))
                createBRMReqJMethod.body().invoke(brmReqJVar,k)
                        .arg(JExpr.invoke(reqJVar, k.replace("set", "get")));
        });

        createBRMReqJMethod.body()._return(brmReqJVar);

        /*3rd method*/
        JMethod createESBResponseJMethod = mainclassdata.getjDefinedClass()
                .method(JMod.PROTECTED, outerPackage5._class("IServiceResponse"), "createESBResponse");
        createESBResponseJMethod.annotate(Override.class);
        createESBResponseJMethod._throws(Exception.class);
        createESBResponseJMethod.param(aBaseBeanJDefinedClass, "brmBean");

        JVar beanJVar = createESBResponseJMethod.body().decl(getJModelClass(jClassesMap, "Bean"), "bean");
        beanJVar.init(JExpr.cast(getJModelClass(jClassesMap, "Bean") , JExpr.ref("brmBean")));

        JVar brmRespJVar = createESBResponseJMethod.body().decl(getJModelClass(jClassesMap, "BResp"), "brmResp");
        brmRespJVar.init(JExpr.invoke(beanJVar, "getReceive"));

        JVar respJVar = createESBResponseJMethod.body().decl(getJModelClass(jClassesMap, "SResp"), "resp");
        respJVar.init(JExpr._new(getJModelClass(jClassesMap, "SResp")));

        Map<String, JMethod> bRespMethodsMap = jClassesMap.get("BResp").getMethodsMap();


        bRespMethodsMap.forEach((k,v) -> {
            if(jClassesMap.get("BRMDTO") == null) {
                if(k.startsWith("set")){
                    createESBResponseJMethod.body().invoke(respJVar,k).arg(JExpr.invoke(brmRespJVar, k.replace("set", "get")));
                }
            }
            else if(k.startsWith("set") && v.params().size() == 1 &&
                    !v.params().get(0).type().name().equals("Vector") &&
                            !v.params().get(0).type().name().replace("[]","").equals(getJModelClass(jClassesMap, "BRMDTO").name())){
                createESBResponseJMethod.body().invoke(respJVar,k).arg(JExpr.invoke(brmRespJVar, k.replace("set", "get")));
            } else if (k.startsWith("set") && v.params().size() == 1 &&
                    v.params().get(0).type().name().replace("[]","").equals(getJModelClass(jClassesMap, "BRMDTO").name())) {

                JInvocation getListJInvocation = JExpr.invoke(brmRespJVar, k.replace("set", "get"));

                JConditional condition = createESBResponseJMethod.body()._if(getListJInvocation.ne(JExpr._null())
                        .cand(getListJInvocation.ref("length").gt(JExpr.lit(0))));

                JVar esbDTOJVar = condition._then().decl(getJModelClass(jClassesMap, "ESBDTO").array(),
                        makeFirstCharacterLowercase(getJModelClass(jClassesMap, "ESBDTO").name()));

                esbDTOJVar.init(JExpr.newArray(getJModelClass(jClassesMap, "ESBDTO"), getListJInvocation.ref("length")));

                JForLoop forLoop = condition._then()._for();
                JVar ivar = forLoop.init(mainclassdata.getMainModel().INT, "i", JExpr.lit(0));
                forLoop.test(ivar.lt(getListJInvocation).ref("length"));
                forLoop.update(ivar.assignPlus(JExpr.lit(1)));

                forLoop.body().assign(esbDTOJVar.component(JExpr.ref("i")), JExpr._new(getJModelClass(jClassesMap,"ESBDTO")));

                jClassesMap.get("BRMDTO").getMethodsMap().forEach((key, value) -> {
                    if(value.name().startsWith("set")){
                        forLoop.body().invoke(esbDTOJVar.component(JExpr.ref("i")), value).arg(
                                JExpr.invoke(getListJInvocation.component(JExpr.ref("i")), value.name().replace("set", "get"))
                        );
                    }
                });
                condition._then().invoke(respJVar, k).arg(esbDTOJVar);

            }

        });





//        JClass listClass = mainclassdata.getMainModel().ref(List.class).narrow(getJModelClass(jClassesMap, "BRMDTO"));
//        JClass arrayListClass = mainclassdata.getMainModel().ref(ArrayList.class).narrow(getJModelClass(jClassesMap, "BRMDTO"));
//        JVar arrayListJVar = createESBResponseJMethod.body()
//                .decl(listClass, makeFirstCharacterLowercase(getJModelClass(jClassesMap, "BRMDTO").name()) + "List");
//        arrayListJVar.init(JExpr._new(arrayListClass));

//        JForLoop forLoop = createESBResponseJMethod.body()._for();
//        JVar ivar = forLoop.init(mainclassdata.getMainModel().INT, "i", JExpr.lit(0));
//
//        forLoop.test(ivar.lt(JExpr.invoke(brmRespJVar, vectorMethodsName).ref("length")));
//        forLoop.update(ivar.assignPlus(JExpr.lit(1)));
//
//        JConditional condition = forLoop.body()._if(respJVar.lt(JExpr.lit(42)));
//        condition._then().add(
//                JMainFileClassData.getMainModel().ref(System.class).staticRef("out").invoke("println").arg(JExpr.lit("hello")));


        //JVar brmRespJVar = createESBResponseJMethod.body().decl(getJModelClass(jClassesMap, "BRMDTO").array(), "");





        createESBResponseJMethod.body()._return(respJVar);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass implementingInterface = outerPackage1._class("AbstractBRMSP");
        mainclassdata.getjDefinedClass()._implements(implementingInterface);
    }
}
