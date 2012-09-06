package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.DeliveryInfoList;
import oneapi.model.common.LoginResponse;


public class QueryDeliveryStatusExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);
		
        LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
        if (loginResponse.isVerified() == false)
        {
            System.out.println("User is not verified!");
            return;
        }
		
		DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus("Sender", "A13SDF");
		System.out.println(deliveryInfoList);	
	}
}
