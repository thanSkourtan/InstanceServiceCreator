package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.exceptions.ApplicationException;
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
    private static JCodeModel brmMessagesCodeModel;
    private static JCodeModel brmBusinessLogicCodeModel;
    private static JCodeModel esbMessagesCodeModel;
    private static JCodeModel esbBusinessLogicCodeModel;
    protected static JCodeModel mainModel;
    protected final Map<String, JVar> fieldsMap = new HashMap<>();
    protected final Map<String, JMethod> methodsMap = new HashMap<>();
    protected BusinessRequestType dataFromXml;
    protected String canonicalName;

    static {
        brmMessagesCodeModel = new JCodeModel();
        brmBusinessLogicCodeModel = new JCodeModel();
        esbMessagesCodeModel = new JCodeModel();
        esbBusinessLogicCodeModel = new JCodeModel();
    }

    public JMainFileClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        this.canonicalName = canonicalName;
        /*makes mainModel object point to the correct JCodeModel object. In such way
        * we have 4 different models for the 4 different packages but 1 access object*/
        _defineJCodeModel();
        this.jPackage = mainModel._package(getPackageName(canonicalName));
        this.jDefinedClass = jPackage._class(getClassName(canonicalName));
        this.dataFromXml = dataFromXml;
    }

    private void _defineJCodeModel(){

        if(isABReqClassName(canonicalName) || isABRespClassName(canonicalName) || isABRMDTOClassName(canonicalName)|| isABeanClassName(canonicalName)) {
            mainModel = brmMessagesCodeModel;
        } else if (isAnSReqClassName(canonicalName) || isAnSRespClassName(canonicalName)|| isAnESBDTOClassName(canonicalName)) {
            mainModel = esbMessagesCodeModel;
        } else if (isAnExitClassName(canonicalName)) {
            mainModel = brmBusinessLogicCodeModel;
        } else if (isAnSPClassName(canonicalName)){
            mainModel = esbBusinessLogicCodeModel;
        }
    }

    public abstract Object dataProcessing();
    public abstract void buildJFieldsAndJMethods(Object data) throws ApplicationException, JClassAlreadyExistsException;

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

    public static JCodeModel getBrmMessagesCodeModel() {
        return brmMessagesCodeModel;
    }

    public static void setBrmMessagesCodeModel(JCodeModel brmMessagesCodeModel) {
        JMainFileClassData.brmMessagesCodeModel = brmMessagesCodeModel;
    }

    public static JCodeModel getBrmBusinessLogicCodeModel() {
        return brmBusinessLogicCodeModel;
    }

    public static void setBrmBusinessLogicCodeModel(JCodeModel brmBusinessLogicCodeModel) {
        JMainFileClassData.brmBusinessLogicCodeModel = brmBusinessLogicCodeModel;
    }

    public static JCodeModel getEsbMessagesCodeModel() {
        return esbMessagesCodeModel;
    }

    public static void setEsbMessagesCodeModel(JCodeModel esbMessagesCodeModel) {
        JMainFileClassData.esbMessagesCodeModel = esbMessagesCodeModel;
    }

    public static JCodeModel getEsbBusinessLogicCodeModel() {
        return esbBusinessLogicCodeModel;
    }

    public static void setEsbBusinessLogicCodeModel(JCodeModel esbBusinessLogicCodeModel) {
        JMainFileClassData.esbBusinessLogicCodeModel = esbBusinessLogicCodeModel;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }
}
