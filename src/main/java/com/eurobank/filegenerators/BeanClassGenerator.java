package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.BeanType;
import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.util.List;

import com.eurobank.util.UtilityMethods;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BeanClassGenerator extends MainFileGenerator{

    private BeanType dataFromXml;
    private String bRespFullClassName;
    private String bReqFullClassName;
    private JPackage reqRespPackage;
    private String reqRespPackageName;

    public BeanClassGenerator (BeanType dataFromXml, List<String> dataTypeClasses){
        super(dataFromXml.getBeanClass());
        this.dataFromXml = dataFromXml;

        dataTypeClasses.forEach( x -> {
            if(x.endsWith("BReq")) {
                this.bReqFullClassName = UtilityMethods.getClassName(x);
            }else if(x.endsWith("BResp")){
                this.bRespFullClassName = UtilityMethods.getClassName(x);
                this.reqRespPackageName = UtilityMethods.getPackageName(x);
            }
        });

    }

    @Override
    public void generatePackages() {
        mainPackage = mainModel._package(packageName);
        secondaryPackage = secondaryModel._package("it.ibm.eurobank.bean.base");
        reqRespPackage = secondaryModel._package(reqRespPackageName);
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException{
        jDefinedClass = mainPackage._class(className);
    }

    @Override
    public void generateConstructors() throws JClassAlreadyExistsException {


    }

    @Override
    public void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException {

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

        /*Constructors*/
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
        JDefinedClass superclass = secondaryPackage._class("ABaseAS400Bean");
        jDefinedClass._extends(superclass);
    }

}
