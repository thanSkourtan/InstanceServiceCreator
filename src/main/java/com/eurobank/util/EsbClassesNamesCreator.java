package com.eurobank.util;

import java.util.NoSuchElementException;
import java.util.Optional;
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
                                        .filter(UtilityMethods::isABReqClassName)
                                        .findAny()
                                        .orElseThrow(NoSuchElementException::new);

        brmClassNamesSet.add(convertbrmDTOClassToEsbClass(bReqClassName));
        brmClassNamesSet.add(convertbrmDTOClassToEsbClass(bRespClassName));

        return brmClassNamesSet;
    }
}
