package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.DeliveryReportSubscription;
import java.util.Arrays;

public class GetDeliveryNotificationsSubscriptionsExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        DeliveryReportSubscription[] deliveryReportSubscriptions = smsClient.getSMSMessagingClient().getDeliveryNotificationsSubscriptions();
        System.out.println(Arrays.toString(deliveryReportSubscriptions));

    }
}
