package com.wise.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Repository;

import com.wise.Constants;
import com.wise.dto.AccDataDto;
import com.wise.dto.CellDto;
import com.wise.dto.CostDistDto;
import com.wise.dto.ExpenseDataDto;
import com.wise.dto.HeaderExpenseDto;
import com.wise.dto.ItemDataExpenseDto;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char20;
import functions.rfc.sap.document.sap_com.Char25;
import functions.rfc.sap.document.sap_com.Char30;
import functions.rfc.sap.document.sap_com.Char35;
import functions.rfc.sap.document.sap_com.Char36;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Char40;
import functions.rfc.sap.document.sap_com.Char50;
import functions.rfc.sap.document.sap_com.Cuky5;
import functions.rfc.sap.document.sap_com.Curr132;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.Decimal52;
import functions.rfc.sap.document.sap_com.Decimal95;
import functions.rfc.sap.document.sap_com.LVC_S_STYL;
import functions.rfc.sap.document.sap_com.LVC_T_STYL;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.Numeric4;
import functions.rfc.sap.document.sap_com.Quantum133;
import functions.rfc.sap.document.sap_com.Time;
import functions.rfc.sap.document.sap_com.Unit3;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_DATA;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_EXPENSE_DATA;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_INSTANCE_TRAVEL_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_EXPENSE_DATA;
import functions.rfc.sap.document.sap_com.Y10_SAVE_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_EXPENACCO;
import functions.rfc.sap.document.sap_com.Y10_STR_EXPENDIST;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER_KEY;
import functions.rfc.sap.document.sap_com.Y10_STR_ITEM_EXP;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_EXPENSE_DATA;
import functions.rfc.sap.document.sap_com.Y10_TT_EXPENACCO;
import functions.rfc.sap.document.sap_com.Y10_TT_EXPENDIST;
import functions.rfc.sap.document.sap_com.Y10_TT_ITEM_EXP;
import functions.rfc.sap.document.sap_com.Y10_TT_TEXT;
import functions.rfc.sap.document.sap_com.ZWS_METHS_FOR_HANDLE_EXPENSESStub;

@Repository
public class ExpenseDao {
	
	public final static Logger LOGGER = Logger.getLogger(ExpenseDao.class);
	
	public Y10_GET_EXPENSE_DATAResponse getExpenseData(String bukrs, String expenseid) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_HANDLE_EXPENSESStub stub = new ZWS_METHS_FOR_HANDLE_EXPENSESStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);

		Y10_INSTANCE_EXPENSE_DATA y10InstanceExpenseData = new Y10_INSTANCE_EXPENSE_DATA(); //Instanciar expense data
		Y10_STR_HEADER_KEY y10StrHeaderKey = new Y10_STR_HEADER_KEY(); //Estructura cabecera comprobaci�n de gastos
		Char4 i_bukrs = new Char4();
		Numeric10 i_expenseId = new Numeric10();
		i_expenseId.setNumeric10(expenseid);
		i_bukrs.setChar4(bukrs);
		y10StrHeaderKey.setBUKRS(i_bukrs);
		y10StrHeaderKey.setEXPENSEID(i_expenseId);
		y10InstanceExpenseData.setEX_EXPENSE_KEY(y10StrHeaderKey);

		Y10_INSTANCE_TRAVEL_DATA y10InstanceTravelData = new Y10_INSTANCE_TRAVEL_DATA(); //Instanciar Datos Viaje
		y10InstanceTravelData.setEX_EXPENSE_KEY(y10StrHeaderKey);

		Y10_GET_EXPENSE_DATA y10GetExpenseData = new Y10_GET_EXPENSE_DATA(); //Obtener datos de una comprobaci�n
		y10GetExpenseData.setEX_EXPENSE_KEY(y10StrHeaderKey);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_INSTANCE_EXPENSE_DATAResponse response = stub.y10_INSTANCE_EXPENSE_DATA(y10InstanceExpenseData);
			LOGGER.info("y10_INSTANCE_EXPENSE_DATA data: " + response);
			Y10_GET_EXPENSE_DATAResponse resp = stub.y10_GET_EXPENSE_DATA(y10GetExpenseData);
			LOGGER.info("y10_GET_EXPENSE_DATA data: " + resp);
			return resp;
		} catch (Exception e) {
			LOGGER.error("Error Getting Expense Data: ", e);
			return null;
		}
	}
	
	public Y10_SAVE_EXPENSE_DATAResponse saveExpenseData(ExpenseDataDto expenseData) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_METHS_FOR_HANDLE_EXPENSESStub stub = new ZWS_METHS_FOR_HANDLE_EXPENSESStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		Y10_SAVE_EXPENSE_DATA y10SaveExpenseData = new Y10_SAVE_EXPENSE_DATA(); //Guardar datos comprobaci�n datos
		Y10_STR_WBS_EXPENSE_DATA y10StrWbsExpenseData = new Y10_STR_WBS_EXPENSE_DATA(); //Estructura web service comprobaci�n gastos
		
		Y10_STR_HEADER y10StrHeader = new Y10_STR_HEADER(); //Estructura cabecera comprobaci�n de gastos
		Y10_TT_ITEM_EXP y10TtItemExp = new Y10_TT_ITEM_EXP(); //Tipo Tabla Detalle Comprobaci�n Gastos
		Y10_TT_TEXT y10TtText = new Y10_TT_TEXT(); //Tipo Tabla Texto
		
		Y10_TT_EXPENACCO y10TtExpenacco = new Y10_TT_EXPENACCO(); //Tipo Tabla Datos Contables
		Y10_TT_EXPENDIST y10TtExpendist = new Y10_TT_EXPENDIST(); //Tipo Tabla Distribuci�n Costos
		LVC_T_STYL lvcTStyl = new LVC_T_STYL(); //Control LVA: Tabla de estilos para celdas
		
		HeaderExpenseDto headerExpenseDto = expenseData.getHeaderExpenseDto();
		
		Char12 i_admin = new Char12();
		Date10 i_aedat = new Date10();
		Date10 i_apprdate = new Date10();
		Char12 i_approver = new Char12();
		Date10 i_bldat = new Date10();
		Date10 i_budat = new Date10();
		Char4 i_bukrs = new Char4();
		Char25 i_buktx = new Char25();
		Char10 i_deptcode = new Char10();
		Char10 i_deptkostl = new Char10();
		Char40 i_deptxt = new Char40();
		Date10 i_erdat = new Date10();
		Char12 i_ernam = new Char12();
		Time i_erzet = new Time();
		Char4 i_expensedoc = new Char4();
		Char40 i_expensedocdes = new Char40();
		Numeric10 i_expenseid = new Numeric10();
		Char1 i_expind = new Char1();
		Decimal95 i_Kursf = new Decimal95();
		Char35 i_Lifnam = new Char35();
		Char10 i_Lifnr = new Char10();
		Curr132 i_Netamtexp = new Curr132();
		Curr132 i_Netamtreq = new Curr132();
		Date10 i_Postdate = new Date10();
		Char12 i_Postedby = new Char12();
		Char10 i_Preldoc = new Char10();
		Numeric4 i_Prelgjahr = new Numeric4();
		Date10 i_Reqexpdat = new Date10();
		Char1 i_Reqind = new Char1();
		Char20 i_Stat_descr = new Char20();
		Char2 i_Status = new Char2();
		Char10 i_Subdeptcode = new Char10();
		Char40 i_Subdeptxt = new Char40();
		Char20 i_Tcode = new Char20();
		Char12 i_Userweb = new Char12();
		Cuky5 i_Waers = new Cuky5();
		Date10 i_Wwert = new Date10();

		i_admin.setChar12(headerExpenseDto.getAdmin());
		i_aedat.setDate10(headerExpenseDto.getAedat());
		i_apprdate.setDate10(headerExpenseDto.getApprdate());
		i_approver.setChar12(headerExpenseDto.getApprover());
		i_bldat.setDate10(headerExpenseDto.getBldat());
		i_budat.setDate10(headerExpenseDto.getBudat());
		i_bukrs.setChar4(headerExpenseDto.getBukrs());
		i_buktx.setChar25(headerExpenseDto.getBuktx());
		i_deptcode.setChar10(headerExpenseDto.getDeptcode());
		i_deptkostl.setChar10(headerExpenseDto.getDeptkostl());
		i_deptxt.setChar40(headerExpenseDto.getDeptxt());
		i_erdat.setDate10(headerExpenseDto.getErdat());
		i_ernam.setChar12(headerExpenseDto.getErnam());
		i_erzet.setTime(new org.apache.axis2.databinding.types.Time(headerExpenseDto.getErzet()));
		i_expensedoc.setChar4(headerExpenseDto.getExpensedoc());
		i_expensedocdes.setChar40(headerExpenseDto.getExpensedocdes());
		i_expenseid.setNumeric10(headerExpenseDto.getExpenseid());
		i_expind.setChar1(headerExpenseDto.getExpind());
		i_Kursf.setDecimal95(new BigDecimal(headerExpenseDto.getKursf()));
		i_Lifnam.setChar35(headerExpenseDto.getLifnam());
		i_Lifnr.setChar10(headerExpenseDto.getLifnr());
		i_Netamtexp.setCurr132(new BigDecimal(headerExpenseDto.getNetamtexp()));
		i_Netamtreq.setCurr132(new BigDecimal(headerExpenseDto.getNetamtreq()));
		i_Postdate.setDate10(headerExpenseDto.getPostdate());
		i_Postedby.setChar12(headerExpenseDto.getPostedby());
		i_Preldoc.setChar10(headerExpenseDto.getPreldoc());
		i_Prelgjahr.setNumeric4(headerExpenseDto.getPrelgjahr());
		i_Reqexpdat.setDate10(headerExpenseDto.getReqexpdat());
		i_Reqind.setChar1(headerExpenseDto.getReqind());
		i_Stat_descr.setChar20(headerExpenseDto.getStat_descr());
		i_Status.setChar2(headerExpenseDto.getStatus());
		i_Subdeptcode.setChar10(headerExpenseDto.getSubdeptcode());
		i_Subdeptxt.setChar40(headerExpenseDto.getSubdeptxt());
		i_Tcode.setChar20(headerExpenseDto.getTcode());
		i_Userweb.setChar12(headerExpenseDto.getUserweb());
		i_Waers.setCuky5(headerExpenseDto.getWaers());
		i_Wwert.setDate10(headerExpenseDto.getWwert());

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

		List<ItemDataExpenseDto> itemDataExpenseDtoList = expenseData.getItemDataExpenseDtoList();

		for (ItemDataExpenseDto item : itemDataExpenseDtoList) {
			Y10_STR_ITEM_EXP y10StrItemExp = new Y10_STR_ITEM_EXP(); //Detalle comprobaci�n de gastos
			
			Curr132 i_Appramount=new Curr132();
			Curr132 i_Available_dmbtr=new Curr132();
			Date10 i_Bldat=new Date10();
			Curr132 i_Budget_dmbtr=new Curr132();
			Char4 i_Bukrs=new Char4();
			Char1 i_Calctypebus=new Char1();
			Char1 i_Calctypesat=new Char1();
			Curr132 i_Deduamount=new Curr132();
			Curr132 i_Dmbtr=new Curr132();
			Quantum133 i_Expenquan=new Quantum133();
			Char4 i_Expenseclass=new Char4();
			Char50 i_Expenseclassdes=new Char50();
			Numeric4 i_Expenseline=new Numeric4();
			Numeric4 i_Expensetype=new Numeric4();
			Char10 i_Hkont=new Char10();
			Curr132 i_Hwbas=new Curr132();
			Curr132 i_Hwste=new Curr132();
			Char10 i_Kostl=new Char10();
			Decimal95 i_kursf=new Decimal95();
			Curr132 i_Limit_bus=new Curr132();
			Curr132 i_Limit_sat=new Curr132();
			Char2 i_Mwskz=new Char2();
			functions.rfc.sap.document.sap_com.String i_Mwskz_xml=new functions.rfc.sap.document.sap_com.String();
			Char1 i_Operflag=new Char1();
			Char2 i_Paymet=new Char2();
			Char4 i_Pdficon=new Char4();
			Char1 i_Pdfins=new Char1();
			
			String pdfxstring = item.getPDFXSTRING();
			if(pdfxstring == null || pdfxstring.length() == 0){
				pdfxstring = ".";
			}
			
			String xmlxstring = item.getXMLXSTRING();
			if(xmlxstring == null || xmlxstring.length() == 0){
				xmlxstring = ".";
			}

			byte[] valueDecodedPDF= Base64.decodeBase64(pdfxstring.getBytes());
			DataSource dsPDF = new ByteArrayDataSource(valueDecodedPDF, "application/pdf");
			DataHandler i_Pdfxstring = new DataHandler(dsPDF);
			
			byte[] valueDecodedXML= Base64.decodeBase64(xmlxstring.getBytes());
			DataSource dsXML = new ByteArrayDataSource(valueDecodedXML, "text/xml");
			DataHandler i_Xmlxstring = new DataHandler(dsXML);
			
			Decimal52 i_Percent_bus=new Decimal52();
			Decimal52 i_Percent_sat=new Decimal52();
			Char16 i_Rfc=new Char16();
			Char2 i_Rultypbus=new Char2();
			Char2 i_Rultypsat=new Char2();
			Unit3 i_Unit=new Unit3();
			Char36 i_Uuid=new Char36();
			//Cuky5 i_Waers=new Cuky5();
			functions.rfc.sap.document.sap_com.String i_Waers_xml=new functions.rfc.sap.document.sap_com.String();
			Curr132 i_Wrbtr=new Curr132();
			Char4 i_Xmlicon=new Char4();
			Char1 i_Xmlins=new Char1();
			
			i_Appramount.setCurr132(new BigDecimal(item.getAPPRAMOUNT() == null || item.getAPPRAMOUNT().length() == 0 ? "0.0" : item.getAPPRAMOUNT()));
			i_Available_dmbtr.setCurr132(new BigDecimal(item.getAVAILABLE_DMBTR() == null || item.getAVAILABLE_DMBTR().length() == 0 ? "0.0" : item.getAVAILABLE_DMBTR()));
			i_Bldat.setDate10(item.getBLDAT());
			i_Budget_dmbtr.setCurr132(new BigDecimal(item.getBUDGET_DMBTR() == null || item.getBUDGET_DMBTR().length() == 0 ? "0.0" : item.getBUDGET_DMBTR()));
			i_Bukrs.setChar4(item.getBUKRS());
			i_Calctypebus.setChar1(item.getCALCTYPEBUS() == null || item.getCALCTYPEBUS().length() == 0 ? "" : item.getCALCTYPEBUS());
			i_Calctypesat.setChar1(item.getCALCTYPESAT() == null || item.getCALCTYPESAT().length() == 0 ? "" : item.getCALCTYPESAT());
			i_Deduamount.setCurr132(new BigDecimal(item.getDEDUAMOUNT() == null || item.getDEDUAMOUNT().length() == 0 ? "0.0" : item.getDEDUAMOUNT()));
			i_Dmbtr.setCurr132(new BigDecimal(item.getDMBTR() == null || item.getDMBTR().length() == 0 ? "0.0" : item.getDMBTR()));
			i_Expenquan.setQuantum133(new BigDecimal(item.getEXPENQUAN() == null || item.getEXPENQUAN().length() == 0 ? "0.0" : item.getEXPENQUAN()));
			i_Expenseclass.setChar4(item.getEXPENSECLASS());
			i_Expenseclassdes.setChar50(item.getEXPENSECLASSDES());
			i_Expenseline.setNumeric4(item.getEXPENSELINE());
			i_Expensetype.setNumeric4(item.getEXPENSETYPE());
			i_Hkont.setChar10(item.getHKONT() == null || item.getHKONT().length() == 0 ? "" : item.getHKONT());
			i_Hwbas.setCurr132(new BigDecimal(item.getHWBAS() == null || item.getHWBAS().length() == 0 ? "0.0" : item.getHWBAS()));
			i_Hwste.setCurr132(new BigDecimal(item.getHWSTE() == null || item.getHWSTE().length() == 0 ? "0.0" : item.getHWSTE()));
			i_Kostl.setChar10(item.getKOSTL());
			i_kursf.setDecimal95(new BigDecimal(item.getKURSF() == null || item.getKURSF().length() == 0 ? "0.0" : item.getKURSF()));
			i_Limit_bus.setCurr132(new BigDecimal(item.getLIMIT_BUS() == null || item.getLIMIT_BUS().length() == 0 ? "0.0" : item.getLIMIT_BUS()));
			i_Limit_sat.setCurr132(new BigDecimal(item.getLIMIT_SAT() == null || item.getLIMIT_SAT().length() == 0 ? "0.0" : item.getLIMIT_SAT()));
			i_Mwskz.setChar2(item.getMWSKZ() == null || item.getMWSKZ().length() == 0 ? "" : item.getMWSKZ());
			i_Mwskz_xml.setString(item.getMwskz_xml());
			i_Operflag.setChar1(item.getOPERFLAG());
			i_Paymet.setChar2(item.getPAYMET());
			i_Pdficon.setChar4(item.getPDFICON() == null || item.getPDFICON().length() == 0 ? "" : item.getPDFICON());
			i_Pdfins.setChar1(item.getPDFINS() == null || item.getPDFINS().length() == 0 ? "" : item.getPDFINS());
			i_Percent_bus.setDecimal52(new BigDecimal(item.getPERCENT_BUS() == null || item.getPERCENT_BUS().length() == 0 ? "0.0" : item.getPERCENT_BUS()));
			i_Percent_sat.setDecimal52(new BigDecimal(item.getPERCENT_SAT() == null || item.getPERCENT_SAT().length() == 0 ? "0.0" : item.getPERCENT_SAT()));
			i_Rfc.setChar16(item.getRFC());
			i_Rultypbus.setChar2(item.getRULTYPBUS() == null || item.getRULTYPBUS().length() == 0 ? "" : item.getRULTYPBUS());
			i_Rultypsat.setChar2(item.getRULTYPSAT() == null || item.getRULTYPSAT().length() == 0 ? "" : item.getRULTYPSAT());
			i_Unit.setUnit3(item.getUNIT());
			i_Uuid.setChar36(item.getUUID());
			i_Waers.setCuky5(item.getWAERS() == null || item.getWAERS().length() == 0 ? "" : item.getWAERS());
			i_Waers_xml.setString(item.getWaers_xml() == null || item.getWaers_xml().length() == 0 ? "" : item.getWaers_xml());
			i_Wrbtr.setCurr132(new BigDecimal(item.getWRBTR() == null || item.getWRBTR().length() == 0 ? "0.0" : item.getWRBTR()));
			i_Xmlicon.setChar4(item.getXMLICON() == null || item.getXMLICON().length() == 0 ? "" : item.getXMLICON());
			i_Xmlins.setChar1(item.getXMLINS() == null || item.getXMLINS().length() == 0 ? "" : item.getXMLINS());
			
			List<AccDataDto> accDataDtoList = item.getACCDATA();
			for (AccDataDto accData : accDataDtoList){
				Y10_STR_EXPENACCO expenacco = new Y10_STR_EXPENACCO(); //Estructura Datos Contables
				
				Numeric4 i_Accolin = new Numeric4();
				//Char4 i_Bukrs = new Char4();
				Numeric10 i_Expenseid = new Numeric10();
				//Numeric4 i_Expenseline = new Numeric4();
				//Char10 i_Hkont = new Char10();
				//Char2 i_Mwskz = new Char2();
				Char50 i_Sgtxt = new Char50();
				Char20 i_Txt20 = new Char20();
				Curr132 i_Wmwst = new Curr132();
				//Curr132 i_Wrbtr = new Curr132();

				i_Accolin.setNumeric4(accData.getACCOLIN()== null || accData.getACCOLIN().length() == 0 ? "" : accData.getACCOLIN());
				i_Bukrs.setChar4(accData.getBUKRS()== null || accData.getBUKRS().length() == 0 ? "" : accData.getBUKRS());
				i_Expenseid.setNumeric10(accData.getEXPENSEID()== null || accData.getEXPENSEID().length() == 0 ? "" : accData.getEXPENSEID());
				//i_Expenseline.setNumeric4(accData.getEXPENSELINE()== null || accData.getEXPENSELINE().length() == 0 ? "" : accData.getEXPENSELINE());
				i_Expenseline.setNumeric4(item.getEXPENSELINE());
				i_Hkont.setChar10(accData.getHKONT()== null || accData.getHKONT().length() == 0 ? "" : accData.getHKONT());
				i_Mwskz.setChar2(accData.getMWSKZ()== null || accData.getMWSKZ().length() == 0 ? "" : accData.getMWSKZ());
				i_Sgtxt.setChar50(accData.getSGTXT()== null || accData.getSGTXT().length() == 0 ? "" : accData.getSGTXT());
				i_Txt20.setChar20(accData.getTXT20() == null || accData.getTXT20().length() == 0 ? "" : accData.getTXT20());
				i_Wmwst.setCurr132(new BigDecimal(accData.getWMWST() == null || accData.getWMWST().length() == 0 ? "0.0" : accData.getWMWST()));
				i_Wrbtr.setCurr132(new BigDecimal(accData.getWRBTR() == null || accData.getWRBTR().length() == 0 ? "0.0" : accData.getWRBTR()));
				
				expenacco.setACCOLIN(i_Accolin);
				expenacco.setBUKRS(i_Bukrs);
				expenacco.setEXPENSEID(i_Expenseid);
				expenacco.setEXPENSELINE(i_Expenseline);
				expenacco.setHKONT(i_Hkont);
				expenacco.setMWSKZ(i_Mwskz);
				expenacco.setSGTXT(i_Sgtxt);
				expenacco.setTXT20(i_Txt20);
				expenacco.setWMWST(i_Wmwst);
				expenacco.setWRBTR(i_Wrbtr);
				
				y10TtExpenacco.addItem(expenacco);
			}
			
			List<CostDistDto> costDistDtoList = item.getCOSTDIST();
			for (CostDistDto costDist :  costDistDtoList){
				Y10_STR_EXPENDIST expendist = new Y10_STR_EXPENDIST(); //Estructura Distribuci�n Costos
				
				Curr132 i_Appdmbtr=new Curr132();
				Decimal52 i_Cost_percent=new Decimal52();
				Numeric4 i_Distlin=new Numeric4();
				//Numeric4 i_Expenseline=new Numeric4();
				//Char10 i_Kostl=new Char10();
				//Char1 i_Operflag=new Char1();
				
				i_Appdmbtr.setCurr132(new BigDecimal(costDist.getAPPDMBTR()== null || costDist.getAPPDMBTR().length() == 0 ? "0.0" : costDist.getAPPDMBTR()));
				i_Cost_percent.setDecimal52(new BigDecimal(costDist.getCOST_PERCENT()== null || costDist.getCOST_PERCENT().length() == 0 ? "0.0" : costDist.getCOST_PERCENT()));
				i_Distlin.setNumeric4(costDist.getDISTLIN()== null || costDist.getDISTLIN().length() == 0 ? "0" : costDist.getDISTLIN());
				//i_Expenseline.setNumeric4(costDist.getEXPENSELINE()== null || costDist.getEXPENSELINE().length() == 0 ? "0" : costDist.getEXPENSELINE());
				i_Expenseline.setNumeric4(item.getEXPENSELINE());
				i_Kostl.setChar10(costDist.getKOSTL()== null || costDist.getKOSTL().length() == 0 ? "" : costDist.getKOSTL());
				i_Operflag.setChar1(costDist.getOPERFLAG()== null || costDist.getOPERFLAG().length() == 0 ? "" : costDist.getOPERFLAG());
				
				expendist.setAPPDMBTR(i_Appdmbtr);
				expendist.setCOST_PERCENT(i_Cost_percent);
				expendist.setDISTLIN(i_Distlin);
				expendist.setEXPENSELINE(i_Expenseline);
				expendist.setKOSTL(i_Kostl);
				expendist.setOPERFLAG(i_Operflag);
				
				y10TtExpendist.addItem(expendist);
			}
			
			List<CellDto> cellDtoList = item.getCELL();
			for (CellDto cellDto :  cellDtoList){
				LVC_S_STYL lvcSStyl = new LVC_S_STYL(); //Control LVA: Nombre campo + estilos
				
				Char30 i_Fieldname=new Char30();
				functions.rfc.sap.document.sap_com.Byte4 i_style=new functions.rfc.sap.document.sap_com.Byte4();
				functions.rfc.sap.document.sap_com.Byte4 i_style2=new functions.rfc.sap.document.sap_com.Byte4();
				functions.rfc.sap.document.sap_com.Byte4 i_style3=new functions.rfc.sap.document.sap_com.Byte4();
				functions.rfc.sap.document.sap_com.Byte4 i_style4=new functions.rfc.sap.document.sap_com.Byte4();
				
				i_Fieldname.setChar30(cellDto.getFIELDNAME());
				i_style.setByte4(new DataHandler(cellDto.getSTYLE() == null || cellDto.getSTYLE().length() == 0 ? "" : cellDto.getSTYLE(), null));
				i_style2.setByte4(new DataHandler(cellDto.getSTYLE2() == null || cellDto.getSTYLE2().length() == 0 ? "" : cellDto.getSTYLE2(), null));
				i_style3.setByte4(new DataHandler(cellDto.getSTYLE3() == null || cellDto.getSTYLE3().length() == 0 ? "" : cellDto.getSTYLE3(), null));
				i_style4.setByte4(new DataHandler(cellDto.getSTYLE4() == null || cellDto.getSTYLE4().length() == 0 ? "" : cellDto.getSTYLE4(), null));
				
				lvcSStyl.setFIELDNAME(i_Fieldname);
				lvcSStyl.setSTYLE(i_style);
				lvcSStyl.setSTYLE2(i_style2);
				lvcSStyl.setSTYLE3(i_style3);
				lvcSStyl.setSTYLE4(i_style4);
				
				lvcTStyl.addItem(lvcSStyl);
			}
			
			y10StrItemExp.setACCDATA(y10TtExpenacco);
			y10StrItemExp.setAPPRAMOUNT(i_Appramount);
			y10StrItemExp.setAVAILABLE_DMBTR(i_Available_dmbtr);
			y10StrItemExp.setBLDAT(i_Bldat);
			y10StrItemExp.setBUDGET_DMBTR(i_Budget_dmbtr);
			y10StrItemExp.setBUKRS(i_Bukrs);
			y10StrItemExp.setCALCTYPEBUS(i_Calctypebus);
			y10StrItemExp.setCALCTYPESAT(i_Calctypesat);
			y10StrItemExp.setCELL(lvcTStyl);
			y10StrItemExp.setCOSTDIST(y10TtExpendist);
			y10StrItemExp.setDEDUAMOUNT(i_Deduamount);
			y10StrItemExp.setDMBTR(i_Dmbtr);
			y10StrItemExp.setEXPENQUAN(i_Expenquan);
			y10StrItemExp.setEXPENSECLASS(i_Expenseclass);
			y10StrItemExp.setEXPENSECLASSDES(i_Expenseclassdes);
			y10StrItemExp.setEXPENSELINE(i_Expenseline);
			y10StrItemExp.setEXPENSETYPE(i_Expensetype);
			y10StrItemExp.setHKONT(i_Hkont);
			y10StrItemExp.setHWBAS(i_Hwbas);
			y10StrItemExp.setHWSTE(i_Hwste);
			y10StrItemExp.setKOSTL(i_Kostl);
			y10StrItemExp.setKURSF(i_kursf);
			y10StrItemExp.setLIMIT_BUS(i_Limit_bus);
			y10StrItemExp.setLIMIT_SAT(i_Limit_sat);
			y10StrItemExp.setMWSKZ(i_Mwskz);
			y10StrItemExp.setMWSKZ_XML(i_Mwskz_xml);
			y10StrItemExp.setOPERFLAG(i_Operflag);
			y10StrItemExp.setPAYMET(i_Paymet);
			y10StrItemExp.setPDFICON(i_Pdficon);
			y10StrItemExp.setPDFINS(i_Pdfins);
			y10StrItemExp.setPDFXSTRING(i_Pdfxstring);
			y10StrItemExp.setPERCENT_BUS(i_Percent_bus);
			y10StrItemExp.setPERCENT_SAT(i_Percent_sat);
			y10StrItemExp.setRFC(i_Rfc);
			y10StrItemExp.setRULTYPBUS(i_Rultypbus);
			y10StrItemExp.setRULTYPSAT(i_Rultypsat);
			y10StrItemExp.setUNIT(i_Unit);
			y10StrItemExp.setUUID(i_Uuid);
			y10StrItemExp.setWAERS(i_Waers);
			y10StrItemExp.setWAERS_XML(i_Waers_xml);
			y10StrItemExp.setWRBTR(i_Wrbtr);
			y10StrItemExp.setXMLICON(i_Xmlicon);
			y10StrItemExp.setXMLINS(i_Xmlins);
			y10StrItemExp.setXMLXSTRING(i_Xmlxstring);
			
			y10TtItemExp.addItem(y10StrItemExp);
			
		}

		y10StrWbsExpenseData.setHEADER_DATA(y10StrHeader);
		y10StrWbsExpenseData.setHEADER_TEXT(y10TtText);
		y10StrWbsExpenseData.setITEM_DATA(y10TtItemExp);
		y10SaveExpenseData.setEX_EXPENSE_DATA(y10StrWbsExpenseData);

		Y10_SAVE_EXPENSE_DATAResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.y10_SAVE_EXPENSE_DATA(y10SaveExpenseData);
		} catch (RemoteException e) {
			LOGGER.error("Error with saveExpenseData: ", e);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error with saveExpenseData: ", e);
			return null;
		}
		
		return response;
	}

}
