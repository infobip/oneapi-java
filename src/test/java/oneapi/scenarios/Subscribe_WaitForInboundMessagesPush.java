package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageNotificationsListener;
import oneapi.model.SubscribeToInboundMessagesRequest;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.Subscribe_WaitForInboundMessagesPush' class to edit where you should populate the following fields: 
 *		'destinationAddress'    'notificationFormat'
 *		'username'              'notifyUrl'           
 *		'password'              'criteria' 
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Inbound Message Notifications' push server is started automatically by adding 'InboundMessageNotificationsListener'
 *  using the 'addPushInboundMessageListener' method. Default server port is 3001 and it can be changed by set the 
 *  'Configuration' property 'InboundMessagesPushServerSimulatorPort'. Used port should match the one used in the 'notifyUrl' property when 
 *  subscribing for the notifications using the 'SubscribeToInboundMessagesNotifications' method.
 **/

public class Subscribe_WaitForInboundMessagesPush {

	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String destinationAddress = "";
	private static String notifyUrl = ""; //e.g. "http://127.0.0.1:3001/" 3001=Default port for 'Inbound Messages Notifications' server simulator
	private static String criteria = "";
	private static String notificationFormat = "JSON"; 

	public static void main(String[] args) {
		
		try 
		{
			// Initialize Configuration object 
			Configuration configuration = new Configuration(username, password);

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
            String subscriptionId = smsClient.getSMSMessagingClient().subscribeToInboundMessagesNotifications(new SubscribeToInboundMessagesRequest(destinationAddress, notifyUrl, criteria, notificationFormat, "", ""));
           
            // Wait 30 seconds for 'Inbound Message Notification' push-es before removing subscription and closing the server connection 
            Thread.sleep(30000);

            // Remove 'Inbound Message Notifications' subscription
            smsClient.getSMSMessagingClient().removeInboundMessagesSubscription(subscriptionId);
			
			// Remove 'Inbound Message Notifications' push listeners and stop the server
            smsClient.getSMSMessagingClient().removePushInboundMessageListeners();   

		} catch (Exception e) {  
			System.out.println(e.getMessage());
		}
	}
}
