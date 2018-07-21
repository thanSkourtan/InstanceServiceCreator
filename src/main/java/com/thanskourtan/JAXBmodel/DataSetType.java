
package com.thanskourtan.JAXBmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Field" type="{}FieldType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FormatAs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DefaultEncode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MatchTargetField" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BeanClass" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RecordLength" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxElements" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetType", propOrder = {
    "field"
})
public class DataSetType {

    @XmlElement(name = "Field")
    protected List<FieldType> field;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "FormatAs")
    protected String formatAs;
    @XmlAttribute(name = "Encode")
    protected String encode;
    @XmlAttribute(name = "DefaultEncode")
    protected String defaultEncode;
    @XmlAttribute(name = "MatchTargetField")
    protected String matchTargetField;
    @XmlAttribute(name = "BeanClass")
    protected String beanClass;
    @XmlAttribute(name = "RecordLength")
    protected String recordLength;
    @XmlAttribute(name = "MaxElements")
    protected String maxElements;

    /**
     * Gets the value of the field property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the field property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldType }
     * 
     * 
     */
    public List<FieldType> getField() {
        if (field == null) {
            field = new ArrayList<FieldType>();
        }
        return this.field;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the formatAs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatAs() {
        return formatAs;
    }

    /**
     * Sets the value of the formatAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatAs(String value) {
        this.formatAs = value;
    }

    /**
     * Gets the value of the defaultEncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultEncode() {
        return defaultEncode;
    }

    /**
     * Sets the value of the defaultEncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultEncode(String value) {
        this.defaultEncode = value;
    }

    /**
     * Gets the value of the matchTargetField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchTargetField() {
        return matchTargetField;
    }

    /**
     * Sets the value of the matchTargetField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchTargetField(String value) {
        this.matchTargetField = value;
    }

    /**
     * Gets the value of the beanClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeanClass() {
        return beanClass;
    }

    /**
     * Sets the value of the beanClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeanClass(String value) {
        this.beanClass = value;
    }

    /**
     * Gets the value of the recordLength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordLength() {
        return recordLength;
    }

    /**
     * Sets the value of the recordLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordLength(String value) {
        this.recordLength = value;
    }

    /**
     * Gets the value of the maxElements property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxElements() {
        return maxElements;
    }

    /**
     * Sets the value of the maxElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxElements(String value) {
        this.maxElements = value;
    }

}
