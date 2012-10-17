package oneapi.examples.ussd;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessage;

public class SendMessageExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "neki_password1");
		SMSClient smsClient = new SMSClient(configuration);

		InboundSMSMessage inboundSMSMessage = null;
		String message  = null;
		while (message != "1" ) {
			inboundSMSMessage  = smsClient.getUSSDClient().sendMessage("11111111111111111111111111", "You favourite color is\n 1. Blue\n2. Red");
		    message = inboundSMSMessage.getMessage();
		}
		
		System.out.println(message);
		
		smsClient.getUSSDClient().stopSession("11111111111111111111111111", "Nice, mine too!");
	}
}
