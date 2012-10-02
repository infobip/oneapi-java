package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageListener;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at www.github.com/parseco
 *
 *  2.) Open 'scenarios.GetInboundMessagesUsingRetriever' class to edit where you should populate the following fields: 
 *		'username'
 *		'password'
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Inbound Messages' are retrieved default every 5000 milisecons (5 seconds). Retrieving interval can be changed
 *        by setting the 'Configuration' property 'InboundMessagesRetrievingInterval'.
 **/

public class GetInboundMessagesUsingRetriever {

	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";

	public static void main(String[] args) {
		
		try {
			// Initialize Configuration object 
			Configuration configuration = new Configuration(username, password);

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
			
		} catch (Exception e) {  
			System.out.println(e.getMessage());
		}
	}
}
