//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.18 at 03:02:42 AM MSK 
//


package io.spring.guides.gs_producing_web_service;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="car"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="grz" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id_category" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="date_add" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="mark" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
    "id",
    "grz",
    "category",
    "idCategory",
    "dateAdd",
    "mark",
    "model"
})
public class Car {

    protected long id;
    @XmlElement(required = true)
    protected String grz;
    @XmlElement(required = true)
    protected String category;
    @XmlElement(name = "id_category", required = true)
    protected BigInteger idCategory;
    @XmlElement(name = "date_add", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdd;
    @XmlElement(required = true)
    protected String mark;
    @XmlElement(required = true)
    protected String model;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the grz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrz() {
        return grz;
    }

    /**
     * Sets the value of the grz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrz(String value) {
        this.grz = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the idCategory property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdCategory() {
        return idCategory;
    }

    /**
     * Sets the value of the idCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdCategory(BigInteger value) {
        this.idCategory = value;
    }

    /**
     * Gets the value of the dateAdd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAdd() {
        return dateAdd;
    }

    /**
     * Sets the value of the dateAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAdd(XMLGregorianCalendar value) {
        this.dateAdd = value;
    }

    /**
     * Gets the value of the mark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMark() {
        return mark;
    }

    /**
     * Sets the value of the mark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMark(String value) {
        this.mark = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

}