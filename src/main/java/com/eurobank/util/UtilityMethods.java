package com.eurobank.util;

/**
 * Created by v-askourtaniotis on 22/5/2018. mailTo: thanskourtan@gmail.com
 */
public class UtilityMethods {

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

    public static String convertbrmDTOClassToEsbClass(String brmClassName) {
        return brmClassName.replace("brm","esb")
                .replace("BReq", "SReq")
                .replace("BResp", "SResp");
    }

}
