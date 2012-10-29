package oneapi.examples.async;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

public class SendSMSAsyncExample {

	public static void main(String[] args) {

		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getSMSMessagingClient().sendSMSAsync(new SMSRequest("Sender", "Hi", "11111111111111111111111111"), new ResponseListener<SendMessageResult>() {

			@Override
			public void onGotResponse(SendMessageResult sendMessageResult, Throwable error) {
				if (error == null) {
					System.out.println(sendMessageResult);
				} else {
					System.out.println(error.getMessage());
				}
			}
		});
	}
}

