package com.eurobank.util;

import java.util.NoSuchElementException;
import java.util.Set;

import static com.eurobank.util.UtilityMethods.*;

public class EsbClassesNamesCreator {

    public static Set<String> addEsbClasses (Set<String> brmClassNamesSet) {

        //TODO: Exception handling, replace the exception
        String bReqClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isABReqClassName)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);
        String bRespClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isABRespClassName)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);
        String DTOClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isDTOClass)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);
        String exitClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isExitClass)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);

        brmClassNamesSet.add(convertBrmObjectClassToEsbClass(bReqClassName));
        brmClassNamesSet.add(convertBrmObjectClassToEsbClass(bRespClassName));
        brmClassNamesSet.add(convertBrmDTOObjectClassToEsbClass(DTOClassName));
        brmClassNamesSet.add(convertBrmExitClassToEsbSPClass(exitClassName));

        return brmClassNamesSet;
    }
}
