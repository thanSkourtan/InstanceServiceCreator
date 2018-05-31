package com.eurobank.util;

import com.eurobank.JAXBmodel.DataSetType;
import static com.eurobank.util.UtilityMethods.*;

import java.util.*;


/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DataSetTypesMerger {


    public static Map<String, List<DataSetType>> mergeDataSets(List<DataSetType> data){

        /*The comparator ensures that DTO classes will always be the first to be constructed*/
        SortedMap<String, List<DataSetType>> dataSetTypesMap =
                new TreeMap<>((a, b) -> isABReqClassName(a) || isABRespClassName(a)? 1 : -1);

        //TODO: Check with datatypes with the same bean name
        data.forEach(x -> {
            String key = x.getBeanClass();
            dataSetTypesMap.computeIfPresent(key, (k,v) -> {
                v.add(x);
                return v;
            });
            dataSetTypesMap.computeIfAbsent(key, v -> {
                List<DataSetType> temp  = new ArrayList<>();
                temp.add(x);
                return temp;
            });

        });

        return dataSetTypesMap;
    }

}
