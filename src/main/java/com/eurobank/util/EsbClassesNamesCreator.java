package com.eurobank.util;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.eurobank.util.UtilityMethods.*;

public class EsbClassesNamesCreator {

    public static Set<String> addEsbClasses (Set<String> brmClassNamesSet) {

        //TODO: Exception handling, replace the exception
        //Todo: test a case with two DTO objects
        String bReqClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isABReqClassName)
                                        .map(UtilityMethods::convertBrmObjectClassToEsbClass)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);
        String bRespClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isABRespClassName)
                                        .map(UtilityMethods::convertBrmObjectClassToEsbClass)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);
        Set<String> DTOClassNameSet = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isDTOClass)
                                        .map(UtilityMethods::convertBrmDTOObjectClassToEsbClass)
                                        .collect(Collectors.toSet());
        String exitClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isExitClass)
                                        .map(UtilityMethods::convertBrmExitClassToEsbSPClass)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);

        brmClassNamesSet.add(bReqClassName);
        brmClassNamesSet.add(bRespClassName);
        brmClassNamesSet.add(exitClassName);
        brmClassNamesSet.addAll(DTOClassNameSet);

        return brmClassNamesSet;
    }
}
