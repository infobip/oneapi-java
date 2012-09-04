package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.HLRNotificationsListener;
import oneapi.model.RoamingNotification;
import oneapi.model.common.LoginResponse;


/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.QueryHLR_WaitForHLRPush' class to edit where you should populate the following fields: 
 *		'address'   'username'    
 *		'notifyUrl' 'password'         
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'HLR Notifications' push server is started automatically by adding 'HLRNotificationsListener'
 *  using the 'AddPushHlrNotificationsListener' method. Default server port is 3002 and it can be changed by set the 
 *  'Configuration' property 'HlrPushServerSimulatorPort'. 
 **/

public class QueryHLR_WaitForHLRPush {
	
	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";    
	private static String address = "";
	private static String notifyUrl = ""; //e.g. "http://127.0.0.1:3002/" 3002=Default port for 'HLR Notifications' server simulator

	public static void main(String[] args) {
		
		try
		{
			// Initialize Configuration object 
			Configuration configuration = new Configuration(username, password);

			// Initialize SMSClient using the Configuration object
			SMSClient smsClient = new SMSClient(configuration);
			
			// Login sms client
			LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
			if (loginResponse.isVerified() == false)
			{
				System.out.println("User is not verified!");
				return;
			}

			// Add listener(start push server and wait for the 'HLR Notifications')
			smsClient.getHLRClient().addPushHLRNotificationsListener(new HLRNotificationsListener() {			
				@Override
				public void OnHLRReceived(RoamingNotification roamingNotification) {
					// Handle pushed 'HLR Notification'
					if (roamingNotification != null) {
						System.out.println(roamingNotification);
					}
				}
			});

			// example:retrieve-roaming-status-with-notify-url
			smsClient.getHLRClient().queryHLR(address, notifyUrl);
			// ----------------------------------------------------------------------------------------------------
					
			// Wait 30 seconds for 'HLR Notification' push-es before closing the server connection 
            Thread.sleep(30000);
			
			// Logout sms client
			smsClient.getCustomerProfileClient().logout();
			
			// Remove 'HLR Notification' push listeners and stop the server
			smsClient.getHLRClient().removePushHLRNotificationsListeners();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());	
		}  
	}
}

