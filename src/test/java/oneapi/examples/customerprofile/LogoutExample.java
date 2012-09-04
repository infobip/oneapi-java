package oneapi.examples.customerprofile;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;


public class LogoutExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);
       
        smsClient.getCustomerProfileClient().logout();
    }
}
