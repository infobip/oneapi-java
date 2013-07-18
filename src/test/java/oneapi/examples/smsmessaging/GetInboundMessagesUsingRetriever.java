package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageListener;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at www.github.com/infobip
 *
 *  2.) Open 'examples.GetInboundMessagesUsingRetriever' class to edit where you should populate the following fields: 
 *		'USERNAME'
 *		'PASSWORD'
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Inbound Messages' are retrieved default every 5000 milisecons (5 seconds). Retrieving interval can be changed
 *        by setting the 'Configuration' property 'InboundMessagesRetrievingInterval'.
 **/

public class GetInboundMessagesUsingRetriever {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();


		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

		// Add listener(start retriever and pull 'Inbound Messages')   
		smsClient.getSMSMessagingClient().addPullInboundMessageListener(new InboundMessageListener() {
			@Override
			public void onMessageRetrieved(InboundSMSMessageList inboundSMSMessageList, Throwable error) {
				// Handle pulled 'Inbound Messages'
				if (error == null) 
				{
					System.out.println(inboundSMSMessageList);
				} else {
					System.out.println(error.getMessage());
				}  	
			}	
		});

		// Wait 30 seconds for the 'Inbound Messages' before stop the retriever
		Thread.sleep(30000);

		// Remove 'Inbound Messages' pull listeners and stop the retriever
		smsClient.getSMSMessagingClient().removePullInboundMessageListeners();

	}
}
