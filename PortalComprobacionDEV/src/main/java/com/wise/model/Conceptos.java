package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Conceptos")
@XmlType(propOrder = { "Concepto" })
public class Conceptos {
	@XmlElement(name="Concepto",namespace="http://www.sat.gob.mx/cfd/3")
	private Concepto[] Concepto;

	public Concepto[] getConcepto() {
		return Concepto;
	}

	public Concepto getConcepto(int index) {
		return this.Concepto[index];
	}

	public void setConcepto(Concepto[] concepto) {
		Concepto = concepto;
	}
		
}
