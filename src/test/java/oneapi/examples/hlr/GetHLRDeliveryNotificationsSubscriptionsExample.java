package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.LoginResponse;

import java.util.Arrays;

public class GetHLRDeliveryNotificationsSubscriptionsExample {

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
        
        DeliveryReportSubscription[] deliveryReportSubscriptions = smsClient.getHLRClient().getHLRDeliveryNotificationsSubscriptionsById("AEFGH132");
        System.out.println(Arrays.toString(deliveryReportSubscriptions));
    }
}
