package com.wise;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class FileHandler {
	
	public static Object unserializeToObject(Object object, String objectContent) throws JAXBException, UnsupportedEncodingException {
		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Unmarshaller u = context.createUnmarshaller();
		// Write to ByteArray
		ByteArrayInputStream bais = new ByteArrayInputStream(objectContent.getBytes("UTF-8"));
		return  u.unmarshal(bais);
	}
}
