package com.wise.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wise.dto.FullConfigDto;
import com.wise.controller.BaseController;
import com.wise.service.ProviderService;

import functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPENResponse;
import functions.rfc.sap.document.sap_com.Y10_STR_WBS_VENDAT;

@Controller
public class LoginController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ProviderService providerService;
	
	@RequestMapping(value="/Login", method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "login";
    }

	@RequestMapping(value="/Login", method = RequestMethod.GET, params = "error")
    public String loginError(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("error","true");
		return "login";
    }
	
	@RequestMapping(value="/Inicio", method = RequestMethod.GET)
	public String inicio(HttpSession session){		
		try {
			String str = getCurrentUser();
			String lifnr = String.format("%010d", Integer.parseInt(str));
			Y10_GET_CONF_DATA_FOR_EXPENResponse providerData =  providerService.getConfDataForExpen(lifnr);
			FullConfigDto fullConfigDto = new FullConfigDto();
			Y10_STR_WBS_VENDAT vendorData = providerData.getIM_CONF_DATA().getVENDOR_DATA(); 
			fullConfigDto.setLifrn(vendorData.getLIFNR().toString());
			fullConfigDto.setDeptcode(vendorData.getDEPTCODE().toString());
			fullConfigDto.setDeptdesc(vendorData.getDEPTDESC().toString());
			fullConfigDto.setName1(vendorData.getNAME1().toString());
			fullConfigDto.setSubdeptcode(vendorData.getSUBDEPTCODE().toString());
			fullConfigDto.setSubdeptdesc(vendorData.getSUBDEPTDESC().toString());
			fullConfigDto.setSubdeptkostl(vendorData.getSUBDEPTKOSTL().toString());
			session.setAttribute("FullConfigDto", fullConfigDto);
			
		} catch (AxisFault e) {
			LOGGER.error("Error datos proveedor",e);
		}
		return "inicio";
	}
}
