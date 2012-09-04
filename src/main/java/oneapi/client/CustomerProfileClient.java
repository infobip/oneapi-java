package oneapi.client;

import oneapi.model.common.AccountBalance;
import oneapi.model.common.CustomerProfile;
import oneapi.model.common.LoginResponse;

public interface CustomerProfileClient {	

	/**
	 * User Login
	 * @return LoginResponse
	 */
	LoginResponse login();

	/**
	 * User Logout
	 */
	void logout();

	
	/**
	 * Gets logged user customer profile 
	 * @param id
	 * @return CustomerProfile
	 */
	CustomerProfile getCustomerProfile();

	/**
	 * Gets customer profile for specific userId
	 * @param id
	 * @return CustomerProfile
	 */
	CustomerProfile getCustomerProfileByUserId(int id);

	/**
	 * Gets all users for currently logged user. Currently logged user must be a main user
	 * @return CustomerProfile[]
	 */
	CustomerProfile[] getCustomerProfiles();	

	/**
	 * Get logged user account balance
	 * @return AccountBalance
	 */
	AccountBalance getAccountBalance();
}