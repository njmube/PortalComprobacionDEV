package com.wise.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char12;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2;
import functions.rfc.sap.document.sap_com.ZWS_EXPENSE_UTILS_METHODSStub;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECK;
import functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication){
		Y10_PASS_CHECKResponse response = loadFromWS(authentication);
		
		if(response == null) {
//			throw new BadCredentialsException("Usuario inválido");
			throw new BadCredentialsException("Error al Autenticar");
		} else {
			if(response.getES_LOGIN_DATA() != null) {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				List<GrantedAuthority> grantedAuths = new ArrayList();
				grantedAuths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
				Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
				return auth;				
			}else {
				throw new AuthenticationCredentialsNotFoundException(response.getRETURN().getItem().toString().equals("E") ? response.getRETURN().getItem().toString() : "");
			}
		}						
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public Y10_PASS_CHECKResponse loadFromWS(Authentication authentication) {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		try {
			ZWS_EXPENSE_UTILS_METHODSStub stub = new ZWS_EXPENSE_UTILS_METHODSStub();
			Options options = stub._getServiceClient().getOptions();
			HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
			auth.setPreemptiveAuthentication(true);
			auth.setUsername(Constants.WISE_WSDL_USER);
			auth.setPassword(Constants.WISE_WSDL_PASS);
			options.setProperty(HTTPConstants.AUTHENTICATE,auth);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Y10_PASS_CHECK user = new Y10_PASS_CHECK();
			Char12 username = new Char12();
			Char16 passwd = new Char16();
			Lang langu = new Lang();			
			TABLE_OF_BAPIRET2 _return = new TABLE_OF_BAPIRET2();
			
			username.setChar12(authentication.getName());
			passwd.setChar16((String)authentication.getCredentials());
			langu.setLang("");
			
			user.setIV_USERWEB(username);
			user.setIV_PASSWORD(passwd);
			user.setIV_LANGUAGE(langu);
			user.setRETURN(_return);
			Y10_PASS_CHECKResponse response = stub.y10_PASS_CHECK(user);
			
			return response;
			
		} catch (Exception e) {
			LOGGER.error("ERROR AL AUTENTICAR ",e);
			return null;
		}
		
	}
	
}
