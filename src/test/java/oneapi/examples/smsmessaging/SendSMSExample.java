package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;

public class SendSMSExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
         SMSClient smsClient = new SMSClient(configuration);

         String requestId = smsClient.getSMSMessagingClient().sendSMS(new SMSRequest("Sender", "Hi", "11111111111111111111111111"));
         System.out.println(requestId);
    }
}
