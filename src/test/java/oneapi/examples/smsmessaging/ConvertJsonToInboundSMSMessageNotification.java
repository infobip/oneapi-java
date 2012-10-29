package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessageList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'examples.ConvertJsonToInboundSMSMessageNotificationExample' class    
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class ConvertJsonToInboundSMSMessageNotification {
	// Pushed 'Inbound Message Notification' JSON example
	private static final String JSON = "{\"inboundSMSMessage\":[{\"dateTime\":1343893501000,\"destinationAddress\":\"7567567657\",\"messageId\":null,\"message\":\"TestCriteria\",\"resourceURL\":null,\"senderAddress\":\"76756\",\"moSessionId\":0}],\"numberOfMessagesInThisBatch\":1,\"resourceURL\":null,\"totalNumberOfPendingMessages\":0,\"callbackData\":null}";

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		SMSClient smsClient = new SMSClient(configuration);

		// example:on-mo
		InboundSMSMessageList inboundSMSMessageList = smsClient.getSMSMessagingClient().convertJsonToInboundSMSMessageNotificationExample(JSON);           
		 // ----------------------------------------------------------------------------------------------------
		System.out.println(inboundSMSMessageList);
	}
}
