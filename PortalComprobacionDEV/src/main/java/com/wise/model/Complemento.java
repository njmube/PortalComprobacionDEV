package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Complemento", namespace = "cfdi")
@XmlType(propOrder = { "TimbreFiscalDigital"})
public class Complemento {
	
	@XmlElement(name="TimbreFiscalDigital",namespace="http://www.sat.gob.mx/TimbreFiscalDigital")
	private TimbreFiscalDigital TimbreFiscalDigital;
	
	
	public TimbreFiscalDigital getTimbreFiscalDigital() {
		return TimbreFiscalDigital;
	}
	public void setTimbreFiscalDigital(TimbreFiscalDigital timbreFiscalDigital) {
		TimbreFiscalDigital = timbreFiscalDigital;
	}
	public Complemento() {
		
		this.TimbreFiscalDigital =new TimbreFiscalDigital();
	}
	
}
