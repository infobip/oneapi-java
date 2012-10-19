package oneapi.scenarios;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryStatusNotificationsListener;
import oneapi.model.DeliveryInfoNotification;
import oneapi.model.SMSRequest;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_WaitForDeliveryStatusPush' class to edit where you should populate the following fields: 
 *		'SENDER		  '     'NOTIFY_URL'   'USERNAME'
 *		'MESSAGE'           'PASSWORD'        
 *		'DESTINATION'   
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Delivery Status Notifications' push server is started automatically by adding 'DeliveryStatusNotificationsListener'
 *  using the 'addPushDeliveryStatusNotificationListener' method. Default server port is 3000 and it can be changed by set the 
 *  'Configuration' property 'setDlrStatusPushServerSimulatorPort'. 
 *  Used port should match the one used in the 'SMSRequest' 'notifyUrl' parameter.
 **/

public class SendSMS_WaitForDeliveryStatusPush {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static final String MESSAGE = "Hello"; 
	private static final String NOTIFY_URL = "http://127.0.0.1:3000/"; // 3000=Default port for 'Delivery Info Notifications' server simulator

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();
		
		
		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

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

		// example:prepare-message-with-notify-url
		SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);
		// The url where the delivery notification will MESSAGE pushed:
		smsRequest.setNotifyURL(NOTIFY_URL); 
		// ----------------------------------------------------------------------------------------------------

		// Send SMS 
		smsClient.getSMSMessagingClient().sendSMS(smsRequest);      

		// Wait 30 seconds for 'Delivery Info Notification' push-es before closing the server connection 
		Thread.sleep(30000);

		// Remove 'Delivery Info Notifications' push listeners and stop the server
		smsClient.getSMSMessagingClient().removePushDeliveryStatusNotificationListeners();  

	}
}
