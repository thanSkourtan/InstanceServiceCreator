package com.thanskourtan.jclasses;

import com.thanskourtan.JAXBmodel.BusinessRequestType;
import com.thanskourtan.exceptions.ApplicationException;
import com.sun.codemodel.JClassAlreadyExistsException;

public class JBReqClassData extends JRequestResponseObjectsClassData{

    public JBReqClassData(String canonicalName, BusinessRequestType dataFromXml, Boolean isAltamira) throws ApplicationException, JClassAlreadyExistsException {
        super(canonicalName, dataFromXml, isAltamira);
    }
}
