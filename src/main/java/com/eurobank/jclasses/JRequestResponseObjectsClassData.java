package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.JAXBmodel.FieldType;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import static com.eurobank.util.DataSetTypesMerger.*;
import static com.eurobank.util.UtilityMethods.*;


/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public class JRequestResponseObjectsClassData extends JMainFileClassData{

    public JRequestResponseObjectsClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
        buildJFieldsAndJMethods(dataProcessing());
    }

    @Override
    public Object dataProcessing() {
        Map<String, List<DataSetType>> mergedDataSetTypes = mergeDataSets(dataFromXml.getDataSet());
        List<DataSetType> classSpecificDataSetsList = mergedDataSetTypes.get(canonicalName);
        return classSpecificDataSetsList;
    }

    @Override
    public void buildJFieldsAndJMethods(Object classSpecificDataSetsList) throws JClassAlreadyExistsException {

        List<DataSetType> data = (List<DataSetType>) classSpecificDataSetsList;

        for (DataSetType d : data) {
            for (FieldType f : d.getField()) {

                JFieldVar tempVar1;
                if (f.getMatchDataSetName() != null) {
                    JFieldVar tempVar2;
//                    String dtoClassFullName = dataTypeClasses
//                            .stream()
//                            .filter(x -> isADTOClassName(x) && x.contains("brm"))
//                            .findFirst()
//                            .orElseThrow(IllegalArgumentException::new);
//
//                    JCodeModel tempModel = new JCodeModel();
//                    JPackage jp = tempModel._package(getPackageName(dtoClassFullName));
//                    JDefinedClass tempClass = jp._class(getClassName(dtoClassFullName));


//                    tempVar1 = jDefinedClass.field(JMod.PRIVATE, Vector.class, f.getName());
//                    tempVar2 = jDefinedClass.field(JMod.PRIVATE, tempClass.array(), makeFirstCharacterLowercase(f.getMatchDataSetName()));
//
//                    createGettersAndSettersMethods(jDefinedClass, tempVar1);
//                    createGettersAndSettersMethods(jDefinedClass, tempVar2);
                } else {
                    Class<?> fieldClass = getFieldClass(f);
                    tempVar1 = jDefinedClass.field(JMod.PRIVATE, fieldClass, f.getName());
                    fieldsMap.put(f.getName(), tempVar1);
                    createGettersAndSettersMethods(jDefinedClass, tempVar1);
                }
            }
            return;
        }

    }

    public Class<?> getFieldClass(FieldType f) {
        if (f.getFormatClassParm().startsWith("X")) {
            return String.class;
        } else if (f.getFormatClassParm().startsWith("9") && f.getFormatClassParm().contains("V9")) {
            return Double.class;
        } else if (f.getFormatClassParm().startsWith("9")) {
            return Integer.class;
        } else {
            //todo: throw application exception
        }
        return null;
    }

    public void createGettersAndSettersMethods (JDefinedClass jDefinedClass, JFieldVar x) {
        JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, x.type(), "get" + x.name());
        tempGetter.body()._return(x);
        methodsMap.put("get" + x.name(), tempGetter);
        JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, jDefinedClass.owner().VOID, "set" + x.name());
        tempSetter.param(x.type(), x.name());
        tempSetter.body().assign(JExpr._this().ref(x.name()), JExpr.ref(x.name()));
        methodsMap.put("set" + x.name(), tempSetter);


    }

}
