
/**
 * ZWS_EXPENSE_UTILS_METHODSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /**
     *  ZWS_EXPENSE_UTILS_METHODSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ZWS_EXPENSE_UTILS_METHODSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ZWS_EXPENSE_UTILS_METHODSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ZWS_EXPENSE_UTILS_METHODSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for y10_SEARCH_DOCUMENT_ID method
            * override this method for handling normal response from y10_SEARCH_DOCUMENT_ID operation
            */
           public void receiveResulty10_SEARCH_DOCUMENT_ID(
                    functions.rfc.sap.document.sap_com.Y10_SEARCH_DOCUMENT_IDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_SEARCH_DOCUMENT_ID operation
           */
            public void receiveErrory10_SEARCH_DOCUMENT_ID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_LIFNR_NAME method
            * override this method for handling normal response from y10_GET_LIFNR_NAME operation
            */
           public void receiveResulty10_GET_LIFNR_NAME(
                    functions.rfc.sap.document.sap_com.Y10_GET_LIFNR_NAMEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_LIFNR_NAME operation
           */
            public void receiveErrory10_GET_LIFNR_NAME(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_EXPENSE_STATEMENT method
            * override this method for handling normal response from y10_GET_EXPENSE_STATEMENT operation
            */
           public void receiveResulty10_GET_EXPENSE_STATEMENT(
                    functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_STATEMENTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_EXPENSE_STATEMENT operation
           */
            public void receiveErrory10_GET_EXPENSE_STATEMENT(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_VALUES_FROM_XML method
            * override this method for handling normal response from y10_GET_VALUES_FROM_XML operation
            */
           public void receiveResulty10_GET_VALUES_FROM_XML(
                    functions.rfc.sap.document.sap_com.Y10_GET_VALUES_FROM_XMLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_VALUES_FROM_XML operation
           */
            public void receiveErrory10_GET_VALUES_FROM_XML(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_EXPENSE_DETAILS method
            * override this method for handling normal response from y10_GET_EXPENSE_DETAILS operation
            */
           public void receiveResulty10_GET_EXPENSE_DETAILS(
                    functions.rfc.sap.document.sap_com.Y10_GET_EXPENSE_DETAILSResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_EXPENSE_DETAILS operation
           */
            public void receiveErrory10_GET_EXPENSE_DETAILS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_FIELDS_FROM_XML method
            * override this method for handling normal response from y10_GET_FIELDS_FROM_XML operation
            */
           public void receiveResulty10_GET_FIELDS_FROM_XML(
                    functions.rfc.sap.document.sap_com.Y10_GET_FIELDS_FROM_XMLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_FIELDS_FROM_XML operation
           */
            public void receiveErrory10_GET_FIELDS_FROM_XML(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_CONF_DATA_FOR_EXPEN method
            * override this method for handling normal response from y10_GET_CONF_DATA_FOR_EXPEN operation
            */
           public void receiveResulty10_GET_CONF_DATA_FOR_EXPEN(
                    functions.rfc.sap.document.sap_com.Y10_GET_CONF_DATA_FOR_EXPENResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_CONF_DATA_FOR_EXPEN operation
           */
            public void receiveErrory10_GET_CONF_DATA_FOR_EXPEN(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_PASS_CHECK method
            * override this method for handling normal response from y10_PASS_CHECK operation
            */
           public void receiveResulty10_PASS_CHECK(
                    functions.rfc.sap.document.sap_com.Y10_PASS_CHECKResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_PASS_CHECK operation
           */
            public void receiveErrory10_PASS_CHECK(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_PASS_CHANGE method
            * override this method for handling normal response from y10_PASS_CHANGE operation
            */
           public void receiveResulty10_PASS_CHANGE(
                    functions.rfc.sap.document.sap_com.Y10_PASS_CHANGEResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_PASS_CHANGE operation
           */
            public void receiveErrory10_PASS_CHANGE(java.lang.Exception e) {
            }
                


    }
    