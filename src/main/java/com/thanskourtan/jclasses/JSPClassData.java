package com.thanskourtan.jclasses;

import com.thanskourtan.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.JClassAlreadyExistsException;

/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public class JSPClassData extends JMainFileClassData {
    public JSPClassData(String canonicalName, BusinessRequestType dataFromXml, Boolean isAltamira) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
    }

    @Override
    public Object dataProcessing() {
        return null;
    }

    @Override
    public void buildJFieldsAndJMethods(Object data) throws JClassAlreadyExistsException {

    }
}
