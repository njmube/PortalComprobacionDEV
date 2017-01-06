package com.wise.service;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.dao.TravelDao;

import functions.rfc.sap.document.sap_com.Y10_SAVE_TRAVEL_DATAResponse;

@Service
public class TravelService {
	
	@Autowired
	TravelDao travelDao;
	
	@Transactional(readOnly = false)
	public Y10_SAVE_TRAVEL_DATAResponse saveTravelData(String cmbRazonSocial, String cmbTipoDocumento, String requestDate, String createdDate, String comments, String waers, String deptcode, String subdeptcode, String createdBy, String lifnr) throws IOException, JSONException {
		return travelDao.saveTravelData(cmbRazonSocial, cmbTipoDocumento, requestDate, createdDate, comments, waers, deptcode, subdeptcode, createdBy, lifnr);
	}

}
