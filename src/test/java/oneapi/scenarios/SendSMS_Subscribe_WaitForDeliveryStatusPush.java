package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryStatusNotificationsListener;
import oneapi.model.DeliveryInfoNotification;
import oneapi.model.SMSRequest;
import oneapi.model.SubscribeToDeliveryNotificationsRequest;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_Subscribe_WaitForDeliveryStatusPush' class to edit where you should populate the following fields: 
 *		'senderAddress'     'notifyUrl'   'username'
 *		'message'           'criteria'    'password'        
 *		'recipientAddress'   
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Delivery Status Notifications' push server is started automatically by adding 'DeliveryStatusNotificationsListener'
 *  using the 'addPushDeliveryStatusNotificationListener' method. Default server port is 3000 and it can be changed by set the 
 *  'Configuration' property 'setDlrStatusPushServerSimulatorPort'. Used port should match the one used in the 'notifyUrl' property when 
 *  subscribing for the notifications using the 'SubscribeToDeliveryStatusNotifications' method.
 **/

public class SendSMS_Subscribe_WaitForDeliveryStatusPush {

	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String senderAddress = "";
	private static String message = "";
	private static String recipientAddress = "";
	private static String notifyUrl = ""; //e.g. "http://127.0.0.1:3000/" 3000=Default port for 'Delivery Info Notifications' server simulator
	private static String criteria = "";

	public static void main(String[] args) {

		try 
		{
			// Initialize Configuration object 
			Configuration configuration = new Configuration(username, password);

			// Initialize SMSClient using the Configuration object
			SMSClient smsClient = new SMSClient(configuration);

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

			// Store 'Delivery Info Notifications' subscription id because we can later remove subscription with it:
			String subscriptionId = smsClient.getSMSMessagingClient().subscribeToDeliveryStatusNotifications(new SubscribeToDeliveryNotificationsRequest(senderAddress, notifyUrl, criteria, "", ""));
			
			// Send SMS 
			smsClient.getSMSMessagingClient().sendSMS(new SMSRequest(senderAddress, criteria + message, recipientAddress));      
		
			// Wait 30 seconds for 'Delivery Info Notification' push-es before removing subscription and closing the server connection 
            Thread.sleep(30000);

            // Remove 'Delivery Info Notifications' subscription
			smsClient.getSMSMessagingClient().removeDeliveryNotificationsSubscription(subscriptionId);

			// Remove 'Delivery Info Notifications' push listeners and stop the server
			smsClient.getSMSMessagingClient().removePushDeliveryStatusNotificationListeners();  

		} catch (Exception e) {  
			System.out.println(e.getMessage());
		}
	}
}
