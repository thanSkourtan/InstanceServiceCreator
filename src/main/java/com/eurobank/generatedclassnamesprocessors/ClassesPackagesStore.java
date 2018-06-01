package com.eurobank.generatedclassnamesprocessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static com.eurobank.util.UtilityMethods.*;

public class ClassesPackagesStore {

    private String BRespPackage;
    private String BRespClass;
    private String BReqPackage;
    private String BReqClass;
    private String BeanPackage;
    private String BeanClass;
    private String OperationExitPackage;
    private String OperationExitClass;
    private String SPPackage;
    private String SPClass;
    private String SRespPackage;
    private String SRespClass;
    private String SReqPackage;
    private String SReqClass;
    private final List<String> DTOBRMPackage = new ArrayList<>();
    private final List<String> DTOBRMClass = new ArrayList<>();
    private final List<String> DTOESBPackage = new ArrayList<>();
    private final List<String> DTOESBClass = new ArrayList<>();

    public ClassesPackagesStore(Set<String> allClassNamesSet ){
        allClassNamesSet.forEach(x -> {
            if(isABRespClassName(x)){
                BRespPackage = getPackageName(x);
                BRespClass = getClassName(x);
            } else if (isABReqClassName(x)){
                BReqPackage = getPackageName(x);
                BReqClass = getClassName(x);
            } else if (isAnExitClassName(x)) {
                OperationExitPackage = getPackageName(x);
                OperationExitClass = getClassName(x);
            } else if (isABeanClassName(x)){
                BeanPackage = getPackageName(x);
                BeanClass = getClassName(x);
            } else if (isAnSPClassName(x)){
                SPPackage = getPackageName(x);
                SPClass = getClassName(x);
            } else if (isAnSRespClassName(x)){
                SRespPackage = getPackageName(x);
                SRespClass = getClassName(x);
            } else if (isAnSReqClassName(x)){
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

    public String getBRespPackage() {
        return BRespPackage;
    }

    public void setBRespPackage(String BRespPackage) {
        this.BRespPackage = BRespPackage;
    }

    public String getBRespClass() {
        return BRespClass;
    }

    public void setBRespClass(String BRespClass) {
        this.BRespClass = BRespClass;
    }

    public String getBReqPackage() {
        return BReqPackage;
    }

    public void setBReqPackage(String BReqPackage) {
        this.BReqPackage = BReqPackage;
    }

    public String getBReqClass() {
        return BReqClass;
    }

    public void setBReqClass(String BReqClass) {
        this.BReqClass = BReqClass;
    }

    public String getBeanPackage() {
        return BeanPackage;
    }

    public void setBeanPackage(String beanPackage) {
        BeanPackage = beanPackage;
    }

    public String getBeanClass() {
        return BeanClass;
    }

    public void setBeanClass(String beanClass) {
        BeanClass = beanClass;
    }

    public String getOperationExitPackage() {
        return OperationExitPackage;
    }

    public void setOperationExitPackage(String operationExitPackage) {
        OperationExitPackage = operationExitPackage;
    }

    public String getOperationExitClass() {
        return OperationExitClass;
    }

    public void setOperationExitClass(String operationExitClass) {
        OperationExitClass = operationExitClass;
    }

    public String getSPPackage() {
        return SPPackage;
    }

    public void setSPPackage(String SPPackage) {
        this.SPPackage = SPPackage;
    }

    public String getSPClass() {
        return SPClass;
    }

    public void setSPClass(String SPClass) {
        this.SPClass = SPClass;
    }

    public String getSRespPackage() {
        return SRespPackage;
    }

    public void setSRespPackage(String SRespPackage) {
        this.SRespPackage = SRespPackage;
    }

    public String getSRespClass() {
        return SRespClass;
    }

    public void setSRespClass(String SRespClass) {
        this.SRespClass = SRespClass;
    }

    public String getSReqPackage() {
        return SReqPackage;
    }

    public void setSReqPackage(String SReqPackage) {
        this.SReqPackage = SReqPackage;
    }

    public String getSReqClass() {
        return SReqClass;
    }

    public void setSReqClass(String SReqClass) {
        this.SReqClass = SReqClass;
    }

    public List<String> getDTOBRMPackage() {
        return DTOBRMPackage;
    }


    public List<String> getDTOBRMClass() {
        return DTOBRMClass;
    }

    public List<String> getDTOESBPackage() {
        return DTOESBPackage;
    }

    public List<String> getDTOESBClass() {
        return DTOESBClass;
    }

}
