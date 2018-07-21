
package com.thanskourtan.JAXBmodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.thanskourtan.JAXBmodel package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BRM_QNAME = new QName("", "BRM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.thanskourtan.JAXBmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BRMType }
     * 
     */
    public BRMType createBRMType() {
        return new BRMType();
    }

    /**
     * Create an instance of {@link IfType }
     * 
     */
    public IfType createIfType() {
        return new IfType();
    }

    /**
     * Create an instance of {@link DataSetType }
     * 
     */
    public DataSetType createDataSetType() {
        return new DataSetType();
    }

    /**
     * Create an instance of {@link ConnectorOutputType }
     * 
     */
    public ConnectorOutputType createConnectorOutputType() {
        return new ConnectorOutputType();
    }

    /**
     * Create an instance of {@link ConnectorInputType }
     * 
     */
    public ConnectorInputType createConnectorInputType() {
        return new ConnectorInputType();
    }

    /**
     * Create an instance of {@link BeanType }
     * 
     */
    public BeanType createBeanType() {
        return new BeanType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link StepType }
     * 
     */
    public StepType createStepType() {
        return new StepType();
    }

    /**
     * Create an instance of {@link OperationType }
     * 
     */
    public OperationType createOperationType() {
        return new OperationType();
    }

    /**
     * Create an instance of {@link ISERIESJ2CType }
     * 
     */
    public ISERIESJ2CType createISERIESJ2CType() {
        return new ISERIESJ2CType();
    }

    /**
     * Create an instance of {@link BusinessRequestType }
     * 
     */
    public BusinessRequestType createBusinessRequestType() {
        return new BusinessRequestType();
    }

    /**
     * Create an instance of {@link FieldType }
     * 
     */
    public FieldType createFieldType() {
        return new FieldType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BRMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BRM")
    public JAXBElement<BRMType> createBRM(BRMType value) {
        return new JAXBElement<BRMType>(_BRM_QNAME, BRMType.class, null, value);
    }

}
