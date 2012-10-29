package oneapi.examples.ussd;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessage;


/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'examples.SendUSSD' class to edit where you should populate the following fields: 
 *		'DESTINATION'    	'MESSAGE'
 *		'USERNAME'                     
 *		'PASSWORD'           
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class SendUSSD {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static final String MESSAGE = "You language of choice?\n1. Java\n2. .NET"; 
	
	public static void main(String[] args) {
		
		// Configure logger
		BasicConfigurator.configure();
		

		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

		String response = null;
		while(response == null || !"1".equals(response)) {
			//Send USSD and wait for the answer
			InboundSMSMessage inboundMessage = smsClient.getUSSDClient().sendMessage(DESTINATION, MESSAGE);
			response = inboundMessage.getMessage();
		}

		// Send message and stop USSD session
		smsClient.getUSSDClient().stopSession(DESTINATION, "Cool");
	}
}
