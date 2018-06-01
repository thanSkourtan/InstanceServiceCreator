package com.eurobank.filegenerators;

import com.eurobank.jclasses.JBReqClassData;
import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.util.UtilityMethods;
import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;

import static com.eurobank.util.UtilityMethods.getClassName;
import static com.eurobank.util.UtilityMethods.getPackageName;


/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */

public abstract class MainFileGenerator {

    protected JCodeModel outerModel;
    protected JMainFileClassData mainclassdata;

    public MainFileGenerator(JBReqClassData mainclassdata){
        outerModel = new JCodeModel();
        this.mainclassdata = mainclassdata;
    }

    public abstract void generateOuterPackages();
    //todo:delete the method below
    public abstract void generateClasses() throws JClassAlreadyExistsException;
    public abstract void generateOuterFieldsAndMethods();
    public abstract void generateInheritance() throws JClassAlreadyExistsException;

    public void generateJavadocs(){
        mainclassdata.getjDefinedClass().javadoc().add("Automatically created by Instant Service Creator.");
    }

    public void generateAll() throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        generateOuterPackages();
        generateClasses();
        generateOuterFieldsAndMethods();
        generateInheritance();
        generateJavadocs();
        //todo: remove it from here, we will call it once at the end
        mainclassdata.getMainModel().build(new File("src//main//resources"));
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
