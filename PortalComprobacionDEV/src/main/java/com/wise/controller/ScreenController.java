package com.wise.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScreenController extends BaseController {
	
	Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/EstadoCuenta", method = RequestMethod.GET)
	public ModelAndView getEstadoCuenta(HttpSession session) {
		ModelAndView page = new ModelAndView("estadoCuenta");
		return page;
	}
	
	@RequestMapping(value="/inactive", method = RequestMethod.GET)
	public String getInactivePage(){
		return "inactive";
	}
	
	@RequestMapping(value="/CrearSolicitud", method = RequestMethod.GET)
	public ModelAndView getCrearSolicitud(HttpSession session) {
		ModelAndView page = new ModelAndView("crearSolicitud");
		return page;
	}
	
	@RequestMapping(value="/ListadoComprobaciones", method = RequestMethod.GET)
	public ModelAndView getListadoComprobaciones() {
		ModelAndView page = new ModelAndView("listadoComprobaciones");
		return page;
	}
	
	@RequestMapping(value="/ListadoAnticipos", method = RequestMethod.GET)
	public ModelAndView getListadoSolicitudes() {
		ModelAndView page = new ModelAndView("listadoAnticipos");
		return page;
	}

}
