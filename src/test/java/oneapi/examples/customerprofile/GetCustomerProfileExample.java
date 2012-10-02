package oneapi.examples.customerprofile;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.CustomerProfile;

public class GetCustomerProfileExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        CustomerProfile customerProfile = smsClient.getCustomerProfileClient().getCustomerProfile();
        System.out.println(customerProfile);
    }
}
