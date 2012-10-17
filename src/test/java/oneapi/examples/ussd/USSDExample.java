package oneapi.examples.ussd;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessage;

public class USSDExample {
	
	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------
	
	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration(USERNAME, PASSWORD);
		String destination = DESTINATION;
		
		SMSClient smsClient = new SMSClient(configuration);
		
		String response = null;
		while(response == null || !"1".equals(response)) {
			InboundSMSMessage inboundMessage = smsClient.getUSSDClient().sendMessage(destination, "You language of choice?\n1. Java\n2. .NET");
			
			response = inboundMessage.getMessage();
		}
		
		smsClient.getUSSDClient().stopSession(destination, "Cool");
	}
}
