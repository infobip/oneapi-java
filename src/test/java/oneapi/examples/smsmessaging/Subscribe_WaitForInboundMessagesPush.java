package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageNotificationsListener;
import oneapi.model.SubscribeToInboundMessagesRequest;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/infobip
 *
 *  2.) Open 'examples.Subscribe_WaitForInboundMessagesPush' class to edit where you should populate the following fields: 
 *		'DESTINATION'    	'NOTIFICATION_FORMAT'
 *		'USERNAME'          'NOTIFY_URL'           
 *		'PASSWORD'          'CRITERIA' 
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Inbound Message Notifications' push server is started automatically by adding 'InboundMessageNotificationsListener'
 *  using the 'addPushInboundMessageListener' method. Default server port is 3001 and it can be changed by set the 
 *  'Configuration' property 'InboundMessagesPushServerSimulatorPort'. Used port should match the one used in the 'notifyUrl' property when 
 *  subscribing for the notifications using the 'SubscribeToInboundMessagesNotifications' method.
 **/

public class Subscribe_WaitForInboundMessagesPush {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static String NOTIFY_URL = "http://127.0.0.1:3000/"; // 3000=Default port for 'Delivery Info Notifications' server simulator
	private static String CRITERIA = "SomeCriteria";
	private static String NOTIFICATION_FORMAT = "JSON"; 

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();
		

		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

		// Add listener(start push server and wait for the 'Inbound Message Notifications')    
		smsClient.getSMSMessagingClient().addPushInboundMessageListener(new InboundMessageNotificationsListener() {
			@Override
			public void onMessageReceived(InboundSMSMessageList inboundSMSMessageList) {
				// Handle pushed 'Inbound Message Notification'
				if (inboundSMSMessageList != null) 
				{
					System.out.println(inboundSMSMessageList);
				}  						
			}						
		});

		//Store 'Inbound Message Notifications' subscription id because we can later remove subscription with it:
		String subscriptionId = smsClient.getSMSMessagingClient().subscribeToInboundMessagesNotifications(new SubscribeToInboundMessagesRequest(DESTINATION, NOTIFY_URL, CRITERIA, NOTIFICATION_FORMAT, "", ""));

		// Wait 30 seconds for 'Inbound Message Notification' push-es before removing subscription and closing the server connection 
		Thread.sleep(30000);

		// Remove 'Inbound Message Notifications' subscription
		smsClient.getSMSMessagingClient().removeInboundMessagesSubscription(subscriptionId);

		// Remove 'Inbound Message Notifications' push listeners and stop the server
		smsClient.getSMSMessagingClient().removePushInboundMessageListeners();   

	}
}
