package com.eurobank.as400filegenerators;

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
        super(jClassesMap.get(getTypeofClassExpanded(canonicalName)));
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

        if (mainClassCasted.getMatchDataSetName() != null) {
            JDefinedClass DTOClass = null;

            if (isABRespClassName(mainClassTempInstance.name()) || isABReqClassName(mainClassTempInstance.name())){
                DTOClass = jClassesMap.get("BRMDTO").getjDefinedClass();
            } else if (isAnSReqClassName(mainClassTempInstance.name()) || isAnSRespClassName(mainClassTempInstance.name())){
                DTOClass = jClassesMap.get("ESBDTO").getjDefinedClass();
            }

            JFieldVar tempVar2 = mainClassTempInstance.field(JMod.PRIVATE,
                                                            DTOClass.array(),
                                                            makeFirstCharacterLowercase(mainClassCasted.getMatchDataSetName()));
            _createGettersAndSettersMethods (mainClassTempInstance, tempVar2);



        }

    }

    private void _createGettersAndSettersMethods (JDefinedClass jDefinedClass, JFieldVar x) {
        JMethod tempGetter = jDefinedClass.method(JMod.PUBLIC, x.type(),
                "get" + makeFirstCharacterCapitalcase(x.name()));
        tempGetter.body()._return(x);
        JMethod tempSetter  = jDefinedClass.method(JMod.PUBLIC, jDefinedClass.owner().VOID,
                "set" + makeFirstCharacterCapitalcase(x.name()));

        mainclassdata.getMethodsMap().put("get" + makeFirstCharacterCapitalcase(x.name()), tempGetter);
        tempSetter.param(x.type(), x.name());
        tempSetter.body().assign(JExpr._this().ref("get" + makeFirstCharacterCapitalcase(x.name())), JExpr.ref(x.name()));
        mainclassdata.getMethodsMap().put("set" + makeFirstCharacterCapitalcase(x.name()), tempSetter);

    }

    @Override
    public void generateInheritance() throws JClassAlreadyExistsException {
        JDefinedClass superclass = outerPackage1._class("ABaseAS400OutputDataBean");
        mainclassdata.getjDefinedClass()._extends(superclass);
    }

}
