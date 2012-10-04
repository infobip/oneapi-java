package oneapi.examples.async;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.common.DeliveryInfoList;

public class QueryDeliveryStatusAsyncExample {

	public static void main(String[] args) {

		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getSMSMessagingClient().queryDeliveryStatusAsync("Sender", "A13SDF", new ResponseListener<DeliveryInfoList>() {

			@Override
			public void onGotResponse(DeliveryInfoList deliveryInfoList, Throwable error) {
				if (error == null) {
					System.out.println(deliveryInfoList);
				} else {
					System.out.println(error.getMessage());
				}				
			}
		});
	}
}
