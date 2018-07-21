package com.thanskourtan.util;

import com.thanskourtan.JAXBmodel.DataSetType;
import static com.thanskourtan.util.UtilityMethods.*;

import java.util.*;


/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class DataSetTypesMerger {


    public static Map<String, List<DataSetType>> mergeDataSets(List<DataSetType> data){

        SortedMap<String, List<DataSetType>> dataSetTypesMap = new TreeMap<>();

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
