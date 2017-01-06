package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Concepto", namespace="http://www.sat.gob.mx/cfd/3")
@XmlType(propOrder = {})
public class Concepto {
	@XmlAttribute(name="cantidad")
	private String cantidad;
	@XmlAttribute(name="descripcion")
	private String descripcion;
	@XmlAttribute(name="importe")
	private String importe;
	@XmlAttribute(name="noIdentificacion")
	private String noIdentificacion;
	@XmlAttribute(name="unidad")
	private String unidad;
	@XmlAttribute(name="valorUnitario")
	private String valorUnitario;
	
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImporte() {
		return importe;
	}
	
	public String getNoIdentificacion() {
		return noIdentificacion;
	}
	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
}
