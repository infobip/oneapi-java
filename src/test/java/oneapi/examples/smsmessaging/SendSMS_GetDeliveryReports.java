package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.DeliveryReportList;
import oneapi.model.SMSRequest;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/infobip
 *
 *  2.) Open 'examples.SendSMS_GetDeliveryReports' class to edit where you should populate the following fields: 
 *		'SENDER'    'USERNAME'
 *		'MESSAGE'   'PASSWORD' 
 *		'DESTINATION'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application'     
 **/

public class SendSMS_GetDeliveryReports {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static final String MESSAGE = PropertyLoader.loadProperty("example.properties", "message");

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();

		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

		// Send SMS
        SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);

		smsClient.getSMSMessagingClient().sendSMS(smsRequest);

		// Wait for 30 seconds to give enought time for the message to be delivered
		Thread.sleep(30000);

		// Get 'Delivery Reports'
		DeliveryReportList deliveryReportList = smsClient.getSMSMessagingClient().getDeliveryReports();
		System.out.println(deliveryReportList);
		
	}
}
