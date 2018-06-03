package com.eurobank.util;

import com.eurobank.JAXBmodel.DataSetType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class UtilityMethods {

    public static String getXmlFileName (String x) {
        return x.substring(x.lastIndexOf("/") + 1, x.indexOf("."));
    }

    public static String getPackageName(String fullClassName){
        return fullClassName.substring(0, fullClassName.lastIndexOf('.'));
    }

    public static String getClassName(String fullClassName){
        return fullClassName.substring(fullClassName.lastIndexOf('.') + 1, fullClassName.length());
    }

    public static String makeFirstCharacterLowercase(String x) {
        return x.substring(0, 1).toLowerCase() + x.substring(1);
    }

    public static boolean isABRespClassName(String className){
        return className.endsWith("BResp");
    }

    public static boolean isABReqClassName(String className){
        return className.endsWith("BReq");
    }

    public static boolean isABeanClassName(String className) {
        return className.endsWith("Bean");
    }

    public static boolean isAnExitClassName(String brmClassName){
        return brmClassName.endsWith("Exit");
    }

    public static boolean isAnSPClassName(String brmClassName){
        return brmClassName.endsWith("SP");
    }

    public static boolean isAnSRespClassName(String brmClassName){
        return brmClassName.endsWith("SResp");
    }

    public static boolean isAnSReqClassName(String brmClassName){
        return brmClassName.endsWith("SReq");
    }

    public static boolean isABRMDTOClassName(String x){
        return isADTOClassName(x) && x.contains(".brm.");
    }

    public static boolean isAnESBDTOClassName(String x){
        return isADTOClassName(x) && x.contains(".esb.");
    }

    public static boolean isADTOClassName (String x) {
        return !isABReqClassName(x) && !isABRespClassName(x)
                && !isAnExitClassName(x) && !isABeanClassName(x)
                && !isAnSPClassName(x) && !isAnSReqClassName(x)
                && !isAnSRespClassName(x);
    }

    public static String convertBrmDTOObjectClassToEsbClass(String brmClassName) {
        return brmClassName.replace("brm","esb");
    }

    public static String convertEsbDTOObjectClassToBrmClass(String brmClassName) {
        return brmClassName.replace("esb","brm");
    }

    public static String convertBrmExitClassToEsbSPClass(String brmClassName) {
        return brmClassName.replace("brm","esb")
                .replace("userExits", "services")
                .replace("OperationExit", "SP")
                .replace("UserExit", "SP");
    }

    public static String convertBrmObjectClassToEsbClass(String brmClassName) {
        return brmClassName.replace("brm","esb")
                .replace("BReq", "SReq")
                .replace("BResp", "SResp");
    }

    public static String convertEsbObjectClassToBrmClass(String brmClassName) {
        return brmClassName.replace("esb","brm")
                .replace("SReq", "BReq")
                .replace("SResp", "BResp");
    }

    public static List<DataSetType> getReqDataSetTypes (Map<String, List<DataSetType>> m) {
         return m.keySet().stream()
                  .filter(UtilityMethods::isABReqClassName)
                  .map(m::get)
                  .flatMap(Collection::stream)
                  .collect(Collectors.toList());
    }

    public static List<DataSetType> getRespDataSetTypes (Map<String, List<DataSetType>> m) {
        return m.keySet().stream()
                .filter(UtilityMethods::isABRespClassName)
                .map(m::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static List<DataSetType> getBRMDTODataSetTypes (Map<String, List<DataSetType>> m) {
        return m.keySet().stream()
                .filter(UtilityMethods::isABRMDTOClassName)
                .map(m::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    //TODO: for now there are not esb dtos, only brms.
    public static List<DataSetType> getESBDTODataSetTypes (Map<String, List<DataSetType>> m) {
        return m.keySet().stream()
                .filter(UtilityMethods::isAnESBDTOClassName)
                .map(m::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static String getTypeofClass(String fullClassName) {
        if(isABRespClassName(fullClassName)) return "BResp";
        else if(isABReqClassName(fullClassName)) return "BReq";
        else if(isABeanClassName(fullClassName)) return "Bean";
        else if(isAnExitClassName(fullClassName)) return "Exit";
        else if(isAnSPClassName(fullClassName)) return "SP";
        else if(isAnSReqClassName(fullClassName)) return "SReq";
        else if(isAnSRespClassName(fullClassName)) return "SResp";
        else if(isADTOClassName(fullClassName)) return "DTO";
        else return "";
    }

    public static String getTypeofClassExpanded(String fullClassName) {
        if(isABRespClassName(fullClassName)) return "BResp";
        else if(isABReqClassName(fullClassName)) return "BReq";
        else if(isABeanClassName(fullClassName)) return "Bean";
        else if(isAnExitClassName(fullClassName)) return "Exit";
        else if(isAnSPClassName(fullClassName)) return "SP";
        else if(isAnSReqClassName(fullClassName)) return "SReq";
        else if(isAnSRespClassName(fullClassName)) return "SResp";
        else if(isABRMDTOClassName(fullClassName)) return "BRMDTO";
        else if(isAnESBDTOClassName(fullClassName)) return "ESBDTO";
        else return "";
    }



}
