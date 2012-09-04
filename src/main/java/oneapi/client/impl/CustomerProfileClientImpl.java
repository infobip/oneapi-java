package oneapi.client.impl;

import java.net.HttpURLConnection;

import javax.swing.event.EventListenerList;

import oneapi.client.CustomerProfileClient;
import oneapi.config.Configuration;
import oneapi.listener.LoginListener;
import oneapi.listener.LogoutListener;
import oneapi.model.LoginRequest;
import oneapi.model.common.AccountBalance;
import oneapi.model.common.CustomerProfile;
import oneapi.model.common.LoginResponse;

public class CustomerProfileClientImpl extends OneAPIBaseClientImpl implements CustomerProfileClient {
	private static final String CUSTOMER_PROFILE_URL_BASE = "/customerProfile";
	
	private EventListenerList loginListenersList = null;
	private EventListenerList logoutListenerList = null;

	//*************************CustomerProfileClientImpl initialization***********************************************************************************************************************************************
	public CustomerProfileClientImpl(Configuration configuration, LoginListener loginListner, LogoutListener logoutListener) {
		super(configuration);
		addLoginListener(loginListner);
		addLogoutListener(logoutListener);
	}

	//*************************CustomerProfileClientImpl public***********************************************************************************************************************************************
	@Override
	public LoginResponse login() {
		LoginRequest loginRequest = new LoginRequest(getConfiguration().getAuthentication().getUsername(), getConfiguration().getAuthentication().getPassword());	
		HttpURLConnection connection = executePost(appendMessagingBaseUrl(CUSTOMER_PROFILE_URL_BASE.concat("/login")), loginRequest);
		LoginResponse response = deserialize(connection, LoginResponse.class, RESPONSE_CODE_200_OK, "login");
		fireOnLogin(response);
		return response;
	}
	
	@Override
	public void logout() {
		HttpURLConnection connection = executePost(appendMessagingBaseUrl(CUSTOMER_PROFILE_URL_BASE.concat("/logout")));
		validateResponse(connection, getResponseCode(connection), RESPONSE_CODE_204_NO_CONTENT);
		fireOnLogout();
	}

	@Override
	public CustomerProfile getCustomerProfile() {	
		HttpURLConnection connection = executeGet(appendMessagingBaseUrl(CUSTOMER_PROFILE_URL_BASE));
		return deserialize(connection, CustomerProfile.class, RESPONSE_CODE_200_OK); 
	}
	
	@Override
	public CustomerProfile[] getCustomerProfiles() {	
		HttpURLConnection connection = executeGet(appendMessagingBaseUrl(CUSTOMER_PROFILE_URL_BASE).concat("/list"));
		return deserialize(connection, CustomerProfile[].class, RESPONSE_CODE_200_OK, "customerProfiles"); 
	}

	@Override
	public CustomerProfile getCustomerProfileByUserId(int id) {	
		StringBuilder urlBuilder = new StringBuilder(CUSTOMER_PROFILE_URL_BASE).append("/");
		urlBuilder.append(encodeURLParam(String.valueOf(id)));
		
		HttpURLConnection connection = executeGet(appendMessagingBaseUrl(urlBuilder.toString()));
		return deserialize(connection, CustomerProfile.class, RESPONSE_CODE_200_OK); 
	}
	
	@Override
    public AccountBalance getAccountBalance()
    {	
		HttpURLConnection connection = executeGet(appendMessagingBaseUrl(CUSTOMER_PROFILE_URL_BASE.concat("/balance")));
		return deserialize(connection, AccountBalance.class, RESPONSE_CODE_200_OK); 
    }
	
	//*************************CustomerProfileClientImpl private******************************************************************************************************************************************************
	/**
	 *  Add OneAPI Login listener
	 * @param listener - (new LoginListener)
	 */
	private void addLoginListener(LoginListener listener) {
		if (listener == null) return;
		if (this.loginListenersList == null) {
			this.loginListenersList = new EventListenerList();
		}
		this.loginListenersList.add(LoginListener.class, listener);
	}

	/**
	 * Add OneAPI Logout listener
	 * @param listener - (new LogoutListener)
	 */
	private void addLogoutListener(LogoutListener listener) {
		if (listener == null) return;
		if (this.logoutListenerList == null) {
			this.logoutListenerList = new EventListenerList();
		}
		this.logoutListenerList.add(LogoutListener.class, listener);
	}

	/**
	 * Fire on Login done
	 * @param response
	 */
	private void fireOnLogin(LoginResponse response) {
		if (loginListenersList != null) {
			// Each listener occupies two elements - the first is the listener class
			// and the second is the listener instance
			for (int i=0; i<loginListenersList.getListenerCount(); i+=2) {
				if (loginListenersList.getListenerList()[i]==LoginListener.class) {
					((LoginListener)loginListenersList.getListenerList()[i+1]).onLogin(response);
				}
			}
		}
	}

	/**
	 * Fire on Logout done
	 */
	private void fireOnLogout() {
		if (logoutListenerList != null) {
			// Each listener occupies two elements - the first is the listener class
			// and the second is the listener instance
			for (int i=0; i<logoutListenerList.getListenerCount(); i+=2) {
				if (logoutListenerList.getListenerList()[i]==LogoutListener.class) {
					((LogoutListener)logoutListenerList.getListenerList()[i+1]).onLogout();
				}
			}
		}
	}
}
