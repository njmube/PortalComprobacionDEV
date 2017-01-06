package com.wise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.dto.PaymentDetailsDto;
import com.wise.dto.HeaderDataDto;
import com.wise.dto.ItemDataDto;
import com.wise.service.RequestService;

import functions.rfc.sap.document.sap_com.BAPIRET2;
import functions.rfc.sap.document.sap_com.Char256;
import functions.rfc.sap.document.sap_com.Y10_GET_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_SAVE_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_ITEM_REQ;
import functions.rfc.sap.document.sap_com.Y10_TT_ITEM_REQ;
import functions.rfc.sap.document.sap_com.Y10_TT_TEXT;

@Controller
@RequestMapping("/request")
public class RequestController extends BaseController {
	
	Logger LOGGER = Logger.getLogger(RequestController.class);
	
	@Autowired
	private RequestService requestService;
	
	@RequestMapping(value="/getRequestData.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRequestData(HttpServletRequest request, HttpSession session, String bukrs, String expenseId) throws AxisFault {
		LOGGER.info("Getting Request data");
		PaymentDetailsDto paymentDetailsDto = null;
		try {
			Y10_GET_REQUEST_DATAResponse response = requestService.getRequestData(bukrs, expenseId);
			if(response != null) {
				paymentDetailsDto = new PaymentDetailsDto();
				HeaderDataDto headerDataDto = new HeaderDataDto();
				Y10_STR_HEADER y10StrHeaderData = response.getIM_REQUEST_DATA().getHEADER_DATA();
				if(y10StrHeaderData != null) {
					headerDataDto.setAdmin(y10StrHeaderData.getADMIN().toString());
					headerDataDto.setAedat(y10StrHeaderData.getAEDAT().toString());
					headerDataDto.setApprdate(y10StrHeaderData.getAPPRDATE().toString());
					headerDataDto.setApprover(y10StrHeaderData.getAPPROVER().toString());
					headerDataDto.setBldat(y10StrHeaderData.getBLDAT().toString());
					headerDataDto.setBudat(y10StrHeaderData.getBUDAT().toString());
					headerDataDto.setBukrs(y10StrHeaderData.getBUKRS().toString());
					headerDataDto.setBuktx(y10StrHeaderData.getBUKTX().toString());
					headerDataDto.setDeptcode(y10StrHeaderData.getDEPTCODE().toString());
					headerDataDto.setDeptkostl(y10StrHeaderData.getDEPTKOSTL().toString());
					headerDataDto.setDeptxt(y10StrHeaderData.getDEPTXT().toString());
					headerDataDto.setErdat(y10StrHeaderData.getERDAT().toString());
					headerDataDto.setErnam(y10StrHeaderData.getERNAM().toString());
					headerDataDto.setErzet(y10StrHeaderData.getERZET().toString());
					headerDataDto.setExpensedoc(y10StrHeaderData.getEXPENSEDOC().toString());
					headerDataDto.setExpensedocdes(y10StrHeaderData.getEXPENSEDOCDES().toString());
					headerDataDto.setExpenseid(y10StrHeaderData.getEXPENSEID().toString());
					headerDataDto.setExpind(y10StrHeaderData.getEXPIND().toString());
					headerDataDto.setKursf(y10StrHeaderData.getKURSF().toString());
					headerDataDto.setLifnam(y10StrHeaderData.getLIFNAM().toString());
					headerDataDto.setLifnr(y10StrHeaderData.getLIFNR().toString());
					headerDataDto.setNetamtexp(y10StrHeaderData.getNETAMTEXP().toString());
					headerDataDto.setNetamtreq(y10StrHeaderData.getNETAMTREQ().toString());
					headerDataDto.setPostdate(y10StrHeaderData.getPOSTDATE().toString());
					headerDataDto.setPostedby(y10StrHeaderData.getPOSTEDBY().toString());
					headerDataDto.setPreldoc(y10StrHeaderData.getPRELDOC().toString());
					headerDataDto.setPrelgjahr(y10StrHeaderData.getPRELGJAHR().toString());
					headerDataDto.setReqexpdat(y10StrHeaderData.getREQEXPDAT().toString());
					headerDataDto.setReqind(y10StrHeaderData.getREQIND().toString());
					headerDataDto.setStat_descr(y10StrHeaderData.getSTAT_DESCR().toString());
					headerDataDto.setStatus(y10StrHeaderData.getSTAT_DESCR().toString());
					headerDataDto.setSubdeptcode(y10StrHeaderData.getSUBDEPTCODE().toString());
					headerDataDto.setSubdeptxt(y10StrHeaderData.getSUBDEPTXT().toString());
					headerDataDto.setTcode(y10StrHeaderData.getTCODE().toString());
					headerDataDto.setUserweb(y10StrHeaderData.getUSERWEB().toString());
					headerDataDto.setWaers(y10StrHeaderData.getWAERS().toString());
					headerDataDto.setWwert(y10StrHeaderData.getWWERT().toString());					
				}
				paymentDetailsDto.setHeaderDataDto(headerDataDto);
				Y10_TT_ITEM_REQ y10TtItemReqResponse = response.getIM_REQUEST_DATA().getITEM_DATA();
				ItemDataDto itemDataDto = null;
				List<ItemDataDto> list = new ArrayList<ItemDataDto>();
				if(y10TtItemReqResponse != null && y10TtItemReqResponse.getItem() != null && y10TtItemReqResponse.getItem().length > 0) {
					for(Y10_STR_ITEM_REQ y10StrItemReq : y10TtItemReqResponse.getItem()) {
						if(y10StrItemReq != null) {
							itemDataDto = new ItemDataDto();
							itemDataDto.setAdmin(y10StrItemReq.getADMIN().toString());
							itemDataDto.setApprdate(y10StrItemReq.getAPPRDATE().toString());
							itemDataDto.setApprover(y10StrItemReq.getAPPROVER().toString());
							itemDataDto.setBelnr(y10StrItemReq.getBELNR().toString());
							itemDataDto.setBldat(y10StrItemReq.getBLDAT().toString());
							itemDataDto.setBudat(y10StrItemReq.getBUDAT().toString());
							itemDataDto.setBukrs(y10StrItemReq.getBUKRS().toString());
							itemDataDto.setDmbtr(y10StrItemReq.getDMBTR().toString());
							itemDataDto.setErdat(y10StrItemReq.getERDAT().toString());
							itemDataDto.setErzet(y10StrItemReq.getERZET().toString());
							itemDataDto.setExpenseid(y10StrItemReq.getEXPENSEID().toString());
							itemDataDto.setGjahr(y10StrItemReq.getGJAHR().toString());
							itemDataDto.setKursf(y10StrItemReq.getKURSF().toString());
							itemDataDto.setMark(y10StrItemReq.getMARK().toString());
							itemDataDto.setPostdate(y10StrItemReq.getPOSTDATE().toString());
							itemDataDto.setPostedby(y10StrItemReq.getPOSTEDBY().toString());
							itemDataDto.setReqlin(y10StrItemReq.getREQLIN().toString());
							itemDataDto.setStatus(y10StrItemReq.getSTATUS().toString());
							itemDataDto.setVbelnr(y10StrItemReq.getVBELNR().toString());
							itemDataDto.setVgjahr(y10StrItemReq.getVGJAHR().toString());
							itemDataDto.setWaers(y10StrItemReq.getWAERS().toString());
							itemDataDto.setWrbtr(y10StrItemReq.getWRBTR().toString());
							list.add(itemDataDto);							
						}
					}
				} else {
					list.add(new ItemDataDto());
				}
				paymentDetailsDto.setItemDataDtoList(list);
				List<String> headerTextItems = new ArrayList<String>();
				Y10_TT_TEXT y10TtText = response.getIM_REQUEST_DATA().getHEADER_TEXT();
				if(y10TtText != null && y10TtText.getItem() != null){
					for(Char256 headerTextItem: response.getIM_REQUEST_DATA().getHEADER_TEXT().getItem()) {
						headerTextItems.add(headerTextItem.toString());
					}
					paymentDetailsDto.setHeaderTextList(headerTextItems);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error getting request data:", e);
			return getModelMapError("Error: " + e.getMessage());
		}
		return getResponseMapFromObject(paymentDetailsDto);
	}

	@RequestMapping(value = "/saveRequestData.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRequestData(@RequestBody PaymentDetailsDto dto, HttpServletResponse responseServlet) throws IOException {
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
		wrapper.setContentType("text/html;charset=UTF-8");
		String good = "",bad = "";
		try {
			Y10_SAVE_REQUEST_DATAResponse response = requestService.saveRequestData(dto);
			if(response != null) {
				BAPIRET2[] items = response.getIM_RET_MSG().getItem();
				if(items != null && items.length > 0) {
					for(BAPIRET2 item : items) {
						if("s".equals(item.getTYPE().toString().toLowerCase())) {
							good += item.getMESSAGE().toString().replace("'", "\"");
						} else {
							bad += item.getMESSAGE().toString().replace("'", "\"");
						}
					}
				}
				return getModelMapSuccess(good + (bad.length() > 0 ? "" + bad : ""));
			} else {
				return getModelMapError("No hubo respuesta del servidor");
			}
		} catch (IOException e) {
			LOGGER.error("Error saving request data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		} catch (JSONException e) {
			LOGGER.error("Error saving request data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		}
	}

}
