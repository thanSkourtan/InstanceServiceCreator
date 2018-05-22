package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.BeanType;
import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    // The classes in this model will not be created
    protected JCodeModel secondaryModel;
    protected JPackage secondaryPackage;

    public MainFileGenerator(){
        mainModel = new JCodeModel();
        secondaryModel = new JCodeModel();
    }

    public abstract void generatePackages();
    public abstract void generateClasses() throws JClassAlreadyExistsException;
    public abstract void generateFieldsAndMethods();
    public abstract void generateInheritance() throws JClassAlreadyExistsException;
    public abstract void generateJavadocs();

    public void generateAll() throws JClassAlreadyExistsException, IOException{
        generatePackages();
        generateClasses();
        generateFieldsAndMethods();
        generateInheritance();
        generateJavadocs();
        mainModel.build(new File("src\\main\\resources"));
    }

}
