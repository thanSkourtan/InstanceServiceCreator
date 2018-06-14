package com.eurobank.as400filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BeanClassGenerator extends MainFileGenerator{
    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;
    private String canonicalName;

    public BeanClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        this.canonicalName = canonicalName;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base");
    }

    @Override
    public void generateOuterFieldsAndMethods() {
        JDefinedClass mainClassTempInstance = mainclassdata.getjDefinedClass();
        mainClassTempInstance.field(JMod.PRIVATE | JMod.FINAL | JMod.STATIC, mainclassdata.getMainModel().LONG, "serialVersionUID", JExpr.lit(1L));

        //Fields, Getters and Setters

        JFieldVar sendField = mainClassTempInstance.field(JMod.PRIVATE, jClassesMap.get("BReq").getjDefinedClass(), "send");
        JFieldVar receiveField = mainClassTempInstance.field(JMod.PRIVATE, jClassesMap.get("BReq").getjDefinedClass(), "receive");

        JMethod getSendMethod = mainClassTempInstance.method(JMod.PUBLIC, sendField.type(), "getSend");
        getSendMethod.body()._return(sendField);

        JMethod setSendMethod = mainClassTempInstance
                .method(JMod.PUBLIC, mainclassdata.getMainModel().VOID, "setSend");
        setSendMethod.param(sendField.type(), sendField.name());
        setSendMethod.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));

        JMethod getReceiveMethod = mainClassTempInstance
                .method(JMod.PUBLIC, receiveField.type(), "getReceive");
        getReceiveMethod.body()._return(receiveField);

        JMethod setReceiveMethod = mainClassTempInstance
                .method(JMod.PUBLIC, mainclassdata.getMainModel().VOID, "setReceive");
        setReceiveMethod.param(receiveField.type(), receiveField.name());
        setReceiveMethod.body().assign(JExpr._this().ref(receiveField.name()), JExpr.ref(receiveField.name()));

        //The 3 Constructors
        JMethod firstConstructor = mainClassTempInstance.constructor(JMod.PUBLIC);
        firstConstructor.param(sendField.type(), "send");
        firstConstructor.param(receiveField.type(), "receive");
        firstConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
        firstConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr.ref(receiveField.name()));

        JMethod secondConstructor = mainClassTempInstance.constructor(JMod.PUBLIC);
        secondConstructor.param(sendField.type(), "send");
        secondConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
        secondConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr._new(jClassesMap.get("BReq").getjDefinedClass()));

        JMethod thirdConstructor = mainClassTempInstance.constructor(JMod.PUBLIC);
        thirdConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr._new(jClassesMap.get("BReq").getjDefinedClass()));
        thirdConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr._new(jClassesMap.get("BReq").getjDefinedClass()));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400Bean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }
}