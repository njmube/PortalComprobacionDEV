package com.wise.service;

import java.io.IOException;

import org.apache.axis2.AxisFault;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.dto.PaymentDetailsDto;
import com.wise.dao.RequestDao;

import functions.rfc.sap.document.sap_com.Y10_GET_REQUEST_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_SAVE_REQUEST_DATAResponse;

@Service
public class RequestService {
	
	@Autowired
	RequestDao requestDao;
	
	@Transactional(readOnly = true)
	public Y10_GET_REQUEST_DATAResponse getRequestData(String bukrs, String expenseId) throws AxisFault {
		return requestDao.getRequestData(bukrs, expenseId);
	}
	
	@Transactional(readOnly = false)
	public Y10_SAVE_REQUEST_DATAResponse saveRequestData(PaymentDetailsDto paymentDetailsDto) throws IOException, JSONException {
		return requestDao.saveRequestData(paymentDetailsDto);
	}

}
