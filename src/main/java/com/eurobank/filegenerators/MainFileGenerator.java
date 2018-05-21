package com.eurobank.filegenerators;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class MainFileGenerator {


    public static void generateFile (Object dataFromXml) {
        if(!(dataFromXml instanceof BusinessRequestType)) {
            System.exit(-1);
        }

        BusinessRequestType data = (BusinessRequestType) dataFromXml;

        JCodeModel codeModel = new JCodeModel();

        JCodeModel dummyModel = new JCodeModel(); // A model for the classes that we do not want to be created.
        JPackage dummyPackage = dummyModel._package("it.ibm.eurobank.bean.base");


        //Create bean class
        String beanClassFullName = data.getBean().getBeanClass();
        String beanPackage = beanClassFullName.substring(0, beanClassFullName.lastIndexOf('.'));
        String beanClass = beanClassFullName.substring(beanClassFullName.lastIndexOf('.') + 1, beanClassFullName.length());
        JPackage jp = codeModel._package(beanPackage);



        System.out.println("lala");
        try{
            JDefinedClass jc = jp._class(beanClass);
            JDefinedClass superclass = dummyPackage._class("ABaseAS400Bean");

            jc._extends(superclass);
            //Adds javadoc
            jc.javadoc().add("Automatically created by Instant Service Creator.");

            //Adds variable
            //JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));
            JFieldVar sendField = jc.field(JMod.PRIVATE, Integer.class, "send");
            JFieldVar receiveField = jc.field(JMod.PRIVATE, Integer.class, "receive");

            // Adds method
            JMethod getVar = jc.method(JMod.PUBLIC, sendField.type(), "getSend");
            getVar.body()._return(sendField);

            JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "setSend");
            setVar.param(sendField.type(), sendField.name());
            setVar.body().assign(JExpr._this().ref(sendField.name()), JExpr.ref(sendField.name()));

            codeModel.build(new File("src\\main\\resources"));

        }catch(IOException | JClassAlreadyExistsException e){
            System.out.println(e.getMessage());
        }


    }


}
