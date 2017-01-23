package com.wise.dao;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2;
import functions.rfc.sap.document.sap_com.ZWS_EXPENSE_UTILS_METHODSStub;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECK;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse;

@Repository
public class UserDao {
	
	public final static Logger LOGGER = Logger.getLogger(UserDao.class);
	
	public Y10_PASS_CHECKResponse findUserFromWS(Authentication authentication) {
		try {
			ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
			Options options = stub._getServiceClient().getOptions();
			HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			auth.setUsername(Constants.WISE_WSDL_USER);
			auth.setPassword(Constants.WISE_WSDL_PASS);
			options.setProperty(HTTPConstants.AUTHENTICATE,auth);
			Y10_PASS_CHECK user = new Y10_PASS_CHECK();
			Char12 iv_userweb = new Char12();
			Char16 iv_password = new Char16();
			Lang iv_language = new Lang();
			TABLE_OF_BAPIRET2 _return = new TABLE_OF_BAPIRET2();
			
			iv_userweb.setChar12(authentication.getName());
			iv_password.setChar16((String)authentication.getCredentials());
			iv_language.setLang("");
			
			user.setIV_USERWEB(iv_userweb);
			user.setIV_PASSWORD(iv_password);
			user.setIV_LANGUAGE(iv_language);
			user.setRETURN(_return);
			Y10_PASS_CHECKResponse response = stub.y10_PASS_CHECK(user);
			
			return response;
			
		} catch (Exception e) {
			LOGGER.error("ERROR AL AUTENTICAR ",e);
			return null;
		}
		
	}
}
