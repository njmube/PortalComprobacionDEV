package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Impuestos", namespace = "cfdi")
@XmlType(propOrder = { "Traslados" })
public class Impuestos {
	
	@XmlElement(name="Traslados",namespace="http://www.sat.gob.mx/cfd/3")
	private Traslados Traslados;
	
	public Traslados getTraslados() {
		return Traslados;
	}
	public void setTraslados(Traslados traslados) {
		Traslados = traslados;
	}
	public Impuestos() {
		//this.Traslados = new Traslados();
	}
	
}
