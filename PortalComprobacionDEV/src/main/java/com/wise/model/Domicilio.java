package com.wise.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "Domicilio", namespace = "cfdi")
@XmlType(propOrder = { "calle","codigoPostal","colonia","estado","localidad",
		"municipio","noExterior","pais" })
public class Domicilio {

		@XmlAttribute
		private String calle;
		@XmlAttribute
		private String codigoPostal;
		@XmlAttribute
		private String colonia;
		@XmlAttribute
		private String estado;
		@XmlAttribute
		private String localidad;
		@XmlAttribute
		private String municipio;
		@XmlAttribute
		private String noExterior;		 
		@XmlAttribute
		private String pais;
		public String getCalle() {
			return calle;
		}
		public void setCalle(String calle) {
			this.calle = calle;
		}
		public String getCodigoPostal() {
			return codigoPostal;
		}
		public void setCodigoPostal(String codigoPostal) {
			this.codigoPostal = codigoPostal;
		}
		public String getColonia() {
			return colonia;
		}
		public void setColonia(String colonia) {
			this.colonia = colonia;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getLocalidad() {
			return localidad;
		}
		public void setLocalidad(String localidad) {
			this.localidad = localidad;
		}
		public String getMunicipio() {
			return municipio;
		}
		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}
		public String getNoExterior() {
			return noExterior;
		}
		public void setNoExterior(String noExterior) {
			this.noExterior = noExterior;
		}
		public String getPais() {
			return pais;
		}
		public void setPais(String pais) {
			this.pais = pais;
		}
		
}
