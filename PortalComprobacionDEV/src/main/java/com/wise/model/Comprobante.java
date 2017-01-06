package com.wise.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
 

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Comprobante", namespace = "http://www.sat.gob.mx/cfd/3")
@XmlType(propOrder = { "Emisor","Receptor","Conceptos","Impuestos","Complemento"})
public class Comprobante {
	@XmlAttribute(name="LugarExpedicion")
	private String lugarExpedicion;
	@XmlAttribute(name="Moneda")
	private String moneda;
	@XmlAttribute(name="NumCtaPago")
	private String numCtaPago;
	@XmlAttribute(name="TipoCambio")
	private String tipoCambio;
	@XmlAttribute
	private String certificado;
	@XmlAttribute
	private String formaDePago;
	@XmlAttribute
	private String folio;
	@XmlAttribute
	private String metodoDePago;
	@XmlAttribute
	private String noCertificado;
	@XmlAttribute
	private String sello;
	@XmlAttribute
	private String total;
	@XmlAttribute
	private String version;
	@XmlAttribute
	private String Serie;
	@XmlAttribute
	private String subTotal;
	@XmlAttribute(name="fecha")
	private String fecha;
	@XmlAttribute
	private String condicionesDePago;
	@XmlAttribute
	private String descuento;
	@XmlAttribute
	private String tipoDeComprobante;
	 
	@XmlElement(name="Emisor",namespace="http://www.sat.gob.mx/cfd/3")
	private Emisor Emisor;
	@XmlElement(name="Receptor",namespace="http://www.sat.gob.mx/cfd/3")
	private Receptor Receptor;
	@XmlElement(name="Conceptos",namespace="http://www.sat.gob.mx/cfd/3")
	private Conceptos Conceptos;
	@XmlElement(name="Impuestos",namespace="http://www.sat.gob.mx/cfd/3")
	private Impuestos Impuestos;
	@XmlElement(name="Complemento",namespace="http://www.sat.gob.mx/cfd/3")
	private Complemento Complemento;
	
	public String getCondicionesDePago() {
		return condicionesDePago; 
	}
	public void setCondicionesDePago(String condicionesDePago) {
		this.condicionesDePago = condicionesDePago;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}
	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}
	public String getSerie() {
		return Serie;
	}
	public void setSerie(String serie) {
		Serie = serie;
	}
	public String getLugarExpedicion() {
		return lugarExpedicion;
	}
	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getNumCtaPago() {
		return numCtaPago;
	}
	public void setNumCtaPago(String numCtaPago) {
		this.numCtaPago = numCtaPago;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getMetodoDePago() {
		return metodoDePago;
	}
	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}
	public String getNoCertificado() {
		return noCertificado;
	}
	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}
	public String getSello() {
		return sello;
	}
	public void setSello(String sello) {
		this.sello = sello;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Emisor getEmisor() {
		return Emisor;
	}
	public void setEmisor(Emisor emisor) {
		Emisor = emisor;
	}
	public Receptor getReceptor() {
		return Receptor;
	}
	public void setReceptor(Receptor receptor) {
		Receptor = receptor;
	}
	public Conceptos getConceptos() {
		return Conceptos;
	}
	public void setConceptos(Conceptos conceptos) {
		Conceptos = conceptos;
	}
	public Impuestos getImpuestos() {
		return Impuestos;
	}
	public void setImpuestos(Impuestos impuestos) {
		Impuestos = impuestos;
	}
	public Complemento getComplemento() {
		return Complemento;
	}
	public void setComplemento(Complemento complemento) {
		Complemento = complemento;
	}
	
	public String getCadenaOriginal(){
		String pipe = "|";
		String cadenaOriginal = "";
		if(this.getComplemento().getTimbreFiscalDigital() != null){
			TimbreFiscalDigital tfd = this.getComplemento().getTimbreFiscalDigital();
			cadenaOriginal = pipe + pipe + tfd.getVersion()
					+ pipe + tfd.getUUID() + pipe + tfd.getFechaTimbrado() + pipe
					+ this.sello + pipe + tfd.getNoCertificadoSAT() + pipe+ pipe;
		}
		
		return cadenaOriginal;
	}
	public Comprobante() {
		
		this.Emisor = new Emisor();
		this.Receptor = new Receptor();
		this.Conceptos= new Conceptos();
		this.Impuestos =new Impuestos();
		this.Complemento= new Complemento();
		
	}
	
	public BigDecimal recoverIVATrasladado(){
		BigDecimal ivaTrasladado = new BigDecimal(0);
		
		if (this.getImpuestos() != null){
			if (this.getImpuestos().getTraslados() != null){
					for(Traslado t: this.getImpuestos().getTraslados().getTraslado()){
						System.out.println("->" + t.getImpuesto() + ": " + t.getImporte());
						if (t.getImpuesto().equals("IVA")) {
							BigDecimal newAmount = new BigDecimal(t.getImporte().trim());
							//System.out.println("->Found" + t.getImpuesto() + ": " + newAmount);
							ivaTrasladado = ivaTrasladado.add(newAmount);
							//System.out.println("->Found 2" + t.getImpuesto() + ": " + ivaTrasladado);
						}
					}
			}
		}
		return ivaTrasladado;
	}
	
	public BigDecimal recoverIEPSTrasladado(){
		BigDecimal iepsTrasladado = new BigDecimal(0);
		
		if (this.getImpuestos() != null){
			if (this.getImpuestos().getTraslados() != null){
					for(Traslado t: this.getImpuestos().getTraslados().getTraslado()){
						if (t.getImpuesto().equals("IEPS")) {
							iepsTrasladado = iepsTrasladado.add(new BigDecimal(t.getImporte().trim()));
						}
					}
					
			}
		}
		return iepsTrasladado;
	}
	
}

