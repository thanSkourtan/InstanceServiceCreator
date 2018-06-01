package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.JAXBmodel.FieldType;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class FieldsGenerator {

    //todo: erase mainModel, dataTypeClasses if not needed
    public void createFields(JDefinedClass jDefinedClass, JCodeModel mainModel, List<DataSetType> data, Set<String> dataTypeClasses) throws ClassNotFoundException, JClassAlreadyExistsException {

        jDefinedClass.field(JMod.PRIVATE | JMod.FINAL | JMod.STATIC, mainModel.LONG, "serialVersionUID", JExpr.lit(1L));

        for(DataSetType d : data) {
            for(FieldType f : d.getField()){

                JFieldVar tempVar1;
                if(f.getMatchDataSetName() != null){

                    JFieldVar tempVar2;
                    String dtoClassFullName = dataTypeClasses
                            .stream()
                            .filter(x -> isADTOClassName(x) &&  x.contains("brm"))
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);

                    JCodeModel tempModel = new JCodeModel();
                    JPackage jp = tempModel._package(getPackageName(dtoClassFullName));
                    JDefinedClass tempClass  = jp._class(getClassName(dtoClassFullName));

                    tempVar1 = jDefinedClass.field(JMod.PRIVATE, Vector.class, f.getName());
                    tempVar2 = jDefinedClass.field(JMod.PRIVATE, tempClass.array(), makeFirstCharacterLowercase(f.getMatchDataSetName()));

                    createGettersAndSettersMethods (jDefinedClass, tempVar1);
                    createGettersAndSettersMethods (jDefinedClass, tempVar2);
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
                    createGettersAndSettersMethods (jDefinedClass, tempVar1);
                }
            }
            return;
        }

    }


    public void createGettersAndSettersMethods (JDefinedClass jDefinedClass, JFieldVar x) {
        JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, x.type(), "get" + x.name());
        tempGetter.body()._return(x);
        JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, jDefinedClass.owner().VOID, "set" + x.name());
        tempSetter.param(x.type(), x.name());
        tempSetter.body().assign(JExpr._this().ref(x.name()), JExpr.ref(x.name()));


    }


}
