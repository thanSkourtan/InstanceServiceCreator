package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.JClassAlreadyExistsException;

public class JBReqClassData extends JRequestResponseObjectsClassData{

    public JBReqClassData(String canonicalName, BusinessRequestType dataFromXml, Boolean isAltamira) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml, isAltamira);
    }
}
