
package com.eurobank.JAXBmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Step" type="{}StepType"/>
 *         &lt;element name="If" type="{}IfType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationType", propOrder = {
    "step",
    "_if"
})
public class OperationType {

    @XmlElement(name = "Step", required = true)
    protected StepType step;
    @XmlElement(name = "If", required = true)
    protected IfType _if;

    /**
     * Gets the value of the step property.
     * 
     * @return
     *     possible object is
     *     {@link StepType }
     *     
     */
    public StepType getStep() {
        return step;
    }

    /**
     * Sets the value of the step property.
     * 
     * @param value
     *     allowed object is
     *     {@link StepType }
     *     
     */
    public void setStep(StepType value) {
        this.step = value;
    }

    /**
     * Gets the value of the if property.
     * 
     * @return
     *     possible object is
     *     {@link IfType }
     *     
     */
    public IfType getIf() {
        return _if;
    }

    /**
     * Sets the value of the if property.
     * 
     * @param value
     *     allowed object is
     *     {@link IfType }
     *     
     */
    public void setIf(IfType value) {
        this._if = value;
    }

}
