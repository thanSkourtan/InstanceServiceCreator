package com.eurobank.util;

import com.eurobank.JAXBmodel.DataSetType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DataSetTypesMerger {


    public static Map<String, List<DataSetType>> mergeDataSets(List<DataSetType> data){

        Map<String, List<DataSetType>> dataSetTypesMap = new HashMap<>();

        //TODO: Check with datatypes with the same bean name
        data.forEach(x -> {
            String key = x.getBeanClass();
            dataSetTypesMap.computeIfPresent(key, (k,v) -> {
                v.add(x);
                return v;
            });
            dataSetTypesMap.computeIfAbsent(key, v -> new ArrayList<>()).add(x);
        });


        return dataSetTypesMap;
    }

}
