package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public abstract class JMainFileClassData {

    protected JPackage jPackage;
    protected JDefinedClass jDefinedClass;
    protected static JCodeModel mainModel;
    protected final Map<String, JVar> fieldsMap = new HashMap<>();
    protected final Map<String, JMethod> methodsMap = new HashMap<>();
    protected BusinessRequestType dataFromXml;
    protected String canonicalName;

    static {
        mainModel = new JCodeModel();
    }

    public JMainFileClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        this.canonicalName = canonicalName;
        this.jPackage = mainModel._package(getPackageName(canonicalName));
        this.jDefinedClass = jPackage._class(getClassName(canonicalName));
        this.dataFromXml = dataFromXml;
    }

    public abstract Object dataProcessing();
    public abstract void buildJFieldsAndJMethods(Object data) throws JClassAlreadyExistsException;

    public JPackage getjPackage() {
        return jPackage;
    }

    public void setjPackage(JPackage jPackage) {
        this.jPackage = jPackage;
    }

    public JDefinedClass getjDefinedClass() {
        return jDefinedClass;
    }

    public void setjDefinedClass(JDefinedClass jDefinedClass) {
        this.jDefinedClass = jDefinedClass;
    }

    public static JCodeModel getMainModel() {
        return mainModel;
    }

    public static void setMainModel(JCodeModel mainModel) {
        JMainFileClassData.mainModel = mainModel;
    }

    public Map<String, JVar> getFieldsMap() {
        return fieldsMap;
    }

    public Map<String, JMethod> getMethodsMap() {
        return methodsMap;
    }

    public BusinessRequestType getDataFromXml() {
        return dataFromXml;
    }

    public void setDataFromXml(BusinessRequestType dataFromXml) {
        this.dataFromXml = dataFromXml;
    }
}
