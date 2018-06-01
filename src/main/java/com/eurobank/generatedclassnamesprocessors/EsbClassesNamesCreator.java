package com.eurobank.generatedclassnamesprocessors;

import com.eurobank.util.UtilityMethods;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class EsbClassesNamesCreator {

    public static Set<String> addEsbClasses (Set<String> brmClassNamesSet) {

        //TODO: Exception handling, replace the exception
        //Todo: test a case with two DTO objects
        //todo: change this to if statement
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
                                        .filter(UtilityMethods::isADTOClassName)
                                        .map(UtilityMethods::convertBrmDTOObjectClassToEsbClass)
                                        .collect(Collectors.toSet());
        String exitClassName = brmClassNamesSet
                                        .stream()
                                        .filter(UtilityMethods::isAnExitClassName)
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
