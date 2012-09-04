package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.LoginResponse;


import java.util.Arrays;

public class GetDeliveryNotificationsSubscriptionsExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        //Login user
        LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
        if (loginResponse.isVerified() == false)
        {
            System.out.println("User is not verified!");
            return;
        }
        
        DeliveryReportSubscription[] deliveryReportSubscriptions = smsClient.getSMSMessagingClient().getDeliveryNotificationsSubscriptions();
        System.out.println(Arrays.toString(deliveryReportSubscriptions));

    }
}
