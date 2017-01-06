package com.wise.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.dao.ProviderDao;
import com.wise.model.Comprobante;
import com.wise.FileHandler;

import functions.rfc.sap.document.sap_com.Y10_FG_ACC_STATUSResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPENResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_STATEMENTResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_FIELDS_FROM_XMLResponse;
import functions.rfc.sap.document.sap_com.Y10_SEARCH_DOCUMENT_IDResponse;

@Service
public class ProviderService {
	
	@Autowired
	ProviderDao providerDao;
	
	@Transactional(readOnly = true)
	public Y10_GET_CONF_DATA_FOR_EXPENResponse getConfDataForExpen(String lifnr) throws AxisFault {
		return providerDao.getConfDataForExpen(lifnr);
	}
	
	@Transactional(readOnly = true)
	public Y10_GET_EXPENSE_STATEMENTResponse getExpenseStatement(String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado, String lifnr, String expenseid) throws AxisFault {
		return providerDao.getExpenseStatement(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, lifnr, expenseid);
	}
	
	@Transactional(readOnly = true)
	public Y10_GET_FIELDS_FROM_XMLResponse getValueFromXml(DataHandler xmlFile) throws RemoteException, ADBException {
		return providerDao.getFieldsFromXml(xmlFile);
	}
	
	public Comprobante readFieldsFromXml(byte[] xmlFile ) throws UnsupportedEncodingException, JAXBException{
		String xmlCFdi = new String(xmlFile,"UTF-8");		
		Comprobante comprobante = new Comprobante();
		comprobante = (Comprobante) FileHandler.unserializeToObject(comprobante, xmlCFdi);
		return comprobante;
	}
	
	@Transactional(readOnly = true)
	public Y10_SEARCH_DOCUMENT_IDResponse searchDocumentId(String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado, String lifnr) throws AxisFault {
		return providerDao.searchDocumentId(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, lifnr);
	}
	
	@Transactional(readOnly = true)
	public Y10_FG_ACC_STATUSResponse getAccStatus(String lifnr, String cmbRazonSocial, String language) throws Exception, RemoteException {
		return providerDao.getAccStatus(lifnr, cmbRazonSocial, language);
	}

}
