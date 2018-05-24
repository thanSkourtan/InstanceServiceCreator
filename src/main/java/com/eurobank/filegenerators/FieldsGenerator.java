package com.eurobank.filegenerators;

import com.sun.codemodel.*;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class FieldsGenerator {


    public void createFields(JDefinedClass jDefinedClass, JCodeModel mainModel){
        //JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));
        JFieldVar sendField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "send");
        JFieldVar receiveField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "receive");
        JMethod getVar = jDefinedClass.method(JMod.PUBLIC, sendField.type(), "getSend");
        getVar.body()._return(sendField);

        JMethod setVar = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "setSend");
        setVar.param(sendField.type(), sendField.name());
        setVar.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
    }

    public void createGetters(){

    }

    public void createSetters(){

    }
}
