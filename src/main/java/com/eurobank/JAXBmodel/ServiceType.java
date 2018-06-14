
package com.eurobank.JAXBmodel;

import com.eurobank.JAXBmodel.*;
import com.eurobank.JAXBmodel.BTTLU0J2CType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ISERIESJ2C" type="{}ISERIESJ2CType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceType", propOrder = {
    "iseriesj2C"
})
public class ServiceType {

    @XmlElement(name = "ISERIESJ2C", required = true)
    protected ISERIESJ2CType iseriesj2C;

    @XmlElement(name = "BTTLU0J2C")
    protected BTTLU0J2CType bttlu0J2C;

    /**
     * Gets the value of the iseriesj2C property.
     * 
     * @return
     *     possible object is
     *     {@link ISERIESJ2CType }
     *     
     */
    public ISERIESJ2CType getISERIESJ2C() {
        return iseriesj2C;
    }

    /**
     * Sets the value of the iseriesj2C property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISERIESJ2CType }
     *     
     */
    public void setISERIESJ2C(ISERIESJ2CType value) {
        this.iseriesj2C = value;
    }

    public BTTLU0J2CType getBttlu0J2C() {
        return bttlu0J2C;
    }
}
