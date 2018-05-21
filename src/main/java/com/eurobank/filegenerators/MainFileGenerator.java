package com.eurobank.filegenerators;

import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by v-askourtaniotis on 21/5/2018. mailTo: thanskourtan@gmail.com
 */
public class MainFileGenerator {


    public static void generateFile () {
        JCodeModel codeModel = new JCodeModel();
        JPackage jp = codeModel._package("com.sookocheff.example");
        try{
            JDefinedClass jc = jp._class("Thanos");

            //Adds javadoc
            jc.javadoc().add("Generated class.");

            //Adds variable
            JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));
            JFieldVar varField = jc.field(JMod.PRIVATE, Integer.class, "var");

            // Adds method
            JMethod getVar = jc.method(JMod.PUBLIC, varField.type(), "getVar");
            getVar.body()._return(varField);

            JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "setVar");
            setVar.param(varField.type(), varField.name());
            setVar.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));

            codeModel.build(new File("src\\main\\resources"));

        }catch(IOException | JClassAlreadyExistsException e){
            System.out.println(e.getMessage());
        }


    }


}
