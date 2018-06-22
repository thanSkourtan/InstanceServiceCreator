
package com.eurobank.JAXBmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for FieldType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HostFormatAs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FormatClassParm" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Mandatory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MatchDataSetName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldType", propOrder = {
    "value"
})
public class FieldType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "AltamiraId")
    protected String altamiraId;
    @XmlAttribute(name = "HostFormatAs")
    protected String hostFormatAs;
    @XmlAttribute(name = "FormatClassParm")
    protected String formatClassParm;
    @XmlAttribute(name = "Mandatory")
    protected String mandatory;
    @XmlAttribute(name = "MatchDataSetName")
    protected String matchDataSetName;
    @XmlAttribute(name = "Default")
    protected String _default;

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
     * Gets the value of the altamiraId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public String getAltamiraId() {
        return altamiraId;
    }

    /**
     * Sets the value of the altamiraId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAltamiraId(String value) {
        this.altamiraId = value;
    }

    /**
     * Gets the value of the hostFormatAs property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHostFormatAs() {
        return hostFormatAs;
    }

    /**
     * Sets the value of the hostFormatAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostFormatAs(String value) {
        this.hostFormatAs = value;
    }

    /**
     * Gets the value of the formatClassParm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatClassParm() {
        return formatClassParm;
    }

    /**
     * Sets the value of the formatClassParm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatClassParm(String value) {
        this.formatClassParm = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandatory(String value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the matchDataSetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchDataSetName() {
        return matchDataSetName;
    }

    /**
     * Sets the value of the matchDataSetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchDataSetName(String value) {
        this.matchDataSetName = value;
    }

    public String get_default() {
        return _default;
    }

    public void set_default(String _default) {
        this._default = _default;
    }
}
