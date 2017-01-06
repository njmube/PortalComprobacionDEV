
/**
 * Y10_STR_EXPENDIST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Y10_STR_EXPENDIST bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Y10_STR_EXPENDIST
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Y10_STR_EXPENDIST
                Namespace URI = urn:sap-com:document:sap:rfc:functions
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for EXPENSELINE
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Numeric4 localEXPENSELINE ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Numeric4
                           */
                           public  functions.rfc.sap.document.sap_com.Numeric4 getEXPENSELINE(){
                               return localEXPENSELINE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EXPENSELINE
                               */
                               public void setEXPENSELINE(functions.rfc.sap.document.sap_com.Numeric4 param){
                            
                                            this.localEXPENSELINE=param;
                                    

                               }
                            

                        /**
                        * field for DISTLIN
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Numeric4 localDISTLIN ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Numeric4
                           */
                           public  functions.rfc.sap.document.sap_com.Numeric4 getDISTLIN(){
                               return localDISTLIN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DISTLIN
                               */
                               public void setDISTLIN(functions.rfc.sap.document.sap_com.Numeric4 param){
                            
                                            this.localDISTLIN=param;
                                    

                               }
                            

                        /**
                        * field for KOSTL
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localKOSTL ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getKOSTL(){
                               return localKOSTL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param KOSTL
                               */
                               public void setKOSTL(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localKOSTL=param;
                                    

                               }
                            

                        /**
                        * field for COST_PERCENT
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Decimal52 localCOST_PERCENT ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Decimal52
                           */
                           public  functions.rfc.sap.document.sap_com.Decimal52 getCOST_PERCENT(){
                               return localCOST_PERCENT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param COST_PERCENT
                               */
                               public void setCOST_PERCENT(functions.rfc.sap.document.sap_com.Decimal52 param){
                            
                                            this.localCOST_PERCENT=param;
                                    

                               }
                            

                        /**
                        * field for APPDMBTR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Curr132 localAPPDMBTR ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Curr132
                           */
                           public  functions.rfc.sap.document.sap_com.Curr132 getAPPDMBTR(){
                               return localAPPDMBTR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param APPDMBTR
                               */
                               public void setAPPDMBTR(functions.rfc.sap.document.sap_com.Curr132 param){
                            
                                            this.localAPPDMBTR=param;
                                    

                               }
                            

                        /**
                        * field for OPERFLAG
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localOPERFLAG ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getOPERFLAG(){
                               return localOPERFLAG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OPERFLAG
                               */
                               public void setOPERFLAG(functions.rfc.sap.document.sap_com.Char1 param){
                            
                                            this.localOPERFLAG=param;
                                    

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
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
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
                           namespacePrefix+":Y10_STR_EXPENDIST",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Y10_STR_EXPENDIST",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localEXPENSELINE==null){
                                                 throw new org.apache.axis2.databinding.ADBException("EXPENSELINE cannot be null!!");
                                            }
                                           localEXPENSELINE.serialize(new javax.xml.namespace.QName("","EXPENSELINE"),
                                               xmlWriter);
                                        
                                            if (localDISTLIN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("DISTLIN cannot be null!!");
                                            }
                                           localDISTLIN.serialize(new javax.xml.namespace.QName("","DISTLIN"),
                                               xmlWriter);
                                        
                                            if (localKOSTL==null){
                                                 throw new org.apache.axis2.databinding.ADBException("KOSTL cannot be null!!");
                                            }
                                           localKOSTL.serialize(new javax.xml.namespace.QName("","KOSTL"),
                                               xmlWriter);
                                        
                                            if (localCOST_PERCENT==null){
                                                 throw new org.apache.axis2.databinding.ADBException("COST_PERCENT cannot be null!!");
                                            }
                                           localCOST_PERCENT.serialize(new javax.xml.namespace.QName("","COST_PERCENT"),
                                               xmlWriter);
                                        
                                            if (localAPPDMBTR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("APPDMBTR cannot be null!!");
                                            }
                                           localAPPDMBTR.serialize(new javax.xml.namespace.QName("","APPDMBTR"),
                                               xmlWriter);
                                        
                                            if (localOPERFLAG==null){
                                                 throw new org.apache.axis2.databinding.ADBException("OPERFLAG cannot be null!!");
                                            }
                                           localOPERFLAG.serialize(new javax.xml.namespace.QName("","OPERFLAG"),
                                               xmlWriter);
                                        
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

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "EXPENSELINE"));
                            
                            
                                    if (localEXPENSELINE==null){
                                         throw new org.apache.axis2.databinding.ADBException("EXPENSELINE cannot be null!!");
                                    }
                                    elementList.add(localEXPENSELINE);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "DISTLIN"));
                            
                            
                                    if (localDISTLIN==null){
                                         throw new org.apache.axis2.databinding.ADBException("DISTLIN cannot be null!!");
                                    }
                                    elementList.add(localDISTLIN);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "KOSTL"));
                            
                            
                                    if (localKOSTL==null){
                                         throw new org.apache.axis2.databinding.ADBException("KOSTL cannot be null!!");
                                    }
                                    elementList.add(localKOSTL);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "COST_PERCENT"));
                            
                            
                                    if (localCOST_PERCENT==null){
                                         throw new org.apache.axis2.databinding.ADBException("COST_PERCENT cannot be null!!");
                                    }
                                    elementList.add(localCOST_PERCENT);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "APPDMBTR"));
                            
                            
                                    if (localAPPDMBTR==null){
                                         throw new org.apache.axis2.databinding.ADBException("APPDMBTR cannot be null!!");
                                    }
                                    elementList.add(localAPPDMBTR);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "OPERFLAG"));
                            
                            
                                    if (localOPERFLAG==null){
                                         throw new org.apache.axis2.databinding.ADBException("OPERFLAG cannot be null!!");
                                    }
                                    elementList.add(localOPERFLAG);
                                

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
        public static Y10_STR_EXPENDIST parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Y10_STR_EXPENDIST object =
                new Y10_STR_EXPENDIST();

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
                    
                            if (!"Y10_STR_EXPENDIST".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Y10_STR_EXPENDIST)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","EXPENSELINE").equals(reader.getName())){
                                
                                                object.setEXPENSELINE(functions.rfc.sap.document.sap_com.Numeric4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","DISTLIN").equals(reader.getName())){
                                
                                                object.setDISTLIN(functions.rfc.sap.document.sap_com.Numeric4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","KOSTL").equals(reader.getName())){
                                
                                                object.setKOSTL(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","COST_PERCENT").equals(reader.getName())){
                                
                                                object.setCOST_PERCENT(functions.rfc.sap.document.sap_com.Decimal52.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","APPDMBTR").equals(reader.getName())){
                                
                                                object.setAPPDMBTR(functions.rfc.sap.document.sap_com.Curr132.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","OPERFLAG").equals(reader.getName())){
                                
                                                object.setOPERFLAG(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
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
           
    