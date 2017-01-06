package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "RegimenFiscal")
@XmlType(propOrder = {"Regimen"})
public class RegimenFiscal {
	@XmlAttribute(name="Regimen")
	private String Regimen;

	public String getRegimen() {
		return Regimen;
	}

	public void setRegimen(String regimen) {
		Regimen = regimen;
	}
	
}
