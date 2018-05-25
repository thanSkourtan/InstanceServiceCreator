package com.eurobank.filegenerators;

import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;

import static com.eurobank.util.UtilityMethods.getClassName;
import static com.eurobank.util.UtilityMethods.getPackageName;


/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */


/**
 *  We need a common state, that's why we use an abstract class, not an interface.
 *
 *
 *
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
        this.packageName = getPackageName(fullClassName);
        this.className = getClassName(fullClassName);
    }

    public abstract void generatePackages();
    public abstract void generateClasses() throws JClassAlreadyExistsException;
    public abstract void generateFieldsAndMethods();
    public abstract void generateInheritance() throws JClassAlreadyExistsException;

    public void generateJavadocs(){
        jDefinedClass.javadoc().add("Automatically created by Instant Service Creator.");
    }

    public void generateAll() throws JClassAlreadyExistsException, IOException{
        generatePackages();
        generateClasses();
        generateFieldsAndMethods();
        generateInheritance();
        generateJavadocs();
        mainModel.build(new File("src//main//resources"));
    }

}
