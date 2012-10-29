package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;
import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'examples.SendSMS_ToMultipleRecipients' class to edit where you should populate the following fields: 
 *		'SENDER'    'USERNAME'
 *		'MESSAGE'   'PASSWORD' 
 *		'DESTINATION'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application'     
 **/

public class SendSMS_ToMultipleRecipients {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static final String MESSAGE = "Hello"; 

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();


		Configuration configuration = new Configuration(USERNAME, PASSWORD);
		SMSClient smsClient = new SMSClient(configuration);

		String[] address = new String[2];
		address[0] = DESTINATION;
		address[1] = DESTINATION;

		SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, address);

		SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);
		System.out.println(sendMessageResult);

	}
}
