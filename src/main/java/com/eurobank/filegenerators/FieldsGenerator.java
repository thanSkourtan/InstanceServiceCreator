package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.JAXBmodel.FieldType;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Vector;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class FieldsGenerator {


    public void createFields(JDefinedClass jDefinedClass, JCodeModel mainModel, List<DataSetType> data, Set<String> dataTypeClasses) throws ClassNotFoundException, JClassAlreadyExistsException {
        Optional<String> dtoClass = dataTypeClasses.stream().filter(x -> !isABRespClassName(x) && !isABReqClassName(x)).findFirst();
        //JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));
        JDefinedClass dtoClassString = mainModel._class(dtoClass.get());

        for(DataSetType d : data) {
            for(FieldType f : d.getField()){

                JFieldVar tempVar1 = null;
                JFieldVar tempVar2 = null;
                if(f.getMatchDataSetName() != null){
                    //TODO:Add logic
                    tempVar1 = jDefinedClass.field(JMod.PRIVATE, Vector.class, f.getName());
                    tempVar2 = jDefinedClass.field(JMod.PRIVATE, dtoClassString, f.getMatchDataSetName());
                } else {
                    Class<?> fieldClass = null;
                    if(f.getFormatClassParm().startsWith("X")) {
                        fieldClass = String.class;
                    } else if (f.getFormatClassParm().startsWith("9") && f.getFormatClassParm().contains("V9")) {
                        fieldClass = Double.class;
                    } else if (f.getFormatClassParm().startsWith("9")) {
                        fieldClass = Integer.class;
                    } else {
                        //todo: throw application exception

                    }
                    tempVar1 = jDefinedClass.field(JMod.PRIVATE, fieldClass, f.getName());
                }

                JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, tempVar1.type(), "get" + tempVar1.name());
                tempGetter.body()._return(tempVar1);
                JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, mainModel.VOID, "set" + tempVar1.name());
                tempSetter.param(tempVar1.type(), tempVar1.name());
                tempSetter.body().assign(JExpr._this().ref(tempVar1.name()), JExpr.ref(tempVar1.name()));

            }
            return;
        }

    }


}
