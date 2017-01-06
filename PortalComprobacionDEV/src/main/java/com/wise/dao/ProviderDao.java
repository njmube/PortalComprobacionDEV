package com.wise.dao;

import java.rmi.RemoteException;

import javax.activation.DataHandler;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.BUKRS_RAN;
import functions.rfc.sap.document.sap_com.BUKRS_RAN_ITAB;
import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.LIFNR_RANG;
import functions.rfc.sap.document.sap_com.LIFRE_RAN_ITAB;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2;
import functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS;
import functions.rfc.sap.document.sap_com.Y10_FG_ACC_STATUS;
import functions.rfc.sap.document.sap_com.Y10_FG_ACC_STATUSResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPEN;
import functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPENResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_STATEMENT;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_STATEMENTResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_FIELDS_FROM_XML;
import functions.rfc.sap.document.sap_com.Y10_GET_FIELDS_FROM_XMLResponse;
import functions.rfc.sap.document.sap_com.Y10_SEARCH_DOCUMENT_ID;
import functions.rfc.sap.document.sap_com.Y10_SEARCH_DOCUMENT_IDResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_RANGE_BLDAT;
import functions.rfc.sap.document.sap_com.Y10_STR_RANGE_ERDAT;
import functions.rfc.sap.document.sap_com.Y10_STR_RANGE_EXPENSEID;
import functions.rfc.sap.document.sap_com.Y10_STR_RANGE_STATUS;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_EXPSTATMENT_PARAMS;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_SEARCH_DOCUMENT;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_BLDAT;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_BUDAT;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_DEPTCODE;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_ERDAT;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_EXPENSEDOC;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_EXPENSEID;
import functions.rfc.sap.document.sap_com.Y10_TT_RANGE_STATUS;
import functions.rfc.sap.document.sap_com.ZWS_EXPENSE_UTILS_METHODSStub;
import functions.rfc.sap.document.sap_com.ZWS_METHS_FOR_EXPMANStub;

@Repository
public class ProviderDao {
	
	public final static Logger LOGGER = Logger.getLogger(ProviderDao.class);
	
	public Y10_GET_CONF_DATA_FOR_EXPENResponse getConfDataForExpen(String lifnr) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		Char10 ex_lifnr = new Char10();
		ex_lifnr.setChar10(lifnr);
		Y10_GET_CONF_DATA_FOR_EXPEN proveedor = new Y10_GET_CONF_DATA_FOR_EXPEN();
		proveedor.setEX_LIFNR(ex_lifnr);
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_GET_CONF_DATA_FOR_EXPENResponse response = stub.y10_GET_CONF_DATA_FOR_EXPEN(proveedor);
			LOGGER.info("y10_GET_CONF_DATA_FOR_EXPEN data: " + response);
			return response;
		} catch (RemoteException e) {
			LOGGER.error("Error Getting Conf Data For Expen: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error Getting Conf Data For Expen: ", e);
			return null;
		}
	}
		
	public Y10_GET_EXPENSE_STATEMENTResponse getExpenseStatement(String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado, String lifnr, String expenseid) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);

		Y10_GET_EXPENSE_STATEMENT y10GetExpenseStatement = new Y10_GET_EXPENSE_STATEMENT();
		Y10_STR_WBS_EXPSTATMENT_PARAMS y10StrWbsExpstatementParams = new Y10_STR_WBS_EXPSTATMENT_PARAMS();
		
		BUKRS_RAN_ITAB bukrsRanItab = new BUKRS_RAN_ITAB();
		BUKRS_RAN bukrsRan = new BUKRS_RAN();
		Char1 b_sign = new Char1();
		Char2 b_option = new Char2();
		Char4 b_low = new Char4();
		Char4 b_high = new Char4();
		b_sign.setChar1("I");
		b_option.setChar2("EQ");
		b_low.setChar4(cmbRazonSocial);
		b_high.setChar4(cmbRazonSocial);
		bukrsRan.setSIGN(b_sign);
		bukrsRan.setOPTION(b_option);
		bukrsRan.setLOW(b_low);
		bukrsRan.setHIGH(b_high);
		bukrsRanItab.addItem(bukrsRan);
		y10StrWbsExpstatementParams.setBUKRS_RAN(bukrsRanItab);
		
		Y10_TT_RANGE_EXPENSEID y10TtRangeExpenseid = new Y10_TT_RANGE_EXPENSEID();
		Y10_STR_RANGE_EXPENSEID y10StrRangeExpenseid = new Y10_STR_RANGE_EXPENSEID();
		Char1 e_sign = new Char1();
		Char2 e_option = new Char2();
		Numeric10 e_low = new Numeric10();
		Numeric10 e_high = new Numeric10();
		e_sign.setChar1("I");
		e_option.setChar2("EQ");
		e_low.setNumeric10(expenseid == null ? "" : expenseid);
		e_high.setNumeric10(expenseid == null ? "" : expenseid);
		y10StrRangeExpenseid.setSIGN(e_sign);
		y10StrRangeExpenseid.setOPTION(e_option);
		y10StrRangeExpenseid.setLOW(e_low);
		y10StrRangeExpenseid.setHIGH(e_high);
		y10TtRangeExpenseid.addItem(y10StrRangeExpenseid);
		y10StrWbsExpstatementParams.setEXPENSEID_RAN(y10TtRangeExpenseid);
		
		LIFRE_RAN_ITAB lifreRanItab = new LIFRE_RAN_ITAB();
		LIFNR_RANG lifnrRang = new LIFNR_RANG();
		Char1 l_sign = new Char1();
		Char2 l_option = new Char2();
		Char10 l_low = new Char10();
		Char10 l_high = new Char10();
		l_sign.setChar1("I");
		l_option.setChar2("EQ");
		l_low.setChar10(lifnr);
		l_high.setChar10(lifnr);
		lifnrRang.setSIGN(l_sign);
		lifnrRang.setOPTION(l_option);
		lifnrRang.setLOW(l_low);
		lifnrRang.setHIGH(l_high);
		lifreRanItab.addItem(lifnrRang);
		y10StrWbsExpstatementParams.setLIFNR_RAN(lifreRanItab);
		
		Y10_TT_RANGE_EXPENSEDOC y10TtRangeExpensedoc = new Y10_TT_RANGE_EXPENSEDOC();
		y10StrWbsExpstatementParams.setEXPENSEDOC_RAN(y10TtRangeExpensedoc);
		
		Y10_TT_RANGE_DEPTCODE y10TtRangeDeptcode = new Y10_TT_RANGE_DEPTCODE();
		y10StrWbsExpstatementParams.setDEPTCODE_RAN(y10TtRangeDeptcode);
		
		Y10_TT_RANGE_BLDAT y10TtRangeBldat = new Y10_TT_RANGE_BLDAT();
		Y10_STR_RANGE_BLDAT y10StrRangeBldat = new Y10_STR_RANGE_BLDAT();
		Char1 d_sign = new Char1();
		Char2 d_option = new Char2();
		Date10 d_low = new Date10();
		Date10 d_high = new Date10();
		d_sign .setChar1("I");
		d_option.setChar2("BT");
		d_low.setDate10(fechaIni);
		d_high.setDate10(fechaFin);
		y10StrRangeBldat.setSIGN(d_sign);
		y10StrRangeBldat.setOPTION(d_option);
		y10StrRangeBldat.setLOW(d_low);
		y10StrRangeBldat.setHIGH(d_high);
		y10TtRangeBldat.addItem(y10StrRangeBldat);
		y10StrWbsExpstatementParams.setBLDAT_RAN(y10TtRangeBldat);
		
		Y10_TT_RANGE_STATUS y10TtRangeStatus = new Y10_TT_RANGE_STATUS();
		Y10_STR_RANGE_STATUS y10StrRangeStatus = new Y10_STR_RANGE_STATUS();
		if ("00".equals(cmbEstado)) {
			Char1 s_sign = new Char1();
			Char2 s_option = new Char2();
			Char2 s_low = new Char2();
			Char2 s_high = new Char2();
			
			s_sign.setChar1("I");
			s_option.setChar2("BT");
			s_low.setChar2("01");
			s_high.setChar2("06");
			
			y10StrRangeStatus.setSIGN(s_sign);
			y10StrRangeStatus.setOPTION(s_option);
			y10StrRangeStatus.setLOW(s_low);
			y10StrRangeStatus.setHIGH(s_high);
		} else{
			Char1 s_sign = new Char1();
			Char2 s_option = new Char2();
			Char2 s_low = new Char2();
			Char2 s_high = new Char2();
			
			s_sign.setChar1("I");
			s_option.setChar2("EQ");
			s_low.setChar2(cmbEstado);
			s_high.setChar2(cmbEstado);
			
			y10StrRangeStatus.setSIGN(s_sign);
			y10StrRangeStatus.setOPTION(s_option);
			y10StrRangeStatus.setLOW(s_low);
			y10StrRangeStatus.setHIGH(s_high);
		}
		y10TtRangeStatus.addItem(y10StrRangeStatus);
		y10StrWbsExpstatementParams.setSTATUS_RAN(y10TtRangeStatus);
		
		y10GetExpenseStatement.setEX_SEARCH_PARAMS(y10StrWbsExpstatementParams);

		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_GET_EXPENSE_STATEMENTResponse response = stub.y10_GET_EXPENSE_STATEMENT(y10GetExpenseStatement);
			LOGGER.info("y10_GET_EXPENSE_STATEMENT data: " + response);
			return response;
		} catch (RemoteException e) {
			LOGGER.error("Error Getting Expense Statement: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error Getting Expense Statement: ", e);
			return null;
		}
	}
	
	public Y10_GET_FIELDS_FROM_XMLResponse getFieldsFromXml(DataHandler xmlFile) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		Y10_GET_FIELDS_FROM_XML y10GetFieldsFromXml = new Y10_GET_FIELDS_FROM_XML();
		y10GetFieldsFromXml.setEX_XML_DATA(xmlFile);
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_GET_FIELDS_FROM_XMLResponse response = stub.y10_GET_FIELDS_FROM_XML(y10GetFieldsFromXml);
			LOGGER.info("y10_GET_FIELDS_FROM_XML data: " + response);
			return response;
		} catch (RemoteException e) {
			LOGGER.error("Error Getting Fields From Xml: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error Getting Fields From Xml: ", e);
			return null;
		}
	}
	
	public Y10_SEARCH_DOCUMENT_IDResponse searchDocumentId(String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado, String lifnr) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);

		Y10_SEARCH_DOCUMENT_ID y10SearchDocumentId = new Y10_SEARCH_DOCUMENT_ID();
		Y10_STR_WBS_SEARCH_DOCUMENT y10StrWbsSearchDocument = new Y10_STR_WBS_SEARCH_DOCUMENT();
		
		BUKRS_RAN_ITAB bukrsRanItab = new BUKRS_RAN_ITAB();
		BUKRS_RAN bukrsRan = new BUKRS_RAN();
		Char1 b_sign = new Char1();
		Char2 b_option = new Char2();
		Char4 b_low = new Char4();
		Char4 b_high = new Char4();
		b_sign.setChar1("I");
		b_option.setChar2("EQ");
		b_low.setChar4(cmbRazonSocial);
		b_high.setChar4(cmbRazonSocial);
		bukrsRan.setSIGN(b_sign);
		bukrsRan.setOPTION(b_option);
		bukrsRan.setLOW(b_low);
		bukrsRan.setHIGH(b_high);
		bukrsRanItab.addItem(bukrsRan);
		y10StrWbsSearchDocument.setBUKRS_RAN(bukrsRanItab);
		
		Y10_TT_RANGE_EXPENSEID y10TtRangeExpenseid = new Y10_TT_RANGE_EXPENSEID();
		y10StrWbsSearchDocument.setEXPENSEID_RAN(y10TtRangeExpenseid);
		
		LIFRE_RAN_ITAB lifreRanItab = new LIFRE_RAN_ITAB();
		LIFNR_RANG lifnrRang = new LIFNR_RANG();
		Char1 l_sign = new Char1();
		Char2 l_option = new Char2();
		Char10 l_low = new Char10();
		Char10 l_high = new Char10();
		l_sign.setChar1("I");
		l_option.setChar2("EQ");
		l_low.setChar10(lifnr);
		l_high.setChar10(lifnr);
		lifnrRang.setSIGN(l_sign);
		lifnrRang.setOPTION(l_option);
		lifnrRang.setLOW(l_low);
		lifnrRang.setHIGH(l_high);
		lifreRanItab.addItem(lifnrRang);
		y10StrWbsSearchDocument.setLIFNR_RAN(lifreRanItab);
		
		Y10_TT_RANGE_EXPENSEDOC expensedoc = new Y10_TT_RANGE_EXPENSEDOC();
		y10StrWbsSearchDocument.setEXPENSEDOC_RAN(expensedoc);
		
		Y10_TT_RANGE_DEPTCODE deptcode=new Y10_TT_RANGE_DEPTCODE();
		y10StrWbsSearchDocument.setDEPTCODE_RAN(deptcode);
		
		Y10_TT_RANGE_BLDAT rangeBldat=new Y10_TT_RANGE_BLDAT();
		y10StrWbsSearchDocument.setBLDAT_RAN(rangeBldat);
		
		Y10_TT_RANGE_BUDAT rangeBudat=new Y10_TT_RANGE_BUDAT();
		y10StrWbsSearchDocument.setBUDAT_RAN(rangeBudat);
		
		Y10_TT_RANGE_STATUS y10TtRangeStatus = new Y10_TT_RANGE_STATUS();
		Y10_STR_RANGE_STATUS y10StrRangeStatus = new Y10_STR_RANGE_STATUS();
		if ("00".equals(cmbEstado)) {
			Char1 s_sign = new Char1();
			Char2 s_option = new Char2();
			Char2 s_low = new Char2();
			Char2 s_high = new Char2();
			
			s_sign.setChar1("I");
			s_option.setChar2("BT");
			s_low.setChar2("01");
			s_high.setChar2("06");
			
			y10StrRangeStatus.setSIGN(s_sign);
			y10StrRangeStatus.setOPTION(s_option);
			y10StrRangeStatus.setLOW(s_low);
			y10StrRangeStatus.setHIGH(s_high);
		} else{
			Char1 s_sign = new Char1();
			Char2 s_option = new Char2();
			Char2 s_low = new Char2();
			Char2 s_high = new Char2();
			
			s_sign.setChar1("I");
			s_option.setChar2("EQ");
			s_low.setChar2(cmbEstado);
			s_high.setChar2(cmbEstado);
			
			y10StrRangeStatus.setSIGN(s_sign);
			y10StrRangeStatus.setOPTION(s_option);
			y10StrRangeStatus.setLOW(s_low);
			y10StrRangeStatus.setHIGH(s_high);
		}
		y10TtRangeStatus.addItem(y10StrRangeStatus);
		y10StrWbsSearchDocument.setSTATUS_RAN(y10TtRangeStatus);
		
		Y10_TT_RANGE_ERDAT y10TtRangeErdat = new Y10_TT_RANGE_ERDAT();
		Y10_STR_RANGE_ERDAT y10StrRangeErdat = new Y10_STR_RANGE_ERDAT();
		Char1 e_sign = new Char1();
		Char2 e_option = new Char2();
		Date10 e_low = new Date10();
		Date10 e_high = new Date10();
		e_sign.setChar1("I");
		e_option.setChar2("BT");
		e_low.setDate10(fechaIni);
		e_high.setDate10(fechaFin);
		y10StrRangeErdat.setSIGN(e_sign);
		y10StrRangeErdat.setOPTION(e_option);
		y10StrRangeErdat.setLOW(e_low);
		y10StrRangeErdat.setHIGH(e_high);
		y10TtRangeErdat.addItem(y10StrRangeErdat);
		y10StrWbsSearchDocument.setERDAT_RAN(y10TtRangeErdat);
		
		Char1 i_reqind = new Char1();
		i_reqind.setChar1("");
		y10StrWbsSearchDocument.setREQIND(i_reqind);
		
		Char1 i_expind = new Char1();
		i_expind.setChar1("");
		y10StrWbsSearchDocument.setEXPIND(i_expind);
		
		y10SearchDocumentId.setEX_SEARCH_PARAMS(y10StrWbsSearchDocument);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_SEARCH_DOCUMENT_IDResponse response = stub.y10_SEARCH_DOCUMENT_ID(y10SearchDocumentId);
			LOGGER.info("y10_SEARCH_DOCUMENT_ID data: " + response);
			return response;
		} catch (RemoteException e) {
			LOGGER.error("Error Searching Document Id: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error Searching Document Id: ", e);
			return null;
		}
	}
	
	public Y10_FG_ACC_STATUSResponse getAccStatus(String lifnr, String cmbRazonSocial, String language) throws RemoteException, AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_EXPMANStub stub = new ZWS_METHS_FOR_EXPMANStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		Y10_FG_ACC_STATUS y10FgAccStatus = new Y10_FG_ACC_STATUS();
		Char4 iv_burks = new Char4();
		Char10 iv_lifnr = new Char10();
		Lang iv_language = new Lang();
		TABLE_OF_Y10_STR_ACC_STATUS et_openitems = new TABLE_OF_Y10_STR_ACC_STATUS();
		TABLE_OF_Y10_STR_ACC_STATUS et_cleareditems = new TABLE_OF_Y10_STR_ACC_STATUS();
		TABLE_OF_BAPIRET2 _return = new TABLE_OF_BAPIRET2();
		
		iv_burks.setChar4(cmbRazonSocial);
		iv_lifnr.setChar10(lifnr);
		iv_language.setLang("S");
		
		y10FgAccStatus.setIV_BUKRS(iv_burks);
		y10FgAccStatus.setIV_LIFNR(iv_lifnr);
		y10FgAccStatus.setIV_LANGUAGE(iv_language);
		y10FgAccStatus.setET_OPENITEMS(et_openitems);
		y10FgAccStatus.setET_CLEAREDITEMS(et_cleareditems);
		y10FgAccStatus.setRETURN(_return);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_FG_ACC_STATUSResponse response = stub.y10_FG_ACC_STATUS(y10FgAccStatus);
			LOGGER.info("y10_FG_ACC_STATUS data: " + response);
			return response;
		} catch (Exception e) {
			LOGGER.error("Error Getting Account Status: ",e);
			return null;
		}
	}

}
