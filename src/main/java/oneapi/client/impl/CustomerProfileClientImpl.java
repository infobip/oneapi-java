package oneapi.client.impl;

import java.util.ArrayList;
import java.util.List;
import oneapi.client.CustomerProfileClient;
import oneapi.config.Configuration;
import oneapi.listener.LoginListener;
import oneapi.listener.LogoutListener;
import oneapi.model.LoginRequest;
import oneapi.model.RequestData;
import oneapi.model.RequestData.Method;
import oneapi.model.common.AccountBalance;
import oneapi.model.common.CustomerProfile;
import oneapi.model.common.LoginResponse;

public class CustomerProfileClientImpl extends OneAPIBaseClientImpl implements CustomerProfileClient {
	private static final String CUSTOMER_PROFILE_URL_BASE = "/customerProfile";
	
	private List<LoginListener> loginListenersList = null;
	private List<LogoutListener> logoutListenerList = null;

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
		RequestData requestData = new RequestData(CUSTOMER_PROFILE_URL_BASE + "/login", RESPONSE_CODE_200_OK, Method.POST, "login", loginRequest, URL_ENCODED_CONTENT_TYPE);
		LoginResponse response = executeMethod(requestData, LoginResponse.class);
		fireOnLogin(response);
		return response;
	}
	
	@Override
	public void logout() {
		RequestData requestData = new RequestData(CUSTOMER_PROFILE_URL_BASE + "/logout", RESPONSE_CODE_204_NO_CONTENT, Method.POST);
		executeMethod(requestData);
		fireOnLogout();
	}

	@Override
	public CustomerProfile getCustomerProfile() {	
		 RequestData requestData = new RequestData(CUSTOMER_PROFILE_URL_BASE, RESPONSE_CODE_200_OK, Method.GET);
		 return executeMethod(requestData, CustomerProfile.class);
	}
	
	@Override
	public CustomerProfile[] getCustomerProfiles() {	
		RequestData requestData = new RequestData(CUSTOMER_PROFILE_URL_BASE + "/list", RESPONSE_CODE_200_OK, Method.GET);
		return executeMethod(requestData, CustomerProfile[].class);
	}

	@Override
	public CustomerProfile getCustomerProfileByUserId(int id) {	
		StringBuilder urlBuilder = new StringBuilder(CUSTOMER_PROFILE_URL_BASE).append("/");
		urlBuilder.append(encodeURLParam(String.valueOf(id)));

		RequestData requestData = new RequestData(urlBuilder.toString(), RESPONSE_CODE_200_OK, Method.GET);
		return executeMethod(requestData, CustomerProfile.class);
	}
	
	@Override
    public AccountBalance getAccountBalance()
    {	
		RequestData requestData = new RequestData(CUSTOMER_PROFILE_URL_BASE + "/balance", RESPONSE_CODE_200_OK, Method.GET);
		return executeMethod(requestData, AccountBalance.class);
    }
	
	//*************************CustomerProfileClientImpl private******************************************************************************************************************************************************
	/**
	 *  Add OneAPI Login listener
	 * @param listener - (new LoginListener)
	 */
	private void addLoginListener(LoginListener listener) {
		if (listener == null) return;
		if (this.loginListenersList == null) {
			this.loginListenersList = new ArrayList<LoginListener>();
		}
		this.loginListenersList.add(listener);
	}

	/**
	 * Add OneAPI Logout listener
	 * @param listener - (new LogoutListener)
	 */
	private void addLogoutListener(LogoutListener listener) {
		if (listener == null) return;
		if (this.logoutListenerList == null) {
			this.logoutListenerList = new ArrayList<LogoutListener>();
		}
		this.logoutListenerList.add(listener);
	}

	/**
	 * Fire on Login done
	 * @param response
	 */
	private void fireOnLogin(LoginResponse response) {
		if (loginListenersList != null) {
			for (LoginListener listener : loginListenersList) {
				listener.onLogin(response);
			}	
		}
	}

	/**
	 * Fire on Logout done
	 */
	private void fireOnLogout() {
		if (logoutListenerList != null) {
			for (LogoutListener listener : logoutListenerList) {
				listener.onLogout();
			}	
		}
	}
}
