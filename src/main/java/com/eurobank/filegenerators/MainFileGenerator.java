package com.eurobank.filegenerators;

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

    protected JCodeModel mainModel;
    protected JPackage mainPackage;
    protected JDefinedClass jDefinedClass;
    protected String packageName;
    protected String className;
    protected String fullClassName;

    // The classes in this model will not be created
    protected JCodeModel secondaryModel;
    protected JPackage secondaryPackage;

    public MainFileGenerator(String fullClassName){
        mainModel = new JCodeModel();
        secondaryModel = new JCodeModel();
        this.fullClassName = fullClassName;
        this.packageName = UtilityMethods.getPackageName(fullClassName);
        this.className = UtilityMethods.getClassName(fullClassName);
    }

    public abstract void generatePackages();
    public abstract void generateClasses() throws JClassAlreadyExistsException;
    public abstract void generateConstructors() throws JClassAlreadyExistsException;
    public abstract void generateFieldsAndMethods() throws ClassNotFoundException, JClassAlreadyExistsException;
    public abstract void generateInheritance() throws JClassAlreadyExistsException;

    public void generateJavadocs(){
        jDefinedClass.javadoc().add("Automatically created by Instant Service Creator.");
    }

    public void generateAll() throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        generatePackages();
        generateClasses();
        generateFieldsAndMethods();
        generateInheritance();
        generateJavadocs();
        mainModel.build(new File("src//main//resources"));
    }


    public JCodeModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(JCodeModel mainModel) {
        this.mainModel = mainModel;
    }

    public JPackage getMainPackage() {
        return mainPackage;
    }

    public void setMainPackage(JPackage mainPackage) {
        this.mainPackage = mainPackage;
    }

    public JDefinedClass getjDefinedClass() {
        return jDefinedClass;
    }

    public void setjDefinedClass(JDefinedClass jDefinedClass) {
        this.jDefinedClass = jDefinedClass;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public JCodeModel getSecondaryModel() {
        return secondaryModel;
    }

    public void setSecondaryModel(JCodeModel secondaryModel) {
        this.secondaryModel = secondaryModel;
    }

    public JPackage getSecondaryPackage() {
        return secondaryPackage;
    }

    public void setSecondaryPackage(JPackage secondaryPackage) {
        this.secondaryPackage = secondaryPackage;
    }
}
