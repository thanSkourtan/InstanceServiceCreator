package com.eurobank.filegenerators;

import com.eurobank.jclasses.JMainFileClassData;
import com.eurobank.jclasses.JRequestResponseObjectsClassData;
import com.sun.codemodel.*;
import static com.eurobank.util.UtilityMethods.*;

import java.io.IOException;
import java.util.Map;


/**
 * Created by v-askourtaniotis on 24/5/2018. mailTo: thanskourtan@gmail.com
 */
public class BRespClassGenerator extends MainFileGenerator{

    private JPackage outerPackage1;
    private Map<String, JMainFileClassData> jClassesMap;

    public BRespClassGenerator( Map<String, JMainFileClassData> jClassesMap, String canonicalName) throws JClassAlreadyExistsException, IOException, ClassNotFoundException {
        super(jClassesMap.get(canonicalName));
        this.jClassesMap = jClassesMap;
        generateAll();
    }

    @Override
    public void generateOuterPackages() {
        outerPackage1 = outerModel._package("it.ibm.eurobank.bean.base.data");
    }

    @Override
    public void generateOuterFieldsAndMethods() throws JClassAlreadyExistsException {
        JDefinedClass mainClassTempInstance = mainclassdata.getjDefinedClass();

        JRequestResponseObjectsClassData mainClassCasted = (JRequestResponseObjectsClassData) mainclassdata;
        JDefinedClass DTOClass = null;
        if (mainClassCasted.getMatchDataSetName() != null) {
            for(String key : jClassesMap.keySet()){
                if ((isABRespClassName(mainClassTempInstance.name()) ||
                    isABReqClassName(mainClassTempInstance.name()) )&&
                    isABRMDTOClassName(key)){
                    DTOClass = jClassesMap.get(key).getjPackage()._getClass(getClassName(key));
                } else if ((isAnSReqClassName(mainClassTempInstance.name()) ||
                           isAnSRespClassName(mainClassTempInstance.name())) &&
                        isAnESBDTOClassName(key)){
                    DTOClass = jClassesMap.get(key).getjPackage()._getClass(getClassName(key));
                }
            }

            JFieldVar tempVar2 = mainClassTempInstance.field(JMod.PRIVATE,
                                                            DTOClass.array(),
                                                            makeFirstCharacterLowercase(mainClassCasted.getMatchDataSetName()));
            _createGettersAndSettersMethods (mainClassTempInstance, tempVar2);
        }

    }

    private void _createGettersAndSettersMethods (JDefinedClass jDefinedClass, JFieldVar x) {
        JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, x.type(), "get" + x.name());
        tempGetter.body()._return(x);
        JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, jDefinedClass.owner().VOID, "set" + x.name());
        tempSetter.param(x.type(), x.name());
        tempSetter.body().assign(JExpr._this().ref(x.name()), JExpr.ref(x.name()));
    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400OutputDataBean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }

}
