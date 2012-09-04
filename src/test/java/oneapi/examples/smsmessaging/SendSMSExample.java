package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.common.LoginResponse;


public class SendSMSExample {

    public static void main(String[] args) throws Exception {
    	 Configuration configuration = new Configuration("FILL USERNAME HERE", "FILL PASSWORD HERE");
         SMSClient smsClient = new SMSClient(configuration);

         //Login user
         LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
         if (loginResponse.isVerified() == false)
         {
             System.out.println("User is not verified!");
             return;
         }
         
         String requestId = smsClient.getSMSMessagingClient().sendSMS(new SMSRequest("Sender", "TestCriteria1 Hello", "385997701356"));
         System.out.println(requestId);
    }
}
