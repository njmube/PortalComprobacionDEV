package com.wise.security;

import java.util.ArrayList;
import java.util.List;







import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.wise.Constants;
import com.wise.controller.BaseController;

import functions.rfc.sap.document.sap_com.BAPIRET2;
import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.Y10_STR_GW_LOGIN;
import functions.rfc.sap.document.sap_com.ZWS_EXPENSE_UTILS_METHODSStub;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECK;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public Authentication authenticate(Authentication authentication){
		Y10_PASS_CHECKResponse response = loadFromWS(authentication);
		
		if(response == null) {
//			throw new BadCredentialsException("Usuario inválido");
			throw new BadCredentialsException("Error al Autenticar");
		} else {
			BAPIRET2[] items = response.getRETURN().getItem();
			if(items != null && items.length > 0) {
				for(BAPIRET2 item : items) {
					if("s".equals(item.getTYPE().toString().toLowerCase())) {
						@SuppressWarnings({ "rawtypes", "unchecked" })
						List<GrantedAuthority> grantedAuths = new ArrayList();
						grantedAuths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
						Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
						return auth;	
			        }else {
			        	throw new AuthenticationCredentialsNotFoundException(("e".equals(item.getTYPE().toString().toLowerCase())) ? item.getMESSAGE().toString() : "");
			        }
				}
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Y10_PASS_CHECKResponse loadFromWS(Authentication authentication) {
		
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
			iv_userweb.setChar12(authentication.getName());
			iv_password.setChar16((String)authentication.getCredentials());
			iv_language.setLang("ES");
			user.setIV_USERWEB(iv_userweb);
			user.setIV_PASSWORD(iv_password);
			user.setIV_LANGUAGE(iv_language);
			Y10_PASS_CHECKResponse response = stub.y10_PASS_CHECK(user);
			
			return response;
			
		} catch (Exception e) {
			LOGGER.error("ERROR AL AUTENTICAR ",e);
			return null;
		}
		
	}
	
}
