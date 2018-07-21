package com.thanskourtan.util;

import java.util.HashMap;
import java.util.Map;

/**
 * /**
 * Created by v-askourtaniotis on 2/6/2018. mailTo: thanskourtan@gmail.com
 *
 * A utility class specifying the order of the creation
 * of the classes. It is an essential step, because of the
 * dependencies some class have to other classes, for example
 * the Bean class to BResp and BResp ones. So, we need to
 * construct them first.
 *
 *
 * */
public class ClassOrdering {

    private static Map<String, Integer> classOrderingMap = new HashMap<>();

    static {
        classOrderingMap.put("brmDTO", 1);
        classOrderingMap.put("esbDTO", 2);
        classOrderingMap.put("SReq", 3);
        classOrderingMap.put("SResp",4);
        classOrderingMap.put("BReq", 5);
        classOrderingMap.put("BResp", 6);
        classOrderingMap.put("Bean", 7);
        classOrderingMap.put("Exit", 8);
        classOrderingMap.put("SP", 9);
    }

    public static Map<String, Integer> getClassOrderingMap() {
        return classOrderingMap;
    }

}
