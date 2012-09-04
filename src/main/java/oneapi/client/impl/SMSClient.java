package oneapi.client.impl;

import oneapi.client.CustomerProfileClient;
import oneapi.client.HLRClient;
import oneapi.client.SMSMessagingClient;
import oneapi.config.Configuration;
import oneapi.listener.LoginListener;
import oneapi.listener.LogoutListener;
import oneapi.model.Authentication.AuthType;
import oneapi.model.common.LoginResponse;


public class SMSClient {
  
	protected static final String VERSION = "1.0.0"; 
	
    private CustomerProfileClient customerProfileClient = null;
    private SMSMessagingClient smsMessagingClient = null;
    private HLRClient hlrClient = null;
    private LoginListener loginListener = null;
    private LogoutListener logoutListener = null;
    private Configuration configuration = null;
  
    //*************************SMSClient initialization***********************************************************************************************************************************************
    /**
     * Initialize SMS client using specified 'configuration' parameter
     * @param configuration - parameter containing OneAPI configuration data
     */
    public SMSClient(Configuration configuration) {

        this.configuration = configuration;


        //Set Login and Logout listeners so that authorization mode can be switched to IBSSO
        setLoginListener();
        setLogoutListener();

        //Initialize Clients     
        customerProfileClient = new CustomerProfileClientImpl(configuration, loginListener, logoutListener);
        smsMessagingClient = new SMSMessagingClientImpl(configuration);   
        hlrClient = new HLRClientImpl(configuration);
    }

    //*************************SMSClient public***********************************************************************************************************************************************
    /**
     * Get Customer Profile client
     * @return CustomerProfileClient
     */
    public CustomerProfileClient getCustomerProfileClient() {
        return customerProfileClient;
    }
    
    /**
     * Get SMS Messaging client
     * @return SMSMessagingClient
     */
    public SMSMessagingClient getSMSMessagingClient() {
        return smsMessagingClient;
    }
    
   /**
     * Get HLR client
     * @return HLRClient
     */
    public HLRClient getHLRClient() {
        return hlrClient;
    }
    
   
	//*************************SMSClient private***********************************************************************************************************************************************
    private void setLoginListener() {
        loginListener = new LoginListener() {
            @Override
            public void onLogin(LoginResponse response) {
                if ((response != null) && (!response.getIbAuthCookie().isEmpty())) {
                    configuration.getAuthentication().setType(AuthType.IBSSO);
                    configuration.getAuthentication().setIbssoToken(response.getIbAuthCookie());
                }
            }
        };
    }

    private void setLogoutListener() {
        logoutListener = new LogoutListener() {
            @Override
            public void onLogout() {
                configuration.getAuthentication().setIbssoToken("");
            }
        };
    }
}
