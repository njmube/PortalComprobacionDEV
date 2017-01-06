package com.wise.service;

import java.io.IOException;

import org.apache.axis2.AxisFault;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.dao.ExpenseDao;
import com.wise.dto.ExpenseDataDto;

import functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_DATAResponse;
import functions.rfc.sap.document.sap_com.Y10_SAVE_EXPENSE_DATAResponse;

@Service
public class ExpenseService {
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Transactional(readOnly = true)
	public Y10_GET_EXPENSE_DATAResponse getExpenseData(String bukrs, String expenseid) throws AxisFault {
		return expenseDao.getExpenseData(bukrs, expenseid);
	}
	
	@Transactional(readOnly = false)
	public Y10_SAVE_EXPENSE_DATAResponse saveExpenseData(ExpenseDataDto expenseDataDto) throws IOException, JSONException {
		return expenseDao.saveExpenseData(expenseDataDto);
	}

}
