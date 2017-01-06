
/**
 * Y10_STR_WBS_CONF_EXPCLASES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Y10_STR_WBS_CONF_EXPCLASES bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Y10_STR_WBS_CONF_EXPCLASES
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Y10_STR_WBS_CONF_EXPCLASES
                Namespace URI = urn:sap-com:document:sap:rfc:functions
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for EXPENSECLASS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char4 localEXPENSECLASS ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char4
                           */
                           public  functions.rfc.sap.document.sap_com.Char4 getEXPENSECLASS(){
                               return localEXPENSECLASS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EXPENSECLASS
                               */
                               public void setEXPENSECLASS(functions.rfc.sap.document.sap_com.Char4 param){
                            
                                            this.localEXPENSECLASS=param;
                                    

                               }
                            

                        /**
                        * field for HKONT
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localHKONT ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getHKONT(){
                               return localHKONT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HKONT
                               */
                               public void setHKONT(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localHKONT=param;
                                    

                               }
                            

                        /**
                        * field for EXPENSECLASSDES
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char50 localEXPENSECLASSDES ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char50
                           */
                           public  functions.rfc.sap.document.sap_com.Char50 getEXPENSECLASSDES(){
                               return localEXPENSECLASSDES;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EXPENSECLASSDES
                               */
                               public void setEXPENSECLASSDES(functions.rfc.sap.document.sap_com.Char50 param){
                            
                                            this.localEXPENSECLASSDES=param;
                                    

                               }
                            

                        /**
                        * field for RULTYPSAT
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char2 localRULTYPSAT ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char2
                           */
                           public  functions.rfc.sap.document.sap_com.Char2 getRULTYPSAT(){
                               return localRULTYPSAT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RULTYPSAT
                               */
                               public void setRULTYPSAT(functions.rfc.sap.document.sap_com.Char2 param){
                            
                                            this.localRULTYPSAT=param;
                                    

                               }
                            

                        /**
                        * field for RULTYPBUS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char2 localRULTYPBUS ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char2
                           */
                           public  functions.rfc.sap.document.sap_com.Char2 getRULTYPBUS(){
                               return localRULTYPBUS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RULTYPBUS
                               */
                               public void setRULTYPBUS(functions.rfc.sap.document.sap_com.Char2 param){
                            
                                            this.localRULTYPBUS=param;
                                    

                               }
                            

                        /**
                        * field for UNIT
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Unit3 localUNIT ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Unit3
                           */
                           public  functions.rfc.sap.document.sap_com.Unit3 getUNIT(){
                               return localUNIT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UNIT
                               */
                               public void setUNIT(functions.rfc.sap.document.sap_com.Unit3 param){
                            
                                            this.localUNIT=param;
                                    

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
                           namespacePrefix+":Y10_STR_WBS_CONF_EXPCLASES",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Y10_STR_WBS_CONF_EXPCLASES",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localEXPENSECLASS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("EXPENSECLASS cannot be null!!");
                                            }
                                           localEXPENSECLASS.serialize(new javax.xml.namespace.QName("","EXPENSECLASS"),
                                               xmlWriter);
                                        
                                            if (localHKONT==null){
                                                 throw new org.apache.axis2.databinding.ADBException("HKONT cannot be null!!");
                                            }
                                           localHKONT.serialize(new javax.xml.namespace.QName("","HKONT"),
                                               xmlWriter);
                                        
                                            if (localEXPENSECLASSDES==null){
                                                 throw new org.apache.axis2.databinding.ADBException("EXPENSECLASSDES cannot be null!!");
                                            }
                                           localEXPENSECLASSDES.serialize(new javax.xml.namespace.QName("","EXPENSECLASSDES"),
                                               xmlWriter);
                                        
                                            if (localRULTYPSAT==null){
                                                 throw new org.apache.axis2.databinding.ADBException("RULTYPSAT cannot be null!!");
                                            }
                                           localRULTYPSAT.serialize(new javax.xml.namespace.QName("","RULTYPSAT"),
                                               xmlWriter);
                                        
                                            if (localRULTYPBUS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("RULTYPBUS cannot be null!!");
                                            }
                                           localRULTYPBUS.serialize(new javax.xml.namespace.QName("","RULTYPBUS"),
                                               xmlWriter);
                                        
                                            if (localUNIT==null){
                                                 throw new org.apache.axis2.databinding.ADBException("UNIT cannot be null!!");
                                            }
                                           localUNIT.serialize(new javax.xml.namespace.QName("","UNIT"),
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
                                                                      "EXPENSECLASS"));
                            
                            
                                    if (localEXPENSECLASS==null){
                                         throw new org.apache.axis2.databinding.ADBException("EXPENSECLASS cannot be null!!");
                                    }
                                    elementList.add(localEXPENSECLASS);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "HKONT"));
                            
                            
                                    if (localHKONT==null){
                                         throw new org.apache.axis2.databinding.ADBException("HKONT cannot be null!!");
                                    }
                                    elementList.add(localHKONT);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "EXPENSECLASSDES"));
                            
                            
                                    if (localEXPENSECLASSDES==null){
                                         throw new org.apache.axis2.databinding.ADBException("EXPENSECLASSDES cannot be null!!");
                                    }
                                    elementList.add(localEXPENSECLASSDES);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "RULTYPSAT"));
                            
                            
                                    if (localRULTYPSAT==null){
                                         throw new org.apache.axis2.databinding.ADBException("RULTYPSAT cannot be null!!");
                                    }
                                    elementList.add(localRULTYPSAT);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "RULTYPBUS"));
                            
                            
                                    if (localRULTYPBUS==null){
                                         throw new org.apache.axis2.databinding.ADBException("RULTYPBUS cannot be null!!");
                                    }
                                    elementList.add(localRULTYPBUS);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "UNIT"));
                            
                            
                                    if (localUNIT==null){
                                         throw new org.apache.axis2.databinding.ADBException("UNIT cannot be null!!");
                                    }
                                    elementList.add(localUNIT);
                                

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
        public static Y10_STR_WBS_CONF_EXPCLASES parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Y10_STR_WBS_CONF_EXPCLASES object =
                new Y10_STR_WBS_CONF_EXPCLASES();

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
                    
                            if (!"Y10_STR_WBS_CONF_EXPCLASES".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Y10_STR_WBS_CONF_EXPCLASES)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","EXPENSECLASS").equals(reader.getName())){
                                
                                                object.setEXPENSECLASS(functions.rfc.sap.document.sap_com.Char4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","HKONT").equals(reader.getName())){
                                
                                                object.setHKONT(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","EXPENSECLASSDES").equals(reader.getName())){
                                
                                                object.setEXPENSECLASSDES(functions.rfc.sap.document.sap_com.Char50.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RULTYPSAT").equals(reader.getName())){
                                
                                                object.setRULTYPSAT(functions.rfc.sap.document.sap_com.Char2.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","RULTYPBUS").equals(reader.getName())){
                                
                                                object.setRULTYPBUS(functions.rfc.sap.document.sap_com.Char2.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","UNIT").equals(reader.getName())){
                                
                                                object.setUNIT(functions.rfc.sap.document.sap_com.Unit3.Factory.parse(reader));
                                              
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
           
    