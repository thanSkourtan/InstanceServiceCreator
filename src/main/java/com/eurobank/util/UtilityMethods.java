package com.eurobank.util;

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

    public static boolean isAnSPRespClassName(String brmClassName){
        return brmClassName.endsWith("SResp");
    }

    public static boolean isAnSPReqClassName(String brmClassName){
        return brmClassName.endsWith("SReq");
    }

    public static String convertBrmObjectClassToEsbClass(String brmClassName) {
        return brmClassName.replace("brm","esb")
                .replace("BReq", "SReq")
                .replace("BResp", "SResp");
    }

    public static boolean isABRMDTOClassName(String x){
        return isADTOClassName(x) && x.contains("\\.brm\\.");
    }

    public static boolean isAnESBDTOClassName(String x){
        return isADTOClassName(x) && x.contains("\\.esb\\.");
    }

    public static boolean isADTOClassName (String x) {
        return !isABReqClassName(x) && !isABRespClassName(x)
                && !isAnExitClassName(x) && !isABeanClassName(x)
                && !isAnSPClassName(x) && !isAnSPReqClassName(x)
                && !isAnSPRespClassName(x);
    }

    public static String convertBrmDTOObjectClassToEsbClass(String brmClassName) {
        return brmClassName.replace("brm","esb");
    }

    public static String convertBrmExitClassToEsbSPClass(String brmClassName) {
        return brmClassName.replace("brm","esb")
                .replace("userExits", "services")
                .replace("OperationExit", "SP")
                .replace("UserExit", "SP");
    }

}
