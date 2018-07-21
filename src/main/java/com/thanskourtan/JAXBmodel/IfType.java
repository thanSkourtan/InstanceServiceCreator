
package com.thanskourtan.JAXBmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for IfType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IfType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="OnExit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OnTrue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OnFalse" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IfType", propOrder = {
    "value"
})
public class IfType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "OnExit")
    protected String onExit;
    @XmlAttribute(name = "OnTrue")
    protected String onTrue;
    @XmlAttribute(name = "OnFalse")
    protected String onFalse;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the onExit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnExit() {
        return onExit;
    }

    /**
     * Sets the value of the onExit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnExit(String value) {
        this.onExit = value;
    }

    /**
     * Gets the value of the onTrue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnTrue() {
        return onTrue;
    }

    /**
     * Sets the value of the onTrue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnTrue(String value) {
        this.onTrue = value;
    }

    /**
     * Gets the value of the onFalse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnFalse() {
        return onFalse;
    }

    /**
     * Sets the value of the onFalse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnFalse(String value) {
        this.onFalse = value;
    }

}
