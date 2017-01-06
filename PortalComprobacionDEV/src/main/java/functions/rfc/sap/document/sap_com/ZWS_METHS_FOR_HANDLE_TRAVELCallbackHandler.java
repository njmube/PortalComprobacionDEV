
/**
 * ZWS_METHS_FOR_HANDLE_TRAVELCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /**
     *  ZWS_METHS_FOR_HANDLE_TRAVELCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ZWS_METHS_FOR_HANDLE_TRAVELCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ZWS_METHS_FOR_HANDLE_TRAVELCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ZWS_METHS_FOR_HANDLE_TRAVELCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for y10_INSTANCE_TRAVEL_DATA method
            * override this method for handling normal response from y10_INSTANCE_TRAVEL_DATA operation
            */
           public void receiveResulty10_INSTANCE_TRAVEL_DATA(
                    functions.rfc.sap.document.sap_com.Y10_INSTANCE_TRAVEL_DATAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_INSTANCE_TRAVEL_DATA operation
           */
            public void receiveErrory10_INSTANCE_TRAVEL_DATA(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_GET_TRAVEL_DATA method
            * override this method for handling normal response from y10_GET_TRAVEL_DATA operation
            */
           public void receiveResulty10_GET_TRAVEL_DATA(
                    functions.rfc.sap.document.sap_com.Y10_GET_TRAVEL_DATAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_GET_TRAVEL_DATA operation
           */
            public void receiveErrory10_GET_TRAVEL_DATA(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_SAVE_TRAVEL_DATA method
            * override this method for handling normal response from y10_SAVE_TRAVEL_DATA operation
            */
           public void receiveResulty10_SAVE_TRAVEL_DATA(
                    functions.rfc.sap.document.sap_com.Y10_SAVE_TRAVEL_DATAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_SAVE_TRAVEL_DATA operation
           */
            public void receiveErrory10_SAVE_TRAVEL_DATA(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for y10_CHECK_TRAVEL_DATA method
            * override this method for handling normal response from y10_CHECK_TRAVEL_DATA operation
            */
           public void receiveResulty10_CHECK_TRAVEL_DATA(
                    functions.rfc.sap.document.sap_com.Y10_CHECK_TRAVEL_DATAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from y10_CHECK_TRAVEL_DATA operation
           */
            public void receiveErrory10_CHECK_TRAVEL_DATA(java.lang.Exception e) {
            }
                


    }
    