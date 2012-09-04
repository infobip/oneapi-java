package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.LoginResponse;


public class QueryHLRUsingNotifyUrlExample {
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
		
		smsClient.getHLRClient().queryHLR("11111111111111111111111111", "http://127.0.0.1:8181/dr/");
	}

}
