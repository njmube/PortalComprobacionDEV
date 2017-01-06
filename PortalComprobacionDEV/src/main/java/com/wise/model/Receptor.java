package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Receptor", namespace = "cfdi")
@XmlType(propOrder = {"Domicilio" })
public class Receptor {
	@XmlElement(name="Domicilio",namespace="http://www.sat.gob.mx/cfd/3")
	private Domicilio Domicilio;
	@XmlAttribute
	private String nombre;
	@XmlAttribute
	private String rfc;
	public Domicilio getDomicilio() {
		return Domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		Domicilio = domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Receptor() {
		
		this.Domicilio =new Domicilio();
	}
	
}
