package com.wise.security;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;





import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.ZWS_EXPENSE_UTILS_METHODSStub;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECK;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse;

public class WSTest {
	
	public static void main(String[] args) throws Exception {
		ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername("E_AMONDONEDO");
		auth.setPassword("amondonedom13");
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Y10_PASS_CHECK user = new Y10_PASS_CHECK();
		Char12 iv_userweb = new Char12();
		Char16 iv_password = new Char16();
		Lang iv_language = new Lang();
		iv_userweb.setChar12("4170");
		iv_password.setChar16("4170");
		iv_language.setLang("ES");
		user.setIV_USERWEB(iv_userweb);
		user.setIV_PASSWORD(iv_password);
		user.setIV_LANGUAGE(iv_language);
		Y10_PASS_CHECKResponse response = stub.y10_PASS_CHECK(user);
		
		//System.out.println("ResponseMSG: " + response.getE_MSG());
		//System.out.println("ResponseLIFNR: " + response.getE_LIFNR());
		//System.out.println("ResponseID: " + response.getE_ID());
		//System.out.println("ResponseType: " + response.getE_TYPE());
	}

}
