package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.DeliveryInfoNotification;

public class ConvertJsonToDeliveryInfoNotificationExample {
	private static final String JSON = "{\"deliveryInfoNotification\":{\"deliveryInfo\":{\"address\":\"38454234234\",\"deliveryStatus\":\"DeliveredToTerminal\"},\"callbackData\":\"\"}}";

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		SMSClient smsClient = new SMSClient(configuration);

		DeliveryInfoNotification deliveryInfoNotification = smsClient.getSMSMessagingClient().convertJsonToDeliveryInfoNotification(JSON);
		System.out.println(deliveryInfoNotification);
	}
}
