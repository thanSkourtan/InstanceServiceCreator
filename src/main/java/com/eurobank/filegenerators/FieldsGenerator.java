package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.JAXBmodel.FieldType;
import com.sun.codemodel.*;

import java.util.List;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class FieldsGenerator {


    public void createFields(JDefinedClass jDefinedClass, JCodeModel mainModel, List<DataSetType> data){

        //JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));

        int totalNumberOfFields = 0;
        for(DataSetType d : data) {
            totalNumberOfFields += d.getField().size();
        }

        JFieldVar[] allFields = new JFieldVar[totalNumberOfFields];
        int i = 0;
        for(DataSetType d : data) {
            for(FieldType f : d.getField()){
                allFields[i++] = jDefinedClass.field(JMod.PRIVATE,
                        f.getFormatClassParm().startsWith("X") ? String.class :
                                f.getFormatClassParm().startsWith("9") && f.getFormatClassParm().contains("V9") ? Double.class : Integer.class,
                        f.getName());
            }

        }

//        JFieldVar sendField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "send");
//        JFieldVar receiveField = jDefinedClass.field(JMod.PRIVATE, Integer.class, "receive");



    }

    public void createGetters(){
//        JMethod getVar = jDefinedClass.method(JMod.PUBLIC, sendField.type(), "getSend");
//        getVar.body()._return(sendField);
    }

    public void createSetters(){
//        JMethod setVar = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "setSend");
//        setVar.param(sendField.type(), sendField.name());
//        setVar.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));
    }
}
