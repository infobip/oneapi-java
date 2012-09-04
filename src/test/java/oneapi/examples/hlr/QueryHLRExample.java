package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.LoginResponse;
import oneapi.model.common.Roaming;


public class QueryHLRExample {
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
		
		Roaming roaming = smsClient.getHLRClient().queryHLR("11111111111111111111111111");
		System.out.println(roaming);
	}
}
