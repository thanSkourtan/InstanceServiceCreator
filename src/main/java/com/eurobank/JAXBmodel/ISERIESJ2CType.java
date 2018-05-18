
package com.eurobank.JAXBmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ISERIESJ2CType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISERIESJ2CType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConnectorInput" type="{}ConnectorInputType"/>
 *         &lt;element name="ConnectorOutput" type="{}ConnectorOutputType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UseService" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Transaction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="runInetStart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UserExitClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISERIESJ2CType", propOrder = {
    "connectorInput",
    "connectorOutput"
})
public class ISERIESJ2CType {

    @XmlElement(name = "ConnectorInput", required = true)
    protected ConnectorInputType connectorInput;
    @XmlElement(name = "ConnectorOutput", required = true)
    protected ConnectorOutputType connectorOutput;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "UseService")
    protected String useService;
    @XmlAttribute(name = "Transaction")
    protected String transaction;
    @XmlAttribute(name = "runInetStart")
    protected String runInetStart;
    @XmlAttribute(name = "TransactionID")
    protected String transactionID;
    @XmlAttribute(name = "UserExitClass")
    protected String userExitClass;

    /**
     * Gets the value of the connectorInput property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectorInputType }
     *     
     */
    public ConnectorInputType getConnectorInput() {
        return connectorInput;
    }

    /**
     * Sets the value of the connectorInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectorInputType }
     *     
     */
    public void setConnectorInput(ConnectorInputType value) {
        this.connectorInput = value;
    }

    /**
     * Gets the value of the connectorOutput property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectorOutputType }
     *     
     */
    public ConnectorOutputType getConnectorOutput() {
        return connectorOutput;
    }

    /**
     * Sets the value of the connectorOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectorOutputType }
     *     
     */
    public void setConnectorOutput(ConnectorOutputType value) {
        this.connectorOutput = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the useService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseService() {
        return useService;
    }

    /**
     * Sets the value of the useService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseService(String value) {
        this.useService = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransaction(String value) {
        this.transaction = value;
    }

    /**
     * Gets the value of the runInetStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunInetStart() {
        return runInetStart;
    }

    /**
     * Sets the value of the runInetStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunInetStart(String value) {
        this.runInetStart = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the userExitClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserExitClass() {
        return userExitClass;
    }

    /**
     * Sets the value of the userExitClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserExitClass(String value) {
        this.userExitClass = value;
    }

}
