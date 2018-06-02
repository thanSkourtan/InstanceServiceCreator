package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.BeanType;
import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.util.List;
import java.util.Set;

import com.eurobank.util.UtilityMethods;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BeanClassGenerator extends MainFileGenerator{
    public BeanClassGenerator(JRequestResponseObjectsClassData mainclassdata) {
        super(mainclassdata);
    }

    /*private BeanType dataFromXml;
    private String bRespFullClassName;
    private String bReqFullClassName;
    private JPackage reqRespPackage;
    private String reqRespPackageName;*/

//    public BeanClassGenerator (BeanType dataFromXml, Set<String> dataTypeClasses){
//        super(dataFromXml.getBeanClass());
//        this.dataFromXml = dataFromXml;
//
//        dataTypeClasses.forEach( x -> {
//            if(isABReqClassName(x)) {
//                this.bReqFullClassName = getClassName(x);
//            }else if(isABRespClassName(x)){
//                this.bRespFullClassName = getClassName(x);
//                this.reqRespPackageName = getPackageName(x);
//            }
//        });
//
//    }

    @Override
    public void generateOuterPackages() {

    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {

    }

    @Override
    public void generateOuterFieldsAndMethods() {

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {

    }
/*
    @Override
    public void generateOuterPackages() {
        mainPackage = mainModel._package(currentPackageName);
        helperPackage1 = outerModel._package("it.ibm.eurobank.bean.base");
        reqRespPackage = outerModel._package(reqRespPackageName);
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException{
        jDefinedClass = mainPackage._class(currentClassName);
    }

    @Override
    public void generateOuterFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {


        jDefinedClass.field(JMod.PRIVATE | JMod.FINAL | JMod.STATIC, mainModel.LONG, "serialVersionUID", JExpr.lit(1L));

        //Fields, Getters and Setters
        JDefinedClass bReqClass = reqRespPackage._class(bReqFullClassName);
        JDefinedClass bRespClass = reqRespPackage._class(bRespFullClassName);

        JFieldVar sendField = jDefinedClass.field(JMod.PRIVATE, bReqClass, "send");
        JFieldVar receiveField = jDefinedClass.field(JMod.PRIVATE, bRespClass, "receive");

        JMethod getSendMethod = jDefinedClass.method(JMod.PUBLIC, sendField.type(), "getSend");
        getSendMethod.body()._return(sendField);

        JMethod setSendMethod = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "setSend");
        setSendMethod.param(sendField.type(), sendField.name());
        setSendMethod.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));

        JMethod getReceiveMethod = jDefinedClass.method(JMod.PUBLIC, receiveField.type(), "getReceive");
        getReceiveMethod.body()._return(receiveField);

        JMethod setReceiveMethod = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "setReceive");
        setReceiveMethod.param(receiveField.type(), receiveField.name());
        setReceiveMethod.body().assign(JExpr._this().ref(receiveField.name()), JExpr.ref(receiveField.name()));

        //The 3 Constructors
        JMethod firstConstructor = jDefinedClass.constructor(JMod.PUBLIC);
        firstConstructor.param(sendField.type(), "send");
        firstConstructor.param(receiveField.type(), "receive");
        firstConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
        firstConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr.ref(receiveField.name()));

        JMethod secondConstructor = jDefinedClass.constructor(JMod.PUBLIC);
        secondConstructor.param(sendField.type(), "send");
        secondConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
        secondConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr._new(bRespClass));

        JMethod thirdConstructor = jDefinedClass.constructor(JMod.PUBLIC);
        thirdConstructor.body().assign(JExpr._this().ref(sendField.name()), JExpr._new(bReqClass));
        thirdConstructor.body().assign(JExpr._this().ref(receiveField.name()), JExpr._new(bRespClass));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException{
        JDefinedClass superclass = helperPackage1._class("ABaseAS400Bean");
        jDefinedClass._extends(superclass);
    }
*/
}
