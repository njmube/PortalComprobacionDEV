package com.wise.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wise.service.TravelService;

import functions.rfc.sap.document.sap_com.BAPIRET2;
import functions.rfc.sap.document.sap_com.Y10_SAVE_TRAVEL_DATAResponse;

@Controller
@RequestMapping("/travel")
public class TravelController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(TravelController.class);
	
	@Autowired
	private TravelService travelService;
	
	@RequestMapping(value="/saveTravelData.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTravelData(HttpSession session, String hdnRazonSocial, String hdnTipoDocumento, String requestDate, String createdDate, String comments, String waers, String deptcode, String subdeptcode, String createdBy, HttpServletResponse responseServlet, HttpServletRequest request) throws IOException {
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
		String good = "",bad = "";
		wrapper.setContentType("text/html;charset=UTF-8");
		String str = getCurrentUser();
		String lifnr = String.format("%010d", Integer.parseInt(str));
		try {
			Y10_SAVE_TRAVEL_DATAResponse response = travelService.saveTravelData(hdnRazonSocial, hdnTipoDocumento, requestDate, createdDate, comments, waers, deptcode, subdeptcode, createdBy, lifnr);
			if(response != null) {
				BAPIRET2[] items = response.getIM_RET_MSG().getItem();
				if(items != null && items.length > 0) {
					for(BAPIRET2 item : items) {
						if("e".equals(item.getTYPE().toString().toLowerCase())) {
							bad += item.getMESSAGE().toString().replace("'", "\"") + "\n";
						} else {
							good += item.getMESSAGE().toString().replace("'", "\"") + "\n";
						}
					}
				}
				return getModelMapSuccess(good + (bad.length() > 0 ? "" + bad : ""));
			} else {
				return getModelMapError("No hubo respuesta del servidor");
			}
		} catch (IOException e) {
			LOGGER.error("Error saving travel data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		} catch (JSONException e) {
			LOGGER.error("Error saving travel data: ", e);
			return getModelMapError("Error:" + e.getMessage());
		}
	}

}
