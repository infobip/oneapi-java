package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'examples.GetInboundMessages' class to edit where you should populate the following fields: 
 *		'USERNAME'
 *		'PASSWORD'
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class GetInboundMessages {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();


		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

		// example:retrieve-inbound-messages
		InboundSMSMessageList inboundSMSMessageList =  smsClient.getSMSMessagingClient().getInboundMessages();
		// ---------------------------------------------------------------------------------------------------- 
		System.out.println(inboundSMSMessageList);
	}
}
