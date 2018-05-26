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

        for(DataSetType d : data) {
            for(FieldType f : d.getField()){


                if(f.getMatchDataSetName() != null){
                    //TODO:Add logic
                    return;
                }

                //TODO: FIX double types
                JFieldVar tempVar = jDefinedClass.field(JMod.PRIVATE,
                        f.getFormatClassParm().startsWith("X") ? String.class :
                                f.getFormatClassParm().startsWith("9") && f.getFormatClassParm().contains("V9") ? Double.class : Integer.class,
                        f.getName());

                JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, tempVar.type(), "get" + tempVar.name());
                tempGetter.body()._return(tempVar);
                JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "set" + tempVar.name());
                tempSetter.param(tempVar.type(), tempVar.name());
                tempSetter.body().assign(JExpr._this().ref(tempVar.name()), JExpr.ref(tempVar.name()));
            }

        }

    }


}
