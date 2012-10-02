package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.MoSubscription;
import java.util.Arrays;

public class RetrieveInboundMessagesNotificationsSubscriptionsExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        MoSubscription[] moSubscriptions = smsClient.getSMSMessagingClient().getInboundMessagesNotificationsSubscriptions();
        System.out.println(Arrays.toString(moSubscriptions));

    }
}
