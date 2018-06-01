package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.DataSetType;
import com.eurobank.jclasses.JBReqClassData;
import com.eurobank.jclasses.JMainFileClassData;
import com.sun.codemodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BReqClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;

    public BReqClassGenerator( JBReqClassData mainclassdata) {
        super(mainclassdata);
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateClasses() throws JClassAlreadyExistsException {

    }

    @Override
    public void generateOuterFieldsAndMethods()  {
        ;
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400InputDataBean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }


}
