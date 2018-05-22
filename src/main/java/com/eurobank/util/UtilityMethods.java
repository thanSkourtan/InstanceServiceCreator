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
}
