package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;

public class SendSMSToMultipleRecipientsExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		String[] address = new String[2];
		address[0] = "11111111111111111111111111";
		address[1] = "22222222222222222222222222";

		SMSRequest smsRequest = new SMSRequest("Sender", "Hi", address);

		String requestId = smsClient.getSMSMessagingClient().sendSMS(smsRequest);
		System.out.println(requestId);
	}
}
