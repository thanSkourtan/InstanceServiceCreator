package com.eurobank.generatedclassnamesprocessors;

import java.util.List;
import java.util.Set;
import static com.eurobank.util.UtilityMethods.*;

public class FullClassesNamesSplitter {

    private static String BRespPackage;
    private static String BRespClass;
    private static String BReqPackage;
    private static String BReqClass;
    private static String BeanPackage;
    private static String BeanClass;
    private static String OperationExitPackage;
    private static String OperationExitClass;
    private static String SPPackage;
    private static String SPClass;
    private static String SRespPackage;
    private static String SRespClass;
    private static String SReqPackage;
    private static String SReqClass;
    private static List<String> DTOBRMPackage;
    private static List<String> DTOBRMClass;
    private static List<String> DTOESBPackage;
    private static List<String> DTOESBClass;

    public FullClassesNamesSplitter(Set<String> allClassNamesSet ){
        allClassNamesSet.forEach(x -> {
            if(isABRespClassName(x)){
                BRespPackage = getPackageName(x);
                BRespClass = getClassName(x);
            } else if (isABReqClassName(x)){
                BReqPackage = getPackageName(x);
                BRespClass = getClassName(x);
            } else if (isAnExitClassName(x)) {
                OperationExitPackage = getPackageName(x);
                OperationExitClass = getClassName(x);
            } else if (isABeanClassName(x)){
                BeanPackage = getPackageName(x);
                BeanClass = getClassName(x);
            } else if (isAnSPClassName(x)){
                SPPackage = getPackageName(x);
                SPClass = getClassName(x);
            } else if (isAnSPRespClassName(x)){
                SRespPackage = getPackageName(x);
                SRespClass = getClassName(x);
            } else if (isAnSPReqClassName(x)){
                SReqPackage = getPackageName(x);
                SReqClass = getClassName(x);
            } else if (isABRMDTOClassName(x)){
                DTOBRMPackage.add(getPackageName(x));
                DTOBRMClass.add(getClassName(x));
            } else if (isAnESBDTOClassName(x)){
                DTOESBPackage.add(getPackageName(x));
                DTOESBClass.add(getClassName(x));
            }
        });
    }

}
