package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.*;

public class SubscribeToHLRDeliveryNotificationsExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        String subscriptionId  = smsClient.getHLRClient().subscribeToHLRDeliveryNotifications(new SubscribeToHLRDeliveryNotificationsRequest("http://127.0.0.1:8181/dr/"));
        System.out.println(subscriptionId);

    }
}
