package com.eurobank.generatedclassnamesprocessors;

import static com.eurobank.util.UtilityMethods.*;

import java.util.*;
import java.util.stream.Collectors;
import static com.eurobank.util.ClassOrdering.*;

public class EsbClassesNamesCreator {

    public static Map<Integer, String> addEsbClasses (Set<String> brmClassNamesSet) {

        SortedMap<Integer, String> allSortedClassNamesMap = new TreeMap<>((a, b) -> {
            if (a < b) return -1;
            else if (a > b) return 1;
            else return 0;
        });

        //TODO: Exception handling, replace the exception
        //Todo: test a case with two DTO objects

        brmClassNamesSet.forEach(x -> {
           String temp = null;
           int priorityNumberBrmClass = 0;
           int priorityNumberEsbClass = 0;
            if(isABReqClassName(x)) {
                temp = convertBrmObjectClassToEsbClass(x);
                priorityNumberBrmClass = getClassOrderingMap().get("BReq");
                priorityNumberEsbClass = getClassOrderingMap().get("SReq");
            } else if(isABRespClassName(x)) {
                temp = convertBrmObjectClassToEsbClass(x);
                priorityNumberBrmClass = getClassOrderingMap().get("BResp");
                priorityNumberEsbClass = getClassOrderingMap().get("BResp");
            } else if (isABeanClassName(x)) {
                priorityNumberBrmClass = getClassOrderingMap().get("Bean");
            }  else if (isAnExitClassName(x)) {
                temp = convertBrmExitClassToEsbSPClass(x);
                priorityNumberBrmClass = getClassOrderingMap().get("Exit");
                priorityNumberEsbClass = getClassOrderingMap().get("SP");
            } else if (isABRMDTOClassName(x)) {
                temp = convertBrmDTOObjectClassToEsbClass(x);
                priorityNumberBrmClass = getClassOrderingMap().get("brmDTO");
                priorityNumberEsbClass = getClassOrderingMap().get("esbDTO");
            }

            if(temp!= null) allSortedClassNamesMap.put(priorityNumberEsbClass, temp);
            allSortedClassNamesMap.put(priorityNumberBrmClass, x);

        });


        return allSortedClassNamesMap;
    }
}
