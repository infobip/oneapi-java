package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryStatusNotificationsListener;
import oneapi.model.DeliveryInfoNotification;
import oneapi.model.SMSRequest;
import oneapi.model.common.LoginResponse;


/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_WaitForDeliveryStatusPush' class to edit where you should populate the following fields: 
 *		'senderAddress'     'notifyUrl'   'username'
 *		'message'           'criteria'    'password'        
 *		'recipientAddress'   
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Delivery Status Notifications' push server is started automatically by adding 'DeliveryStatusNotificationsListener'
 *  using the 'addPushDeliveryStatusNotificationListener' method. Default server port is 3000 and it can be changed by set the 
 *  'Configuration' property 'setDlrStatusPushServerSimulatorPort'. 
 *  Used port should match the one used in the 'SMSRequest' 'notifyUrl' parameter.
 **/

public class SendSMS_WaitForDeliveryStatusPush {

	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String senderAddress = "";
	private static String message = "";
	private static String recipientAddress = "";
	private static String notifyUrl = ""; //e.g. "http://127.0.0.1:3000/" 3000=Default port for 'Delivery Info Notifications' server simulator
	
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

			// Add listener(start push server and wait for the 'Delivery Info Notifications')    
			smsClient.getSMSMessagingClient().addPushDeliveryStatusNotificationListener(new DeliveryStatusNotificationsListener() {		
				@Override
				public void onDeliveryStatusNotificationReceived(DeliveryInfoNotification deliveryInfoNotification) {
					// Handle pushed 'Delivery Info Notification'
					if (deliveryInfoNotification != null) 
					{
						String deliveryStatus = deliveryInfoNotification.getDeliveryInfo().getDeliveryStatus();
						System.out.println(deliveryStatus);
					}  
				}
			});

			 // example:prepare-message-with-notify-url
            SMSRequest smsRequest = new SMSRequest(senderAddress, message, recipientAddress);
            // The url where the delivery notification will be pushed:
            smsRequest.setNotifyURL(notifyUrl); 
            // ----------------------------------------------------------------------------------------------------
			
			// Send SMS 
			smsClient.getSMSMessagingClient().sendSMS(smsRequest);      
		
			 // Wait 30 seconds for 'Delivery Info Notification' push-es before closing the server connection 
            Thread.sleep(30000);

			// Logout sms client
			smsClient.getCustomerProfileClient().logout();

			// Remove 'Delivery Info Notifications' push listeners and stop the server
			smsClient.getSMSMessagingClient().removePushDeliveryStatusNotificationListeners();  

		} catch (Exception e) {  
			System.out.println(e.getMessage());
		}
	}
}
