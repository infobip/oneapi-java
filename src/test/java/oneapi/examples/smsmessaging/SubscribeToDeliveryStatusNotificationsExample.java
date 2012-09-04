package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.*;
import oneapi.model.common.LoginResponse;


public class SubscribeToDeliveryStatusNotificationsExample {

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
        
        String subscriptionId = smsClient.getSMSMessagingClient().subscribeToDeliveryStatusNotifications(new SubscribeToDeliveryNotificationsRequest("http://127.0.0.1:8181/dr/"));
        System.out.println(subscriptionId);

    }
}
