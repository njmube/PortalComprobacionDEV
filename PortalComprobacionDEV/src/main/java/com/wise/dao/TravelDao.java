package com.wise.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Repository;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char256;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Cuky5;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.Time;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_TRAVEL_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_TRAVEL_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_TRAVEL_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER_KEY;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_TRAVEL_DATA;
import functions.rfc.sap.document.sap_com.Y10_TT_TEXT;
import functions.rfc.sap.document.sap_com.ZWS_METHS_FOR_HANDLE_TRAVELStub;

@Repository
public class TravelDao {
	
	public final static Logger LOGGER = Logger.getLogger(TravelDao.class);
	
	public Y10_SAVE_TRAVEL_DATAResponse saveTravelData(String cmbRazonSocial, String cmbTipoDocumento, String requestDate, String createdDate, String comments, String waers, String deptcode, String subdeptcode, String createdBy, String lifnr) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_HANDLE_TRAVELStub stub = new ZWS_METHS_FOR_HANDLE_TRAVELStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);

		Y10_INSTANCE_TRAVEL_DATA y10InstanceTravelData = new Y10_INSTANCE_TRAVEL_DATA(); //Instanciar Datos Viaje
		Y10_STR_HEADER_KEY y10StrHeaderKey = new Y10_STR_HEADER_KEY(); //Estructura cabecera comprobación de gastos
		
		Char4 i_bukrs = new Char4();
		Numeric10 i_expenseid = new Numeric10();
		i_bukrs.setChar4(cmbRazonSocial);
		i_expenseid.setNumeric10("");
		y10StrHeaderKey.setBUKRS(i_bukrs);
		y10StrHeaderKey.setEXPENSEID(i_expenseid);
		y10InstanceTravelData.setEX_EXPENSE_KEY(y10StrHeaderKey);
		stub.y10_INSTANCE_TRAVEL_DATA(y10InstanceTravelData);
		
		Y10_SAVE_TRAVEL_DATA y10SaveTravelData = new Y10_SAVE_TRAVEL_DATA(); //Almacenar datos notificación viaje
		Y10_STR_WBS_TRAVEL_DATA y10StrWbsTravelData = new Y10_STR_WBS_TRAVEL_DATA(); //Estructura web service datos notif. viaje
		
		Y10_STR_HEADER y10StrHeader = new Y10_STR_HEADER(); //Estructura cabecera comprobación de gastos
		Char4 i_burks = new Char4();
		Char10 i_deptcode = new Char10();
		Date10 i_erdat = new Date10();
		Time i_erzet = new Time();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		org.apache.axis2.databinding.types.Time i_currenttime = new org.apache.axis2.databinding.types.Time(sdf.format(new Date()));
		Char4 i_expensedoc = new Char4();
		Char10 i_lifnr = new Char10();
		Date10 i_reqexpdat = new Date10();
		Char10 i_subdeptcode = new Char10();
		Cuky5 i_waers = new Cuky5();
		
		i_burks.setChar4(cmbRazonSocial);
		i_deptcode.setChar10(deptcode);
		i_erdat.setDate10(createdDate);
		i_erzet.setTime(i_currenttime);
		i_expensedoc.setChar4(cmbTipoDocumento);
		i_lifnr.setChar10(lifnr);
		i_reqexpdat.setDate10(requestDate);
		i_subdeptcode.setChar10(subdeptcode);
		i_waers.setCuky5(waers);
		
		y10StrHeader.setBUKRS(i_burks);
		y10StrHeader.setDEPTCODE(i_deptcode);
		y10StrHeader.setERDAT(i_erdat);
		y10StrHeader.setERZET(i_erzet);
		y10StrHeader.setEXPENSEDOC(i_expensedoc);
		y10StrHeader.setLIFNR(i_lifnr);
		y10StrHeader.setREQEXPDAT(i_reqexpdat);
		y10StrHeader.setSUBDEPTCODE(i_subdeptcode);
		y10StrHeader.setWAERS(i_waers);

		Y10_TT_TEXT y10TtText = new Y10_TT_TEXT(); //Tipo Tabla Texto
		Char256 i_textItem = new Char256();
		i_textItem.setChar256(comments);
		Char256[] i_text = new Char256[] { i_textItem };
		y10TtText.setItem(i_text);

		y10StrWbsTravelData.setHEADER_DATA(y10StrHeader);
		y10StrWbsTravelData.setHEADER_TEXT(y10TtText);
		Char1 i_operation = new Char1(); //Tipo Operacion 1=Insertar /2= Actualizar
		i_operation.setChar1("1");
		y10StrWbsTravelData.setOPERATION(i_operation);
		y10SaveTravelData.setEX_TRAVEL_DATA(y10StrWbsTravelData);
		
		Y10_SAVE_TRAVEL_DATAResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.y10_SAVE_TRAVEL_DATA(y10SaveTravelData);
		} catch (RemoteException e) {
			LOGGER.error("Error with saveTravelData: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error with saveTravelData: ", e);
			return null;
		}
		
		return response;
	}

}
