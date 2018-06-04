package com.eurobank.jclasses;

import com.eurobank.JAXBmodel.BusinessRequestType;
import com.sun.codemodel.JClassAlreadyExistsException;

/**
 * Created by v-askourtaniotis on 1/6/2018. mailTo: thanskourtan@gmail.com
 */
public class JExitClassData extends JMainFileClassData{
    private String transactionId;

    public JExitClassData(String canonicalName, BusinessRequestType dataFromXml) throws JClassAlreadyExistsException {
        super(canonicalName, dataFromXml);
        this.transactionId = dataFromXml.getService().getISERIESJ2C().getTransactionID();
    }

    @Override
    public Object dataProcessing() {
        return null;
    }

    @Override
    public void buildJFieldsAndJMethods(Object data) throws JClassAlreadyExistsException {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
