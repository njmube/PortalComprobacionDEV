package com.wise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.wise.dto.FieldsXmlDto;
import com.wise.dto.GastoDto;
import com.wise.dto.ListExpenseDto;
import com.wise.dto.RequestDto;
import com.wise.dto.PaymethdDto;
import com.wise.dto.CecosDto;
import com.wise.dto.ExpDocConfDto;
import com.wise.dto.FullConfigDto;
import com.wise.dto.TaxesDto;
import com.wise.dto.ConfigDto;
import com.wise.dto.AccStatusDto;
import com.wise.model.Cfdi;
import com.wise.model.Comprobante;
import com.wise.service.ProviderService;

import functions.rfc.sap.document.sap_com.BAPIRET2;
import functions.rfc.sap.document.sap_com.Y10_FG_ACC_STATUSResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPENResponse;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_STATEMENTResponse;
import functions.rfc.sap.document.sap_com.Y10_SEARCH_DOCUMENT_IDResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_ACC_STATUS;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_PAYMENT_METHODS;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_CONF_BUKRS;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_CONF_CECOS;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_CONF_DOC_CLASSES;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_CONF_EXPCLASES;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_CONF_TAXES;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_EXP_STATEMENT;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_VENDAT;
import functions.rfc.sap.document.sap_com.Y10_TT_HEADER;
import functions.rfc.sap.document.sap_com.Y10_TT_WBS_EXP_STATEMENT;

@Controller
@RequestMapping("/provider")
public class ProviderController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(ProviderController.class);
	
	@Autowired
	private ProviderService providerService;

	@RequestMapping(value = "/getConfDataForExpen.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getConfDataForExpen(HttpServletRequest request, HttpSession session) throws AxisFault {
		LOGGER.info("Getting Conf Data For Expen");
		String str = getCurrentUser();
		String lifnr = StringUtils.leftPad(str, 10, "0");
		//String lifnr = String.format("%010d", Integer.parseInt(str));
		if(lifnr != null){
			try {
				Y10_GET_CONF_DATA_FOR_EXPENResponse response = providerService.getConfDataForExpen(lifnr);
				FullConfigDto fullConfigDto = new FullConfigDto();
				for(Y10_STR_WBS_CONF_DOC_CLASSES doc : response.getIM_CONF_DATA().getEXPDOC_CONF().getItem()){
					ExpDocConfDto dto = new ExpDocConfDto();
					dto.setExpenseDoc(doc.getEXPENSEDOC().toString());
					dto.setExpenseDocAbre(doc.getEXPENSEDOCABRE().toString());
					dto.setExpenseDocDesc(doc.getEXPENSEDOCDESC().toString());
					fullConfigDto.getExpenseDocConf().add(dto);
				}
				for(Y10_STR_WBS_CONF_BUKRS burk : response.getIM_CONF_DATA().getBUKRS_CONF().getItem()) {
					ConfigDto configDto = new ConfigDto();
					configDto.setBukrs(burk.getBUKRS().toString());
					configDto.setButxt(burk.getBUTXT().toString());
					for(Y10_STR_WBS_CONF_EXPCLASES item : burk.getEXPCLASSES().getItem()) {
						GastoDto gasto = new GastoDto();
						gasto.setExpenseClass(item.getEXPENSECLASS().toString());
						gasto.setExpenseClassDes(item.getEXPENSECLASSDES().toString());
						gasto.setHkont(item.getHKONT().toString());
						gasto.setRultypbus(item.getRULTYPBUS().toString());
						gasto.setRultypsat(item.getRULTYPSAT().toString());
						gasto.setUnit(item.getUNIT().toString());
						configDto.getDocTypes().add(gasto);
					}
					for(Y10_STR_WBS_CONF_TAXES tax : burk.getTAXES().getItem()) {
						TaxesDto taxesDto = new TaxesDto();
						taxesDto.setActive(tax.getACTIVE().toString());
						taxesDto.setKalsm(tax.getKALSM().toString());
						taxesDto.setMwskz(tax.getMWSKZ().toString());
						configDto.getTaxesDto().add(taxesDto);
					}
					for(Y10_STR_WBS_CONF_CECOS cecos : burk.getCOSTCENTERS().getItem()){
						CecosDto cecosDto = new CecosDto();
						cecosDto.setAct_state(cecos.getACT_STATE().toString());
						cecosDto.setCo_area(cecos.getCO_AREA().toString());
						cecosDto.setKostl(cecos.getCOSTCENTER().toString());
						cecosDto.setDescript(cecos.getDESCRIPT().toString());
						cecosDto.setName(cecos.getNAME().toString());
						configDto.getCecosDto().add(cecosDto);
					}
					fullConfigDto.getConfigData().add(configDto);
				}				
				for(Y10_STR_PAYMENT_METHODS paymets : response.getIM_CONF_DATA().getPAYMETHD().getItem()){
					PaymethdDto paymethd = new PaymethdDto();
					paymethd.setDOCCAT(paymets.getDOCCAT().toString());
					paymethd.setPAYMET(paymets.getPAYMET().toString());
					paymethd.setPAYMETDES(paymets.getPAYMETDES().toString());
					paymethd.setUMSKZ(paymets.getUMSKZ().toString());
					fullConfigDto.getPaymethd().add(paymethd);
				}
				Y10_STR_WBS_VENDAT vendorData = response.getIM_CONF_DATA().getVENDOR_DATA(); 
				fullConfigDto.setLifrn(vendorData.getLIFNR().toString());
				fullConfigDto.setDeptcode(vendorData.getDEPTCODE().toString());
				fullConfigDto.setDeptdesc(vendorData.getDEPTDESC().toString());
				fullConfigDto.setName1(vendorData.getNAME1().toString());
				fullConfigDto.setSubdeptcode(vendorData.getSUBDEPTCODE().toString());
				fullConfigDto.setSubdeptdesc(vendorData.getSUBDEPTDESC().toString());
				fullConfigDto.setSubdeptkostl(vendorData.getSUBDEPTKOSTL().toString());
				
				return getResponseMapFromObject(fullConfigDto);
				
			} catch (Exception e) {
				LOGGER.error("Error Getting Conf Data For Expen: " + e.getMessage(), e);
				return getModelMapError(e.getMessage());
			}
			
		} else {
			return getModelMapError("Error de sesión");
		}
		
	}
	
	@RequestMapping(value = "/getExpenseStatement.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getExpenseStatement(HttpServletRequest request, HttpSession session, String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado, String expenseid) throws AxisFault {
		LOGGER.info("Getting Expenses Statement");
		String str = getCurrentUser();
		String lifnr = StringUtils.leftPad(str, 10, "0");
		//String lifnr = String.format("%010d", Integer.parseInt(str));
		if(lifnr != null){
			try {
				List<ListExpenseDto> jsonResponse = new ArrayList<ListExpenseDto>();
				Y10_GET_EXPENSE_STATEMENTResponse response = providerService.getExpenseStatement(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, lifnr, expenseid);
				Y10_TT_WBS_EXP_STATEMENT header = response.getIM_EXP_STATEMENT();
				Y10_STR_WBS_EXP_STATEMENT[] data = header.getItem();
				if(data != null) {
					for(Y10_STR_WBS_EXP_STATEMENT item : data) {
						ListExpenseDto dto = new ListExpenseDto();
						dto.setAppdmbtr(item.getAPPDMBTR().toString());
						dto.setBldat(item.getBLDAT().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setBukrs(item.getBUKRS().toString());
						dto.setDeptcode(item.getDEPTCODE().toString());
						dto.setDmbtr(item.getDMBTR().toString());
						dto.setExpensedoc(item.getEXPENSEDOC().toString());
						dto.setExpenseid(item.getEXPENSEID().toString());
						dto.setLifnam(item.getLIFNAM().toString());
						dto.setLifnr(item.getLIFNR().toString());
						dto.setSaldo(item.getSALDO().toString());
						dto.setStat_descr(item.getSTAT_DESCR().toString());
						dto.setStatus(item.getSTATUS().toString());
						dto.setSubdeptcode(item.getSUBDEPTCODE().toString());
						dto.setWaers(item.getWAERS().toString());
						jsonResponse.add(dto);
					}
					return getResponseMap(jsonResponse);
				} else {
					return getModelMapSuccess("No se encontró información con los criterios seleccionados");
				}
				
			} catch (Exception e) {
				LOGGER.error("Error Getting Expense Statement: " + e.getMessage(), e);
				return getModelMapError(e.getMessage());
			}
			
		} else {
			return getModelMapError("Error de sesión");
		}
	
	}
	
	@RequestMapping(value = "/getFieldsFromXml.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getFieldsFromXml(@RequestParam("file") MultipartFile multipartFile) throws IOException, ADBException, JAXBException {
		LOGGER.info("Getting Fields From Xml");
		String good = "",bad = "";
		FieldsXmlDto fieldsXmlDto;
		Cfdi cfdi=null;
		Comprobante comprobante;
		try {
			cfdi=new Cfdi();
			fieldsXmlDto=new FieldsXmlDto();
			ByteArrayDataSource rawData= new ByteArrayDataSource(multipartFile.getBytes(), bad);
			DataHandler data= new DataHandler(rawData);
			comprobante=providerService.readFieldsFromXml(multipartFile.getBytes());
			//System.out.println("Fecha XML "+comprobante.getFecha());
			//Y10GetFieldsFromXmlResponse response = providerService.getValueFromXml(data);
			
			//fieldsXmlDto.setNetamt(response.getImXmlFields().getNetamt().toString());
			//fieldsXmlDto.setDate(comprobante.getFecha());
			//fieldsXmlDto.setIsh(response.getImXmlFields().getIsh().toString());
			//fieldsXmlDto.setIva(comprobante.getImpuestos().getTraslados().getTraslado()[0].get);
			fieldsXmlDto.setRfc(comprobante.getEmisor().getRfc());
			fieldsXmlDto.setUuid(comprobante.getComplemento().getTimbreFiscalDigital().getUUID());
			fieldsXmlDto.setWRBTR(comprobante.getTotal());
			fieldsXmlDto.setWAERS(comprobante.getMoneda());
			fieldsXmlDto.setMWSKZ(comprobante.getImpuestos().getTraslados().getTraslado()[0].getTasa());
			fieldsXmlDto.setKURSF(comprobante.getTipoCambio());
			fieldsXmlDto.setBLDAT(comprobante.getComplemento().getTimbreFiscalDigital().getFechaTimbrado());
			if(comprobante.getSerie() != null){
				fieldsXmlDto.setNumFact(comprobante.getSerie()+" - "+comprobante.getFolio());				
			} else {
				fieldsXmlDto.setNumFact(comprobante.getFolio());
			}
			
		} catch (Exception e) {
			LOGGER.error("Error Getting Fields From Xml: " + e.getMessage(), e);
			return getModelMapError(e.getMessage());
		}
		return getResponseMapFromObject(fieldsXmlDto);
	}
	
	@RequestMapping(value = "/searchDocumentId.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchDocumentId(HttpServletRequest request, HttpSession session, String cmbRazonSocial, String fechaIni, String fechaFin, String cmbEstado) throws AxisFault {
		LOGGER.info("Getting Requests List");
		String str = getCurrentUser();
		String lifnr = StringUtils.leftPad(str, 10, "0");
		//String lifnr = String.format("%010d", Integer.parseInt(str));
		if(lifnr != null){
			try {
				List<RequestDto> jsonResponse = new ArrayList<RequestDto>();
				Y10_SEARCH_DOCUMENT_IDResponse response = providerService.searchDocumentId(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, lifnr);
				Y10_TT_HEADER header = response.getIM_SEARCH_RESULT();
				Y10_STR_HEADER[] data = header.getItem();
				if(data != null) {
					for(Y10_STR_HEADER item : data) {
						RequestDto dto = new RequestDto();
						dto.setAdmin(item.getADMIN().toString());
						dto.setAedat(item.getAEDAT().toString());
						dto.setApprdate(item.getAPPRDATE().toString());
						dto.setApprover(item.getAPPROVER().toString());
						dto.setBldat(item.getBLDAT().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setBukrs(item.getBUKRS().toString());
						dto.setBuktx(item.getBUKTX().toString());
						dto.setDeptcode(item.getDEPTCODE().toString());
						dto.setDeptkostl(item.getDEPTKOSTL().toString());
						dto.setDeptxt(item.getDEPTXT().toString());
						dto.setErdat(item.getERDAT().toString());
						dto.setErnam(item.getERNAM().toString());
						dto.setErzet(item.getERZET().toString());
						dto.setExpensedoc(item.getEXPENSEDOC().toString());
						dto.setExpensedocdes(item.getEXPENSEDOCDES().toString());
						dto.setExpenseid(item.getEXPENSEID().toString());
						dto.setExpind(item.getEXPIND().toString());
						dto.setKursf(item.getKURSF().toString());
						dto.setLifnam(item.getLIFNAM().toString());
						dto.setLifnr(item.getLIFNR().toString());
						dto.setNetamtexp(item.getNETAMTEXP().toString());
						dto.setNetamtreq(item.getNETAMTREQ().toString());
						dto.setPostdate(item.getPOSTDATE().toString());
						dto.setPostedby(item.getPOSTEDBY().toString());
						dto.setPreldoc(item.getPRELDOC().toString());
						dto.setPrelgjahr(item.getPRELGJAHR().toString());
						dto.setReqexpdat(item.getREQEXPDAT().toString());
						dto.setReqind(item.getREQIND().toString());
						dto.setStatus(item.getSTATUS().toString());
						dto.setStat_descr(item.getSTAT_DESCR().toString());
						dto.setSubdeptcode(item.getSUBDEPTCODE().toString());
						dto.setSubdeptxt(item.getSUBDEPTXT().toString());
						dto.setTcode(item.getTCODE().toString());
						dto.setUserweb(item.getUSERWEB().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setWwert(item.getWWERT().toString());
						jsonResponse.add(dto);
						}
				
					return getResponseMap(jsonResponse);
				} else {
					return getModelMapSuccess("No se encontró información con los criterios seleccionados");
				}
				
			} catch (Exception e) {
				LOGGER.error("Error Getting Requests List: " + e.getMessage(), e);
				return getModelMapError(e.getMessage());
			}
			
		} else {
			return getModelMapError("Error de sesión");
		}
	
	}
	
	@RequestMapping(value = "/getAccStatus.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAccStatus(HttpServletRequest request, HttpSession session, String cmbRazonSocial) {
		LOGGER.info("Getting Account Status");
		String str = getCurrentUser();
		String lifnr = StringUtils.leftPad(str, 10, "0");
		//String lifnr = String.format("%010d", Integer.parseInt(str));
		Locale locale = RequestContextUtils.getLocale(request);
		String bad = "";
		if(lifnr != null) {
			try {
				Y10_FG_ACC_STATUSResponse response = providerService.getAccStatus(lifnr, cmbRazonSocial, locale.getLanguage());				
				if(response.getET_OPENITEMS() == null && response.getET_CLEAREDITEMS() == null){
					BAPIRET2[] items = response.getRETURN().getItem();
					if(items != null && items.length > 0) {
						for(BAPIRET2 item : items) {
							if("E".equals(item.getTYPE().toString().toLowerCase())) {
								bad += item.getMESSAGE().toString().replace("'", "\"");
							}
						}
					}
					return getModelMapError(bad);
				} else {
					Y10_STR_ACC_STATUS[] data = response.getET_OPENITEMS().getItem();
					Y10_STR_ACC_STATUS[] dataCompensada = response.getET_CLEAREDITEMS().getItem();
					
					List<AccStatusDto> abierta = new ArrayList<AccStatusDto>();
					List<AccStatusDto> compensada = new ArrayList<AccStatusDto>();
					if(data != null && data.length > 0)
					for(Y10_STR_ACC_STATUS item : data) {
						AccStatusDto dto = new AccStatusDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setDmbtr(item.getDMBTR().toString());
						dto.setGjahr(item.getGJAHR().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						abierta.add(dto);
					}
					if(dataCompensada != null && dataCompensada.length > 0)
					for(Y10_STR_ACC_STATUS item : dataCompensada) {
						AccStatusDto dto = new AccStatusDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setDmbtr(item.getDMBTR().toString());
						dto.setGjahr(item.getGJAHR().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						compensada.add(dto);
					}
					return getTwoList(abierta, compensada);
				}
				
			} catch (Exception e) {
				LOGGER.error("Error Getting Account Status: " + e.getMessage(), e);
				return getModelMapError(e.getMessage());
			}
			
		} else {
			return getModelMapError("Error de sesión");
		}
	}
	
}
