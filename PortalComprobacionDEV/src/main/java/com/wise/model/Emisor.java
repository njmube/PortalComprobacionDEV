package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Emisor", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlType(propOrder = {"DomicilioFiscal","ExpedidoEn","RegimenFiscal" })
public class Emisor {
	@XmlAttribute
	private String nombre;
	@XmlAttribute
	private String rfc;
	@XmlElement(name="DomicilioFiscal",namespace="http://www.sat.gob.mx/cfd/3")
	private DomicilioFiscal DomicilioFiscal;
	@XmlElement(name="ExpedidoEn",namespace="http://www.sat.gob.mx/cfd/3")
	private ExpedidoEn ExpedidoEn;
	@XmlElement(name="RegimenFiscal",namespace="http://www.sat.gob.mx/cfd/3")
	private RegimenFiscal RegimenFiscal;
	public DomicilioFiscal getDomicilioFiscal() {
		return DomicilioFiscal;
	}
	public void setDomicilioFiscal(DomicilioFiscal domicilioFiscal) {
		DomicilioFiscal = domicilioFiscal;
	}
	public ExpedidoEn getExpedidoEn() {
		return ExpedidoEn;
	}
	public void setExpedidoEn(ExpedidoEn expedidoEn) {
		ExpedidoEn = expedidoEn;
	}
	public RegimenFiscal getRegimenFiscal() {
		return RegimenFiscal;
	}
	public void setRegimenFiscal(RegimenFiscal regimenFiscal) {
		RegimenFiscal = regimenFiscal;
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
	public Emisor() {
		
		this.DomicilioFiscal =new DomicilioFiscal();
		this.ExpedidoEn = new ExpedidoEn();
		this.RegimenFiscal = new RegimenFiscal();
	}
	
	
}
