package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SubscribeToInboundMessagesRequest;

public class SubscribeToInboundMessagesNotificationsExample {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration("user1", "neki_password1");
        SMSClient smsClient = new SMSClient(configuration);

        String subscriptionId = 
				smsClient.getSMSMessagingClient().subscribeToInboundMessagesNotifications(new SubscribeToInboundMessagesRequest("Sender", "http://notify"));

		System.out.println(subscriptionId);
    }
}
