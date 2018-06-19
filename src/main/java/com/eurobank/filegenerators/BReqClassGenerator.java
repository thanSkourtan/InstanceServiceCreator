package com.eurobank.filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;

import java.io.IOException;
import java.util.Map;

import static com.eurobank.util.UtilityMethods.getTypeofClassExpanded;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BReqClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;
    private Boolean isAltamira;

    public BReqClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName, Boolean isAltamira) throws JClassAlreadyExistsException, IOException, ClassNotFoundException, NoSuchMethodException {
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
        this.jClassesMap = jClassesMap;
        this.isAltamira = isAltamira;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateOuterFieldsAndMethods()  {


    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = isAltamira ?
                outerPackage1._class("ABaseAltamiraInputDataBean") :
                outerPackage1._class("ABaseAS400InputDataBean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }


}
