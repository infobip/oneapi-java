package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

public class SendSMSExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		SendMessageResult response = smsClient.getSMSMessagingClient().sendSMS(new SMSRequest("Sender", "Hi", "11111111111111111111111111"));
		System.out.println(response);
	}
}
