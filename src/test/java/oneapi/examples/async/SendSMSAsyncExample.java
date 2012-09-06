package oneapi.examples.async;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.SMSRequest;
import oneapi.model.common.LoginResponse;

public class SendSMSAsyncExample {

	public static void main(String[] args) {

		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
		if (loginResponse.isVerified() == false)
		{
			System.out.println("User is not verified!");
			return;
		}

		smsClient.getSMSMessagingClient().sendSMSAsync(new SMSRequest("Sender", "Hi", "11111111111111111111111111"), new ResponseListener<String>() {

			@Override
			public void onGotResponse(String requestd, Throwable error) {
				if (error == null) {
					System.out.println(requestd);
				} else {
					System.out.println(error.getMessage());
				}
			}
		});
	}
}

