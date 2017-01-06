package com.wise.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.dto.AccDataDto;
//import com.wise.dto.CellDto;
import com.wise.dto.CostDistDto;
import com.wise.dto.ExpenseDataDto;
import com.wise.dto.HeaderExpenseDto;
import com.wise.dto.ItemDataExpenseDto;
import com.wise.service.ExpenseService;

import functions.rfc.sap.document.sap_com.BAPIRET2;
import functions.rfc.sap.document.sap_com.Char256;
//import functions.rfc.sap.document.sap_com.LVC_S_STYL;
import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_SAVE_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_EXPENACCO;
import functions.rfc.sap.document.sap_com.Y10_STR_EXPENDIST;
import functions.rfc.sap.document.sap_com.Y10_STR_HEADER;
import functions.rfc.sap.document.sap_com.Y10_STR_ITEM_EXP;
import functions.rfc.sap.document.sap_com.Y10_TT_ITEM_EXP;
import functions.rfc.sap.document.sap_com.Y10_TT_TEXT;

@Controller
@RequestMapping("/expense")
public class ExpenseController extends BaseController {
	
	Logger LOGGER = Logger.getLogger(ExpenseController.class);
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping(value="/getExpenseData.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getExpenseData(HttpServletRequest request, HttpSession session, String bukrs, String expenseid) throws AxisFault {
		LOGGER.info("Getting Expense data");
		ExpenseDataDto expenseDataDto = null;
		try {
			Y10_GET_EXPENSE_DATAResponse response = expenseService.getExpenseData(bukrs, expenseid);
			if(response != null) {
				expenseDataDto = new ExpenseDataDto();
				HeaderExpenseDto headerExpenseDto = new HeaderExpenseDto();
				Y10_STR_HEADER y10StrHeaderData = response.getIM_EXPENSE_DATA().getHEADER_DATA();
				if(y10StrHeaderData != null) {
					headerExpenseDto.setAdmin(y10StrHeaderData.getADMIN().toString());
					headerExpenseDto.setAedat(y10StrHeaderData.getAEDAT().toString());
					headerExpenseDto.setApprdate(y10StrHeaderData.getAPPRDATE().toString());
					headerExpenseDto.setApprover(y10StrHeaderData.getAPPROVER().toString());
					headerExpenseDto.setBldat(y10StrHeaderData.getBLDAT().toString());
					headerExpenseDto.setBudat(y10StrHeaderData.getBUDAT().toString());
					headerExpenseDto.setBukrs(y10StrHeaderData.getBUKRS().toString());
					headerExpenseDto.setBuktx(y10StrHeaderData.getBUKTX().toString());
					headerExpenseDto.setDeptcode(y10StrHeaderData.getDEPTCODE().toString());
					headerExpenseDto.setDeptkostl(y10StrHeaderData.getDEPTKOSTL().toString());
					headerExpenseDto.setDeptxt(y10StrHeaderData.getDEPTXT().toString());
					headerExpenseDto.setErdat(y10StrHeaderData.getERDAT().toString());
					headerExpenseDto.setErnam(y10StrHeaderData.getERNAM().toString());
					headerExpenseDto.setErzet(y10StrHeaderData.getERZET().toString());
					headerExpenseDto.setExpensedoc(y10StrHeaderData.getEXPENSEDOC().toString());
					headerExpenseDto.setExpensedocdes(y10StrHeaderData.getEXPENSEDOCDES().toString());
					headerExpenseDto.setExpenseid(y10StrHeaderData.getEXPENSEID().toString());
					headerExpenseDto.setExpind(y10StrHeaderData.getEXPIND().toString());
					headerExpenseDto.setKursf(y10StrHeaderData.getKURSF().toString());
					headerExpenseDto.setLifnam(y10StrHeaderData.getLIFNAM().toString());
					headerExpenseDto.setLifnr(y10StrHeaderData.getLIFNR().toString());
					headerExpenseDto.setNetamtexp(y10StrHeaderData.getNETAMTEXP().toString());
					headerExpenseDto.setNetamtreq(y10StrHeaderData.getNETAMTREQ().toString());
					headerExpenseDto.setPostdate(y10StrHeaderData.getPOSTDATE().toString());
					headerExpenseDto.setPostedby(y10StrHeaderData.getPOSTEDBY().toString());
					headerExpenseDto.setPreldoc(y10StrHeaderData.getPRELDOC().toString());
					headerExpenseDto.setPrelgjahr(y10StrHeaderData.getPRELGJAHR().toString());
					headerExpenseDto.setReqexpdat(y10StrHeaderData.getREQEXPDAT().toString());
					headerExpenseDto.setReqind(y10StrHeaderData.getREQIND().toString());
					headerExpenseDto.setStat_descr(y10StrHeaderData.getSTAT_DESCR().toString());
					headerExpenseDto.setStatus(y10StrHeaderData.getSTATUS().toString());
					headerExpenseDto.setSubdeptcode(y10StrHeaderData.getSUBDEPTCODE().toString());
					headerExpenseDto.setSubdeptxt(y10StrHeaderData.getSUBDEPTXT().toString());
					headerExpenseDto.setTcode(y10StrHeaderData.getTCODE().toString());
					headerExpenseDto.setUserweb(y10StrHeaderData.getUSERWEB().toString());
					headerExpenseDto.setWaers(y10StrHeaderData.getWAERS().toString());
					headerExpenseDto.setWwert(y10StrHeaderData.getWWERT().toString());					
				}
				expenseDataDto.setHeaderExpenseDto(headerExpenseDto);
				Y10_TT_ITEM_EXP y10TtItemExpResponse = response.getIM_EXPENSE_DATA().getITEM_DATA();
				ItemDataExpenseDto itemDataExpenseDto = null;
				List<ItemDataExpenseDto> list = new ArrayList<ItemDataExpenseDto>();
				if(y10TtItemExpResponse != null && y10TtItemExpResponse.getItem() != null && y10TtItemExpResponse.getItem().length > 0) {
					for(Y10_STR_ITEM_EXP y10StrItemExp : y10TtItemExpResponse.getItem()) {
						if(y10StrItemExp != null) {
							itemDataExpenseDto = new ItemDataExpenseDto();
							for(Y10_STR_EXPENACCO expenacco : y10StrItemExp.getACCDATA().getItem()) {
								AccDataDto accData = new AccDataDto();
								accData.setACCOLIN(expenacco.getACCOLIN().toString());
								accData.setBUKRS(expenacco.getBUKRS().toString());
								accData.setEXPENSEID(expenacco.getEXPENSEID().toString());
								accData.setEXPENSELINE(expenacco.getEXPENSELINE().toString());
								accData.setHKONT(expenacco.getHKONT().toString());
								accData.setMWSKZ(expenacco.getMWSKZ().toString());
								accData.setSGTXT(expenacco.getSGTXT().toString());
								accData.setTXT20(expenacco.getTXT20().toString());
								accData.setWMWST(expenacco.getWMWST().toString());
								accData.setWRBTR(expenacco.getWRBTR().toString());
								itemDataExpenseDto.getACCDATA().add(accData);
							}
							itemDataExpenseDto.setAPPRAMOUNT(y10StrItemExp.getAPPRAMOUNT().toString());
							itemDataExpenseDto.setAVAILABLE_DMBTR(y10StrItemExp.getAVAILABLE_DMBTR().toString());
							itemDataExpenseDto.setBLDAT(y10StrItemExp.getBLDAT().toString());
							itemDataExpenseDto.setBUDGET_DMBTR(y10StrItemExp.getBUDGET_DMBTR().toString());
							itemDataExpenseDto.setBUKRS(y10StrItemExp.getBUKRS().toString());
							itemDataExpenseDto.setCALCTYPEBUS(y10StrItemExp.getCALCTYPEBUS().toString());
							itemDataExpenseDto.setCALCTYPESAT(y10StrItemExp.getCALCTYPESAT().toString());
							/*for(LVC_S_STYL styl : y10StrItemExp.getCELL().getItem()) {
								CellDto cell = new CellDto();
								cell.setFIELDNAME(styl.getFIELDNAME().toString());
								cell.setMAXLEN(Integer.toString(styl.getMAXLEN()));
								cell.setSTYLE(styl.getSTYLE().toString());
								cell.setSTYLE2(styl.getSTYLE2().toString());
								cell.setSTYLE3(styl.getSTYLE3().toString());
								cell.setSTYLE4(styl.getSTYLE4().toString());
								itemDataExpenseDto.getCELL().add(cell);
							}*/
							for(Y10_STR_EXPENDIST expendist : y10StrItemExp.getCOSTDIST().getItem()) {
								CostDistDto costDist = new CostDistDto();
								costDist.setAPPDMBTR(expendist.getAPPDMBTR().toString());
								costDist.setCOST_PERCENT(expendist.getCOST_PERCENT().toString());
								costDist.setDISTLIN(expendist.getDISTLIN().toString());
								costDist.setEXPENSELINE(expendist.getEXPENSELINE().toString());
								costDist.setKOSTL(expendist.getKOSTL().toString());
								costDist.setOPERFLAG(expendist.getOPERFLAG().toString());
								itemDataExpenseDto.getCOSTDIST().add(costDist);
							}
							itemDataExpenseDto.setDEDUAMOUNT(y10StrItemExp.getDEDUAMOUNT().toString());
							itemDataExpenseDto.setDMBTR(y10StrItemExp.getDMBTR().toString());
							itemDataExpenseDto.setEXPENQUAN(y10StrItemExp.getEXPENQUAN().toString());
							itemDataExpenseDto.setEXPENSECLASS(y10StrItemExp.getEXPENSECLASS().toString());
							itemDataExpenseDto.setEXPENSECLASSDES(y10StrItemExp.getEXPENSECLASSDES().toString());
							itemDataExpenseDto.setEXPENSELINE(y10StrItemExp.getEXPENSELINE().toString());
							itemDataExpenseDto.setEXPENSETYPE(y10StrItemExp.getEXPENSETYPE().toString());
							itemDataExpenseDto.setHKONT(y10StrItemExp.getHKONT().toString());
							itemDataExpenseDto.setHWBAS(y10StrItemExp.getHWBAS().toString());
							itemDataExpenseDto.setHWSTE(y10StrItemExp.getHWSTE().toString());
							itemDataExpenseDto.setKOSTL(y10StrItemExp.getKOSTL().toString());
							itemDataExpenseDto.setKURSF(y10StrItemExp.getKURSF().toString());
							itemDataExpenseDto.setLIMIT_BUS(y10StrItemExp.getLIMIT_BUS().toString());
							itemDataExpenseDto.setLIMIT_SAT(y10StrItemExp.getLIMIT_SAT().toString());
							itemDataExpenseDto.setMWSKZ(y10StrItemExp.getMWSKZ().toString());
							itemDataExpenseDto.setMwskz_xml(y10StrItemExp.getMWSKZ_XML().toString());
							itemDataExpenseDto.setOPERFLAG(y10StrItemExp.getOPERFLAG().toString());
							itemDataExpenseDto.setPAYMET(y10StrItemExp.getPAYMET().toString());
							itemDataExpenseDto.setPDFICON(y10StrItemExp.getPDFICON().toString());
							itemDataExpenseDto.setPDFINS(y10StrItemExp.getPDFINS().toString());
							
							InputStream isPDF = y10StrItemExp.getPDFXSTRING().getInputStream();
							byte[] bytesPDF = IOUtils.toByteArray(isPDF);
							byte[] bytesEncodedPDF = Base64.encodeBase64(bytesPDF);
							String encodedStringPDF = new String(bytesEncodedPDF);
							itemDataExpenseDto.setPDFXSTRING(removeTrailingDupes(encodedStringPDF));
							
							itemDataExpenseDto.setPERCENT_BUS(y10StrItemExp.getPERCENT_BUS().toString());
							itemDataExpenseDto.setPERCENT_SAT(y10StrItemExp.getPERCENT_SAT().toString());
							itemDataExpenseDto.setRFC(y10StrItemExp.getRFC().toString());
							itemDataExpenseDto.setRULTYPBUS(y10StrItemExp.getRULTYPBUS().toString());
							itemDataExpenseDto.setRULTYPSAT(y10StrItemExp.getRULTYPSAT().toString());
							itemDataExpenseDto.setUNIT(y10StrItemExp.getUNIT().toString());
							itemDataExpenseDto.setUUID(y10StrItemExp.getUUID().toString());
							itemDataExpenseDto.setWAERS(y10StrItemExp.getWAERS().toString());
							itemDataExpenseDto.setWaers_xml(y10StrItemExp.getWAERS_XML().toString());
							itemDataExpenseDto.setWRBTR(y10StrItemExp.getWRBTR().toString());
							itemDataExpenseDto.setXMLICON(y10StrItemExp.getXMLICON().toString());
							itemDataExpenseDto.setXMLINS(y10StrItemExp.getXMLINS().toString());
							
							InputStream isXML = y10StrItemExp.getXMLXSTRING().getInputStream();
							byte[] bytesXML = IOUtils.toByteArray(isXML);
							byte[] bytesEncodedXML = Base64.encodeBase64(bytesXML);
							String encodedStringXML = new String(bytesEncodedXML);
							itemDataExpenseDto.setXMLXSTRING(removeTrailingDupes(encodedStringXML));
							
							if(y10StrItemExp.getOPERFLAG().toString().equals("1")){
								itemDataExpenseDto.setPdf("Si");
								itemDataExpenseDto.setXml("Si");
							}
							
							if(y10StrItemExp.getOPERFLAG().toString().equals("2")){
								itemDataExpenseDto.setPdf("Si");
								itemDataExpenseDto.setXml("No");
							}
							
							list.add(itemDataExpenseDto);
						}
					}
				} else {
					list.add(new ItemDataExpenseDto());
				}
				expenseDataDto.setItemDataExpenseDtoList(list);
				List<String> headerTextItems = new ArrayList<String>();
				Y10_TT_TEXT y10TtText = response.getIM_EXPENSE_DATA().getHEADER_TEXT();
				if(y10TtText != null && y10TtText.getItem() != null){
					for(Char256 headerTextItem: response.getIM_EXPENSE_DATA().getHEADER_TEXT().getItem()) {
						headerTextItems.add(headerTextItem.toString());
					}
					expenseDataDto.setHeaderTextList(headerTextItems);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error getting expense data:", e);
			return getModelMapError("Error: " + e.getMessage());
		}
		return getResponseMapFromObject(expenseDataDto);
	}

	@RequestMapping(value = "/saveExpenseData.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveExpenseData(@RequestBody ExpenseDataDto dto, HttpServletResponse responseServlet) throws IOException {
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
		wrapper.setContentType("text/html;charset=UTF-8");
		String good = "",bad = "";
		try {
			Y10_SAVE_EXPENSE_DATAResponse response = expenseService.saveExpenseData(dto);
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
			LOGGER.error("Error saving expense data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		} catch (JSONException e) {
			LOGGER.error("Error saving expense data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		}
	}
	
	private String removeTrailingDupes(String s) {
		// Is there a dupe?
	    int l = s.length();
	    if (l > 1 && s.charAt(l - 1) == s.charAt(l - 2)) {
	      // Where to cut.
	      int cut = l - 2;
	      // What to cut.
	      char c = s.charAt(cut);
	      while (cut > 0 && s.charAt(cut - 1) == c) {
	        // Cut that one too.
	        cut -= 1;
	      }
	      // Cut off the repeats.
	      return s.substring(0, cut);
	    }
	    // Return it untouched.
	    return s;
	}

}
