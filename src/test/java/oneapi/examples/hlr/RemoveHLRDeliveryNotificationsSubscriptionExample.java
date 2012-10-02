package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;

public class RemoveHLRDeliveryNotificationsSubscriptionExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getHLRClient().removeHLRDeliveryNotificationsSubscription("AEFGRE");
	}
}
