package oneapi.examples.async;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.common.LoginResponse;
import oneapi.model.common.Roaming;

public class QueryHLRAsyncExample {

	public static void main(String[] args) {

		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
		if (loginResponse.isVerified() == false)
		{
			System.out.println("User is not verified!");
			return;
		}

		smsClient.getHLRClient().queryHLRAsync("11111111111111111111111111", new ResponseListener<Roaming>() {

			@Override
			public void onGotResponse(Roaming roaming, Throwable error) {
				if (error == null) {
					System.out.println(roaming);
				} else {
					System.out.println(error.getMessage());
				}				
			}
		});
	}
}

