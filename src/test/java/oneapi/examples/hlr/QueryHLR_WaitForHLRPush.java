package oneapi.examples.hlr;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.HLRNotificationsListener;
import oneapi.model.RoamingNotification;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/infobip
 *
 *  2.) Open 'examples.QueryHLR_WaitForHLRPush' class to edit where you should populate the following fields: 
 *		'DESTINATION'   'USERNAME'    
 *		'NOTIFY_URL' 'PASSWORD'         
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'HLR Notifications' push server is started automatically by adding 'HLRNotificationsListener'
 *  using the 'AddPushHlrNotificationsListener' method. Default server port is 3002 and it can be changed by set the 
 *  'Configuration' property 'HlrPushServerSimulatorPort'. 
 **/

public class QueryHLR_WaitForHLRPush {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");;
	private static String NOTIFY_URL = "http://127.0.0.1:3002/"; // 3002=Default port for 'HLR Notifications' server simulator

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();


		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

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
		smsClient.getHLRClient().queryHLR(DESTINATION, NOTIFY_URL);
		// ----------------------------------------------------------------------------------------------------

		// Wait 30 seconds for 'HLR Notification' push-es before closing the server connection 
		Thread.sleep(30000);

		// Remove 'HLR Notification' push listeners and stop the server
		smsClient.getHLRClient().removePushHLRNotificationsListeners();
	}
}

