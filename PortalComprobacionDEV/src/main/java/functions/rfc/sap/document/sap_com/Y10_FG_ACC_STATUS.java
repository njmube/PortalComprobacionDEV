
/**
 * Y10_FG_ACC_STATUS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Y10_FG_ACC_STATUS bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Y10_FG_ACC_STATUS
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:sap-com:document:sap:rfc:functions",
                "Y10_FG_ACC_STATUS",
                "ns1");

            

                        /**
                        * field for ET_CLEAREDITEMS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS localET_CLEAREDITEMS ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localET_CLEAREDITEMSTracker = false ;

                           public boolean isET_CLEAREDITEMSSpecified(){
                               return localET_CLEAREDITEMSTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS
                           */
                           public  functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS getET_CLEAREDITEMS(){
                               return localET_CLEAREDITEMS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ET_CLEAREDITEMS
                               */
                               public void setET_CLEAREDITEMS(functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS param){
                            localET_CLEAREDITEMSTracker = param != null;
                                   
                                            this.localET_CLEAREDITEMS=param;
                                    

                               }
                            

                        /**
                        * field for ET_OPENITEMS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS localET_OPENITEMS ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localET_OPENITEMSTracker = false ;

                           public boolean isET_OPENITEMSSpecified(){
                               return localET_OPENITEMSTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS
                           */
                           public  functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS getET_OPENITEMS(){
                               return localET_OPENITEMS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ET_OPENITEMS
                               */
                               public void setET_OPENITEMS(functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS param){
                            localET_OPENITEMSTracker = param != null;
                                   
                                            this.localET_OPENITEMS=param;
                                    

                               }
                            

                        /**
                        * field for IV_BUKRS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char4 localIV_BUKRS ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char4
                           */
                           public  functions.rfc.sap.document.sap_com.Char4 getIV_BUKRS(){
                               return localIV_BUKRS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IV_BUKRS
                               */
                               public void setIV_BUKRS(functions.rfc.sap.document.sap_com.Char4 param){
                            
                                            this.localIV_BUKRS=param;
                                    

                               }
                            

                        /**
                        * field for IV_LANGUAGE
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Lang localIV_LANGUAGE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIV_LANGUAGETracker = false ;

                           public boolean isIV_LANGUAGESpecified(){
                               return localIV_LANGUAGETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Lang
                           */
                           public  functions.rfc.sap.document.sap_com.Lang getIV_LANGUAGE(){
                               return localIV_LANGUAGE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IV_LANGUAGE
                               */
                               public void setIV_LANGUAGE(functions.rfc.sap.document.sap_com.Lang param){
                            localIV_LANGUAGETracker = param != null;
                                   
                                            this.localIV_LANGUAGE=param;
                                    

                               }
                            

                        /**
                        * field for IV_LIFNR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localIV_LIFNR ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getIV_LIFNR(){
                               return localIV_LIFNR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IV_LIFNR
                               */
                               public void setIV_LIFNR(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localIV_LIFNR=param;
                                    

                               }
                            

                        /**
                        * field for RETURN
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2 localRETURN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRETURNTracker = false ;

                           public boolean isRETURNSpecified(){
                               return localRETURNTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2
                           */
                           public  functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2 getRETURN(){
                               return localRETURN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RETURN
                               */
                               public void setRETURN(functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2 param){
                            localRETURNTracker = param != null;
                                   
                                            this.localRETURN=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:sap-com:document:sap:rfc:functions");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Y10_FG_ACC_STATUS",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Y10_FG_ACC_STATUS",
                           xmlWriter);
                   }

               
                   }
                if (localET_CLEAREDITEMSTracker){
                                            if (localET_CLEAREDITEMS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ET_CLEAREDITEMS cannot be null!!");
                                            }
                                           localET_CLEAREDITEMS.serialize(new javax.xml.namespace.QName("","ET_CLEAREDITEMS"),
                                               xmlWriter);
                                        } if (localET_OPENITEMSTracker){
                                            if (localET_OPENITEMS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ET_OPENITEMS cannot be null!!");
                                            }
                                           localET_OPENITEMS.serialize(new javax.xml.namespace.QName("","ET_OPENITEMS"),
                                               xmlWriter);
                                        }
                                            if (localIV_BUKRS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IV_BUKRS cannot be null!!");
                                            }
                                           localIV_BUKRS.serialize(new javax.xml.namespace.QName("","IV_BUKRS"),
                                               xmlWriter);
                                         if (localIV_LANGUAGETracker){
                                            if (localIV_LANGUAGE==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IV_LANGUAGE cannot be null!!");
                                            }
                                           localIV_LANGUAGE.serialize(new javax.xml.namespace.QName("","IV_LANGUAGE"),
                                               xmlWriter);
                                        }
                                            if (localIV_LIFNR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IV_LIFNR cannot be null!!");
                                            }
                                           localIV_LIFNR.serialize(new javax.xml.namespace.QName("","IV_LIFNR"),
                                               xmlWriter);
                                         if (localRETURNTracker){
                                            if (localRETURN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("RETURN cannot be null!!");
                                            }
                                           localRETURN.serialize(new javax.xml.namespace.QName("","RETURN"),
                                               xmlWriter);
                                        }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:sap-com:document:sap:rfc:functions")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localET_CLEAREDITEMSTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ET_CLEAREDITEMS"));
                            
                            
                                    if (localET_CLEAREDITEMS==null){
                                         throw new org.apache.axis2.databinding.ADBException("ET_CLEAREDITEMS cannot be null!!");
                                    }
                                    elementList.add(localET_CLEAREDITEMS);
                                } if (localET_OPENITEMSTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ET_OPENITEMS"));
                            
                            
                                    if (localET_OPENITEMS==null){
                                         throw new org.apache.axis2.databinding.ADBException("ET_OPENITEMS cannot be null!!");
                                    }
                                    elementList.add(localET_OPENITEMS);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "IV_BUKRS"));
                            
                            
                                    if (localIV_BUKRS==null){
                                         throw new org.apache.axis2.databinding.ADBException("IV_BUKRS cannot be null!!");
                                    }
                                    elementList.add(localIV_BUKRS);
                                 if (localIV_LANGUAGETracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "IV_LANGUAGE"));
                            
                            
                                    if (localIV_LANGUAGE==null){
                                         throw new org.apache.axis2.databinding.ADBException("IV_LANGUAGE cannot be null!!");
                                    }
                                    elementList.add(localIV_LANGUAGE);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "IV_LIFNR"));
                            
                            
                                    if (localIV_LIFNR==null){
                                         throw new org.apache.axis2.databinding.ADBException("IV_LIFNR cannot be null!!");
                                    }
                                    elementList.add(localIV_LIFNR);
                                 if (localRETURNTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "RETURN"));
                            
                            
                                    if (localRETURN==null){
                                         throw new org.apache.axis2.databinding.ADBException("RETURN cannot be null!!");
                                    }
                                    elementList.add(localRETURN);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Y10_FG_ACC_STATUS parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Y10_FG_ACC_STATUS object =
                new Y10_FG_ACC_STATUS();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Y10_FG_ACC_STATUS".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Y10_FG_ACC_STATUS)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ET_CLEAREDITEMS").equals(reader.getName())){
                                
                                                object.setET_CLEAREDITEMS(functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ET_OPENITEMS").equals(reader.getName())){
                                
                                                object.setET_OPENITEMS(functions.rfc.sap.document.sap_com.TABLE_OF_Y10_STR_ACC_STATUS.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IV_BUKRS").equals(reader.getName())){
                                
                                                object.setIV_BUKRS(functions.rfc.sap.document.sap_com.Char4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IV_LANGUAGE").equals(reader.getName())){
                                
                                                object.setIV_LANGUAGE(functions.rfc.sap.document.sap_com.Lang.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IV_LIFNR").equals(reader.getName())){
                                
                                                object.setIV_LIFNR(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RETURN").equals(reader.getName())){
                                
                                                object.setRETURN(functions.rfc.sap.document.sap_com.TABLE_OF_BAPIRET2.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    