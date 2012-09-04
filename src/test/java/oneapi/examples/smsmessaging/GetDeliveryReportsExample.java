package oneapi.examples.smsmessaging;

import java.util.Arrays;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.DeliveryReport;
import oneapi.model.common.LoginResponse;


public class GetDeliveryReportsExample {

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
		
		DeliveryReport[] deliveryReports =  smsClient.getSMSMessagingClient().getDeliveryReports();
		System.out.println(Arrays.toString(deliveryReports));
	}
}
