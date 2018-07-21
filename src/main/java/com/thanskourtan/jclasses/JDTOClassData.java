package com.thanskourtan.jclasses;

import com.thanskourtan.JAXBmodel.BusinessRequestType;
import com.thanskourtan.exceptions.ApplicationException;
import com.sun.codemodel.JClassAlreadyExistsException;

/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public class JDTOClassData extends JRequestResponseObjectsClassData {
    public JDTOClassData(String canonicalName, BusinessRequestType dataFromXml, Boolean isAltamira) throws ApplicationException, JClassAlreadyExistsException {
        super(canonicalName, dataFromXml, isAltamira);
    }
}
