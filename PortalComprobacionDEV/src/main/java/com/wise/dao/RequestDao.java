package com.wise.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Repository;

import com.wise.Constants;
import com.wise.dto.PaymentDetailsDto;
import com.wise.dto.HeaderDataDto;
import com.wise.dto.ItemDataDto;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char20;
import functions.rfc.sap.document.sap_com.Char25;
import functions.rfc.sap.document.sap_com.Char35;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Char40;
import functions.rfc.sap.document.sap_com.Cuky5;
import functions.rfc.sap.document.sap_com.Curr132;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.Decimal95;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.Numeric4;
import functions.rfc.sap.document.sap_com.Time;
import functions.rfc.sap.document.sap_com.Y10_GET_REQUEST_DATA;
import functions.rfc.sap.document.sap_com.Y10_GET_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_REQUEST_DATA;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_TRAVEL_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_REQUEST_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER_KEY;
import functions.rfc.sap.document.sap_com.Y10_STR_ITEM_REQ;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_REQUEST_DATA;
import functions.rfc.sap.document.sap_com.Y10_TT_ITEM_REQ;
import functions.rfc.sap.document.sap_com.Y10_TT_TEXT;
import functions.rfc.sap.document.sap_com.ZWS_METHS_FOR_HANDLE_REQUESTStub;

@Repository
public class RequestDao {
	
	public final static Logger LOGGER = Logger.getLogger(RequestDao.class);
	
	public Y10_GET_REQUEST_DATAResponse getRequestData(String bukrs, String expenseId) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_HANDLE_REQUESTStub stub = new ZWS_METHS_FOR_HANDLE_REQUESTStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);

		Y10_INSTANCE_REQUEST_DATA y10InstanceRequestData = new Y10_INSTANCE_REQUEST_DATA(); //Instanciar request data
		Y10_STR_HEADER_KEY y10StrHeaderKey = new Y10_STR_HEADER_KEY(); //Estructura cabecera comprobación de gastos
		Char4 i_bukrs = new Char4();
		Numeric10 i_expenseId = new Numeric10();
		i_expenseId.setNumeric10(expenseId);
		i_bukrs.setChar4(bukrs);
		y10StrHeaderKey.setBUKRS(i_bukrs);
		y10StrHeaderKey.setEXPENSEID(i_expenseId);
		y10InstanceRequestData.setEX_EXPENSE_KEY(y10StrHeaderKey);

		Y10_INSTANCE_TRAVEL_DATA y10InstanceTravelData = new Y10_INSTANCE_TRAVEL_DATA(); //Instanciar Datos Viaje
		y10InstanceTravelData.setEX_EXPENSE_KEY(y10StrHeaderKey);

		Y10_GET_REQUEST_DATA y10GetRequestData = new Y10_GET_REQUEST_DATA(); //Obtener datos solicitud de anticipo
		y10GetRequestData.setEX_EXPENSE_KEY(y10StrHeaderKey);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_INSTANCE_REQUEST_DATAResponse response = stub.y10_INSTANCE_REQUEST_DATA(y10InstanceRequestData);
			LOGGER.info("y10_INSTANCE_REQUEST_DATA data: " + response);
			Y10_GET_REQUEST_DATAResponse resp = stub.y10_GET_REQUEST_DATA(y10GetRequestData);
			LOGGER.info("y10_GET_REQUEST_DATA data: " + resp);
			return resp;
		} catch (Exception e) {
			LOGGER.error("Error Getting Request Data: ", e);
			return null;
		}
	}
	
	public Y10_SAVE_REQUEST_DATAResponse saveRequestData(PaymentDetailsDto paymentDetailsDto) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_HANDLE_REQUESTStub stub = new ZWS_METHS_FOR_HANDLE_REQUESTStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		Y10_SAVE_REQUEST_DATA y10SaveRequestData = new Y10_SAVE_REQUEST_DATA(); //Almacenar datos anticipo en BD
		Y10_STR_WBS_REQUEST_DATA y10StrWbsRequestData = new Y10_STR_WBS_REQUEST_DATA(); //Estructura web service datos anticipo
		
		Y10_STR_HEADER y10StrHeader = new Y10_STR_HEADER(); //Estructura cabecera comprobación de gastos
		Y10_TT_ITEM_REQ y10TtItemReq = new Y10_TT_ITEM_REQ(); //Tipo Tabla Anticipo Gastos
		Y10_TT_TEXT y10TtText = new Y10_TT_TEXT(); //Tipo Tabla Texto

		HeaderDataDto headerDataDto = paymentDetailsDto.getHeaderDataDto();
		
		Char12 i_admin=new Char12();
		Date10 i_aedat=new Date10();
		Date10 i_apprdate=new Date10();
		Char12 i_approver=new Char12();
		Date10 i_bldat=new Date10();
		Date10 i_budat=new Date10();
		Char4 i_bukrs=new Char4();
		Char25 i_buktx=new Char25();
		Char10 i_deptcode=new Char10();
		Char10 i_deptkostl=new Char10();
		Char40 i_deptxt=new Char40();
		Date10 i_erdat=new Date10();
		Char12 i_ernam=new Char12();
		Time i_erzet=new Time();
		Char4 i_expensedoc=new Char4();
		Char40 i_expensedocdes=new Char40();
		Numeric10 i_expenseid=new Numeric10();
		Char1 i_expind=new Char1();
		Decimal95 i_Kursf=new Decimal95();
		Char35 i_Lifnam=new Char35();
		Char10 i_Lifnr=new Char10();
		Curr132 i_Netamtexp=new Curr132();
		Curr132 i_Netamtreq=new Curr132();
		Date10 i_Postdate=new Date10();
		Char12 i_Postedby=new Char12();
		Char10 i_Preldoc=new Char10();
		Numeric4 i_Prelgjahr=new Numeric4();
		Date10 i_Reqexpdat=new Date10();
		Char1 i_Reqind=new Char1();
		Char20 i_Stat_descr=new Char20();
		Char2 i_Status=new Char2();
		Char10 i_Subdeptcode=new Char10();
		Char40 i_Subdeptxt=new Char40();
		Char20 i_Tcode=new Char20();
		Char12 i_Userweb=new Char12();
		Cuky5 i_Waers=new Cuky5();
		Date10 i_Wwert=new Date10();
		
		i_admin.setChar12(headerDataDto.getAdmin());
		i_aedat.setDate10(headerDataDto.getAedat());
		i_apprdate.setDate10(headerDataDto.getApprdate());
		i_approver.setChar12(headerDataDto.getApprover());
		i_bldat.setDate10(headerDataDto.getBldat());
		i_budat.setDate10(headerDataDto.getBudat());
		i_bukrs.setChar4(headerDataDto.getBukrs());
		i_buktx.setChar25(headerDataDto.getBuktx());
		i_deptcode.setChar10(headerDataDto.getDeptcode());
		i_deptkostl.setChar10(headerDataDto.getDeptkostl());
		i_deptxt.setChar40(headerDataDto.getDeptxt());
		i_erdat.setDate10(headerDataDto.getErdat());
		i_ernam.setChar12(headerDataDto.getErnam());
		i_erzet.setTime(new org.apache.axis2.databinding.types.Time(headerDataDto.getErzet()));
		i_expensedoc.setChar4(headerDataDto.getExpensedoc());
		i_expensedocdes.setChar40(headerDataDto.getExpensedocdes());
		i_expenseid.setNumeric10(headerDataDto.getExpenseid());
		i_expind.setChar1(headerDataDto.getExpind());
		i_Kursf.setDecimal95(new BigDecimal(headerDataDto.getKursf()));
		i_Lifnam.setChar35(headerDataDto.getLifnam());
		i_Lifnr.setChar10(headerDataDto.getLifnr());
		i_Netamtexp.setCurr132(new BigDecimal(headerDataDto.getNetamtexp()));
		i_Netamtreq.setCurr132(new BigDecimal(headerDataDto.getNetamtreq()));
		i_Postdate.setDate10(headerDataDto.getPostdate());
		i_Postedby.setChar12(headerDataDto.getPostedby());
		i_Preldoc.setChar10(headerDataDto.getPreldoc());
		i_Prelgjahr.setNumeric4(headerDataDto.getPostedby());
		i_Reqexpdat.setDate10(headerDataDto.getReqexpdat());
		i_Reqind.setChar1(headerDataDto.getReqind());
		i_Stat_descr.setChar20(headerDataDto.getStat_descr());
		i_Status.setChar2(headerDataDto.getStatus());
		i_Subdeptcode.setChar10(headerDataDto.getSubdeptcode());
		i_Subdeptxt.setChar40(headerDataDto.getSubdeptxt());
		i_Tcode.setChar20(headerDataDto.getTcode());
		i_Userweb.setChar12(headerDataDto.getUserweb());
		i_Waers.setCuky5(headerDataDto.getWaers());
		i_Wwert.setDate10(headerDataDto.getWwert());
		
		y10StrHeader.setADMIN(i_admin);
		y10StrHeader.setAEDAT(i_aedat);
		y10StrHeader.setAPPRDATE(i_apprdate);
		y10StrHeader.setAPPROVER(i_approver);
		y10StrHeader.setBLDAT(i_bldat);
		y10StrHeader.setBUDAT(i_budat);
		y10StrHeader.setBUKRS(i_bukrs);
		y10StrHeader.setBUKTX(i_buktx);
		y10StrHeader.setDEPTCODE(i_deptcode);
		y10StrHeader.setDEPTKOSTL(i_deptkostl);
		y10StrHeader.setDEPTXT(i_deptxt);
		y10StrHeader.setERDAT(i_erdat);
		y10StrHeader.setERNAM(i_ernam);
		y10StrHeader.setERZET(i_erzet);
		y10StrHeader.setEXPENSEDOC(i_expensedoc);
		y10StrHeader.setEXPENSEDOCDES(i_expensedocdes);
		y10StrHeader.setEXPENSEID(i_expenseid);
		y10StrHeader.setEXPIND(i_expind);
		y10StrHeader.setKURSF(i_Kursf);
		y10StrHeader.setLIFNAM(i_Lifnam);
		y10StrHeader.setLIFNR(i_Lifnr);
		y10StrHeader.setNETAMTEXP(i_Netamtexp);
		y10StrHeader.setNETAMTREQ(i_Netamtreq);
		y10StrHeader.setPOSTDATE(i_Postdate);
		y10StrHeader.setPOSTEDBY(i_Postedby);
		y10StrHeader.setPRELDOC(i_Preldoc);
		y10StrHeader.setPRELGJAHR(i_Prelgjahr);
		y10StrHeader.setREQEXPDAT(i_Reqexpdat);
		y10StrHeader.setREQIND(i_Reqind);
		y10StrHeader.setSTAT_DESCR(i_Stat_descr);
		y10StrHeader.setSTATUS(i_Status);
		y10StrHeader.setSUBDEPTCODE(i_Subdeptcode);
		y10StrHeader.setSUBDEPTXT(i_Subdeptxt);
		y10StrHeader.setTCODE(i_Tcode);
		y10StrHeader.setUSERWEB(i_Userweb);
		y10StrHeader.setWAERS(i_Waers);
		y10StrHeader.setWWERT(i_Wwert);

		List<ItemDataDto> itemDataDtoList = paymentDetailsDto.getItemDataDtoList();

		for (ItemDataDto item : itemDataDtoList) {
			Y10_STR_ITEM_REQ y10StrItemReq = new Y10_STR_ITEM_REQ(); //Estructura Anticipo Gastos
			
			Char12 i_Admin=new Char12();
			Date10 i_Apprdate=new Date10();
			Char12 i_Approver=new Char12();
			Char10 i_Belnr=new Char10();
			Date10 i_Bldat=new Date10();
			Date10 i_Budat=new Date10();
			Char4 i_Bukrs=new Char4();
			Curr132 i_Dmbtr=new Curr132();
			Date10 i_Erdat=new Date10();
			SimpleDateFormat sdfErzet = new SimpleDateFormat("HH:mm:ss");
			Time i_Erzet =new Time();
			Numeric10 i_Expenseid=new Numeric10();
			Numeric4 i_Gjahr=new Numeric4();
			Decimal95 i_kursf=new Decimal95();
			Char1 i_Mark=new Char1();
			Date10 i_postdate=new Date10();
			Char12 i_postedby=new Char12();
			Numeric4 i_Reqlin=new Numeric4();
			Char2 i_status=new Char2();
			Char10 i_Vbelnr=new Char10();
			Numeric4 i_Vgjahr=new Numeric4();
			Cuky5 i_waers=new Cuky5();
			Curr132 i_wrbtr=new Curr132();
			
			i_Admin.setChar12(item.getAdmin());
			i_Apprdate.setDate10(item.getApprdate());
			i_Approver.setChar12(item.getApprover());
			i_Belnr.setChar10(item.getBelnr());
			i_Bldat.setDate10(item.getBldat());
			i_Budat.setDate10(item.getBudat());
			i_Bukrs.setChar4(item.getBukrs());
			i_Dmbtr.setCurr132(new BigDecimal(item.getDmbtr() == null || item.getDmbtr().length() == 0 ? "0.0" : item.getDmbtr()));
			i_Erdat.setDate10(item.getErdat());
			i_Erzet.setTime(new org.apache.axis2.databinding.types.Time(item.getErzet() != null && item.getErzet().length() > 0 ? item.getErzet() : sdfErzet.format(new Date())));
			i_Expenseid.setNumeric10(item.getExpenseid());
			i_Gjahr.setNumeric4(item.getGjahr());
			i_kursf.setDecimal95(new BigDecimal(item.getKursf() == null || item.getKursf().length() == 0 ? "0.0" : item.getKursf()));
			i_Mark.setChar1(item.getMark());
			i_postdate.setDate10(item.getPostdate());
			i_postedby.setChar12(item.getPostdate());
			i_Reqlin.setNumeric4(item.getReqlin());
			i_status.setChar2(item.getStatus());
			i_Vbelnr.setChar10(item.getVbelnr());
			i_Vgjahr.setNumeric4(item.getVgjahr());
			i_waers.setCuky5(item.getWaers());
			i_wrbtr.setCurr132(new BigDecimal(item.getWrbtr() == null || item.getWrbtr().length() == 0 ? "0.0" : item.getWrbtr()));
			
			y10StrItemReq.setADMIN(i_Admin);
			y10StrItemReq.setAPPRDATE(i_Apprdate);
			y10StrItemReq.setAPPROVER(i_Approver);
			y10StrItemReq.setBELNR(i_Belnr);
			y10StrItemReq.setBLDAT(i_Bldat);
			y10StrItemReq.setBUDAT(i_Budat);
			y10StrItemReq.setBUKRS(i_Bukrs);
			y10StrItemReq.setDMBTR(i_Dmbtr);
			y10StrItemReq.setERDAT(i_Erdat);
			y10StrItemReq.setERZET(i_Erzet);
			y10StrItemReq.setEXPENSEID(i_Expenseid);
			y10StrItemReq.setGJAHR(i_Gjahr);
			y10StrItemReq.setKURSF(i_kursf);
			y10StrItemReq.setMARK(i_Mark);
			y10StrItemReq.setPOSTDATE(i_postdate);
			y10StrItemReq.setPOSTEDBY(i_postedby);
			y10StrItemReq.setREQLIN(i_Reqlin);
			y10StrItemReq.setSTATUS(i_status);
			y10StrItemReq.setVBELNR(i_Vbelnr);
			y10StrItemReq.setVGJAHR(i_Vgjahr);
			y10StrItemReq.setWAERS(i_waers);
			y10StrItemReq.setWRBTR(i_wrbtr);
			
			y10TtItemReq.addItem(y10StrItemReq);
		}

		y10StrWbsRequestData.setHEADER_DATA(y10StrHeader);
		y10StrWbsRequestData.setHEADER_TEXT(y10TtText);
		y10StrWbsRequestData.setITEM_DATA(y10TtItemReq);
		y10SaveRequestData.setEX_REQUEST_DATA(y10StrWbsRequestData);

		Y10_SAVE_REQUEST_DATAResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.y10_SAVE_REQUEST_DATA(y10SaveRequestData);
		} catch (RemoteException e) {
			LOGGER.error("Error with saveRequestData: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error with saveRequestData: ", e);
			return null;
		}
		
		return response;
	}

}
