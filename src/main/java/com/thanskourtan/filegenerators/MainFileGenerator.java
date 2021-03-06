package com.thanskourtan.filegenerators;

import com.thanskourtan.jclasses.JMainFileClassData;
import com.sun.codemodel.*;

import java.io.IOException;


/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */

public abstract class MainFileGenerator {

    protected JCodeModel outerModel;
    protected JMainFileClassData mainclassdata;

    public MainFileGenerator(JMainFileClassData jClassData){
        outerModel = new JCodeModel();
        this.mainclassdata = jClassData;
    }

    public abstract void generateOuterPackages();
    public abstract void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException, NoSuchMethodException;
    public abstract void generateInheritance() throws JClassAlreadyExistsException;
    public void generateJavadocs(){
        mainclassdata.getjDefinedClass().javadoc().add("Automatically created by Instant Service Creator.");
    }

    public void generateAll() throws JClassAlreadyExistsException, IOException, ClassNotFoundException, NoSuchMethodException {
        generateOuterPackages();
        generateOuterFieldsAndMethods();
        generateInheritance();
        generateJavadocs();
    }

    public JCodeModel getOuterModel() {
        return outerModel;
    }

    public void setOuterModel(JCodeModel outerModel) {
        this.outerModel = outerModel;
    }

    public JMainFileClassData getMainclassdata() {
        return mainclassdata;
    }

    public void setMainclassdata(JMainFileClassData mainclassdata) {
        this.mainclassdata = mainclassdata;
    }
}
