package oneapi.examples.async;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.DeliveryReportList;
import oneapi.model.common.LoginResponse;

public class GetDeliveryReportsAsyncExample {

	public static void main(String[] args) {

		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
		if (loginResponse.isVerified() == false)
		{
			System.out.println("User is not verified!");
			return;
		}

		smsClient.getSMSMessagingClient().getDeliveryReportsAsync(new ResponseListener<DeliveryReportList>() {

			@Override
			public void onGotResponse(DeliveryReportList deliveryReportList, Throwable error) {
				if (error == null) {
					System.out.println(deliveryReportList);
				} else {
					System.out.println(error.getMessage());
				}
			}
		});
	}
}

