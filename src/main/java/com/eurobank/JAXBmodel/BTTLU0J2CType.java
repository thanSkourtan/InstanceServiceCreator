
package com.eurobank.JAXBmodel;

import com.eurobank.JAXBmodel.ConnectorInputType;
import com.eurobank.JAXBmodel.ConnectorOutputType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for BTTLU0J2CType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BTTLU0J2CType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConnectorInput" type="{}ConnectorInputType"/>
 *         &lt;element name="ConnectorOutput" type="{}ConnectorOutputType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UseService" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Transaction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="UserExitClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionKey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="_DebugStreamFromConnector" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BTTLU0J2CType", propOrder = {
    "connectorInput",
    "connectorOutput"
})
public class BTTLU0J2CType {

    @XmlElement(name = "ConnectorInput", required = true)
    protected com.eurobank.JAXBmodel.ConnectorInputType connectorInput;
    @XmlElement(name = "ConnectorOutput", required = true)
    protected ConnectorOutputType connectorOutput;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "UseService")
    protected String useService;
    @XmlAttribute(name = "Transaction")
    protected String transaction;
    @XmlAttribute(name = "TransactionName")
    protected String transactionName;
    @XmlAttribute(name = "UserExitClass")
    protected String userExitClass;
    @XmlAttribute(name = "TransactionKey")
    protected String transactionKey;
    @XmlAttribute(name = "TransactionType")
    protected String transactionType;
    @XmlAttribute(name = "_DebugStreamFromConnector")
    protected String debugStreamFromConnector;

    /**
     * Gets the value of the connectorInput property.
     *
     * @return
     *     possible object is
     *     {@link com.eurobank.JAXBmodel.ConnectorInputType }
     *
     */
    public com.eurobank.JAXBmodel.ConnectorInputType getConnectorInput() {
        return connectorInput;
    }

    /**
     * Sets the value of the connectorInput property.
     *
     * @param value
     *     allowed object is
     *     {@link com.eurobank.JAXBmodel.ConnectorInputType }
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
     * Gets the value of the transactionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionName() {
        return transactionName;
    }

    /**
     * Sets the value of the transactionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionName(String value) {
        this.transactionName = value;
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

    /**
     * Gets the value of the transactionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionKey() {
        return transactionKey;
    }

    /**
     * Sets the value of the transactionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionKey(String value) {
        this.transactionKey = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the debugStreamFromConnector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebugStreamFromConnector() {
        return debugStreamFromConnector;
    }

    /**
     * Sets the value of the debugStreamFromConnector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebugStreamFromConnector(String value) {
        this.debugStreamFromConnector = value;
    }

}
