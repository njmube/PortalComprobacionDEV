package com.wise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.wise.dao.UserDao;

import functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public Y10_PASS_CHECKResponse getUserFromWS(Authentication auth){
		return userDao.findUserFromWS(auth);
	}
}
