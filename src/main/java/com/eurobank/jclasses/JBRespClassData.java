package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.JClassAlreadyExistsException;

/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public class JBRespClassData extends JRequestResponseObjectsClassData{


    public JBRespClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
    }
}
