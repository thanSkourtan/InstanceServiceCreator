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

    private String matchDataSetName;

    public JRequestResponseObjectsClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
        buildJFieldsAndJMethods(dataProcessing());
    }

    @Override
    public Object dataProcessing() {
        String tempName = canonicalName; //So that we do not change the canonical name
        Map<String, List<DataSetType>> mergedDataSetTypes = mergeDataSets(dataFromXml.getDataSet());
        List<DataSetType> classSpecificDataSetsList = mergedDataSetTypes.get(isAnESBDTOClassName(tempName)?
                                                            convertEsbDTOObjectClassToBrmClass(tempName):
                                                            isAnSReqClassName(tempName) || isAnSRespClassName(tempName)?
                                                            convertEsbObjectClassToBrmClass(tempName):tempName);
        return classSpecificDataSetsList;
    }

    @Override
    public void buildJFieldsAndJMethods(Object classSpecificDataSetsList) throws JClassAlreadyExistsException {

        jDefinedClass.field(JMod.PRIVATE | JMod.FINAL | JMod.STATIC, mainModel.LONG, "serialVersionUID", JExpr.lit(1L));
        List<DataSetType> data = (List<DataSetType>) classSpecificDataSetsList;

        for (DataSetType d : data) {
            for (FieldType f : d.getField()) {
                if(f.getMatchDataSetName() == null){
                    Class<?> fieldClass = getFieldClass(f);
                    JFieldVar tempVar1 = jDefinedClass.field(JMod.PRIVATE, fieldClass, f.getName());
                    fieldsMap.put(f.getName(), tempVar1);
                    createGettersAndSettersMethods(jDefinedClass, tempVar1);
                } else {
                    JFieldVar tempVar1 = jDefinedClass.field(JMod.PRIVATE, Vector.class, f.getName());
                    createGettersAndSettersMethods(jDefinedClass, tempVar1);
                    matchDataSetName = f.getMatchDataSetName();
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
        JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, x.type(), "get" + makeFirstCharacterCapitalcase(x.name()));
        tempGetter.body()._return(x);
        methodsMap.put("get" + makeFirstCharacterCapitalcase(x.name()), tempGetter);
        JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, jDefinedClass.owner().VOID, "set" + makeFirstCharacterCapitalcase(x.name()));
        tempSetter.param(x.type(), x.name());
        tempSetter.body().assign(JExpr._this().ref(x.name()), JExpr.ref(x.name()));
        methodsMap.put("set" + makeFirstCharacterCapitalcase(x.name()), tempSetter);


    }

    public String getMatchDataSetName() {
        return matchDataSetName;
    }

    public void setMatchDataSetName(String matchDataSetName) {
        this.matchDataSetName = matchDataSetName;
    }
}
