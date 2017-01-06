package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "TimbreFiscalDigital", namespace = "tfd")
@XmlType(propOrder = { "FechaTimbrado","UUID","selloCFD","selloSAT","noCertificadoSAT", "version" })
public class TimbreFiscalDigital {
	@XmlAttribute(name="FechaTimbrado")
	private String FechaTimbrado;
	@XmlAttribute(name="UUID")
	private String UUID;
	@XmlAttribute
	private String selloCFD;
	@XmlAttribute
	private String selloSAT;
	@XmlAttribute
	private String noCertificadoSAT;
	@XmlAttribute
	private String version;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getNoCertificadoSAT() {
		return noCertificadoSAT;
	}
	public void setNoCertificadoSAT(String noCertificadoSAT) {
		this.noCertificadoSAT = noCertificadoSAT;
	}
	public String getFechaTimbrado() {
		return FechaTimbrado;
	}
	public void setFechaTimbrado(String fechaTimbrado) {
		FechaTimbrado = fechaTimbrado;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getSelloCFD() {
		return selloCFD;
	}
	public void setSelloCFD(String selloCFD) {
		this.selloCFD = selloCFD;
	}
	public String getSelloSAT() {
		return selloSAT;
	}
	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}
	
}
