package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.eurobank.filegenerators.BReqClassGenerator;
import com.sun.codemodel.JClassAlreadyExistsException;

public class JBReqClassData extends JRequestResponseObjectsClassData{

    public JBReqClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
    }
}
