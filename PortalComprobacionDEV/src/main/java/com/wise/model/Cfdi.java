package com.wise.model;

import java.io.Serializable;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "xmlContent" })
public class Cfdi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATTR_CFDI_ID = "cfdiId";
	public static final String ATTR_UUID = "uuid";
	public static final String ATTR_RFCE = "rfce";
	public static final String ATTR_RFCR = "rfcr";
	public static final String ATTR_STATUS_TYPE = "statusType";
	public static final String ATTR_DOC_REF = "docRef";
	public static final String ATTR_DATE = "cfdiDate";
	public static final String ATTR_ERP_ACC_DOC_REF = "erpAccDocRef";

	public Cfdi() {
		this.setUuid("");
		this.setSearchKey("");
		this.setRfce("");
		this.setRfcr("");
		this.setFolio("");
		this.setSerie("");
		this.setNombreEmisor("");
		this.setFecha("");
		this.setFechaTimbrado("");
		this.setStatusType("");
		this.setStatusMessage("");
	}

	public String getDocRef() {
		return docRef;
	}

	public void setDocRef(String docRef) {
		this.docRef = docRef;
	}

	public String getErpAccDocRef() {
		return erpAccDocRef;
	}

	public void setErpAccDocRef(String erpAccDocRef) {
		this.erpAccDocRef = erpAccDocRef;
	}

	public Integer getCfdiId() {
		return cfdiId;
	}

	public void setCfdiId(Integer cfdiId) {
		this.cfdiId = cfdiId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRfce() {
		return rfce;
	}

	public void setRfce(String rfce) {
		this.rfce = rfce;
	}

	public String getRfcr() {
		return rfcr;
	}

	public void setRfcr(String rfcr) {
		this.rfcr = rfcr;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaTimbrado() {
		return fechaTimbrado;
	}

	public void setFechaTimbrado(String fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getXmlContent() {
		return xmlContent;
	}

	

	

	
	

	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}


	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void seteSystemClientId(Integer eSystemClientId) {
		this.eSystemClientId = eSystemClientId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public Integer geteSystemClientId() {
		return eSystemClientId;
	}

	private Integer cfdiId;
	private String uuid;
	private String rfce;
	private String rfcr;
	private BigDecimal total;
	private BigDecimal exchangeRate;
	private String currency;

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	private String folio;
	private String serie;
	private String nombreEmisor;
	private String fecha;
	private String fechaTimbrado;
	private String statusType;
	private String statusMessage;
	private Integer enterpriseId;
	private Integer eSystemClientId;
	private String xmlContent;
	private String docRef;
	private String erpAccDocRef;
	private String searchKey;
	private String systemAppSource;

	public String getSystemAppSource() {
		return systemAppSource;
	}

	public void setSystemAppSource(String systemAppSource) {
		this.systemAppSource = systemAppSource;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	
	

	

	public void copyFromDataCfdi(Cfdi cfdi) {
		// this.setCfdiId(cfdi.getCfdiId());
		this.setUuid(cfdi.getUuid());
		this.setRfce(cfdi.getRfce());
		this.setRfcr(cfdi.getRfcr());
		this.setTotal(cfdi.getTotal());
		this.setFolio(cfdi.getFolio());
		this.setSerie(cfdi.getSerie());
		this.setNombreEmisor(cfdi.getNombreEmisor());
		this.setFecha(cfdi.getFecha());
		this.setFechaTimbrado(cfdi.getFechaTimbrado());
		this.setStatusType(cfdi.getStatusType());
		this.setStatusMessage(cfdi.getStatusMessage());
		this.setEnterpriseId(cfdi.getEnterpriseId());
		this.seteSystemClientId(cfdi.geteSystemClientId());
		this.setXmlContent(cfdi.getXmlContent());
		
		this.setDocRef(cfdi.getDocRef());
		this.setErpAccDocRef(cfdi.getErpAccDocRef());
	}

	public String getFecha() {
		return fecha;
	}
}
