package oneapi.examples.ussd;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.USSDRequest;

public class SendUSSDExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getUSSDClient().sendUSSD(new USSDRequest("11111111111111111111111111", "Hi"));
	}
}
