package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Traslados")
@XmlType(propOrder = { "Traslado" })
public class Traslados {
	@XmlElement(name="Traslado",namespace="http://www.sat.gob.mx/cfd/3")
	private Traslado[] Traslado;

	public Traslado[] getTraslado() {
		return Traslado;
	}

	public Traslado getTraslado(int index) {
		return this.Traslado[index];
	}

	public void setTraslado(Traslado[] traslado) {
		Traslado = traslado;
	}
	
}
