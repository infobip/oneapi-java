package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessageList;
import oneapi.model.common.LoginResponse;


public class GetInboundMessagesExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

        LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
        if (loginResponse.isVerified() == false)
        {
            System.out.println("User is not verified!");
            return;
        }
		
		InboundSMSMessageList inboundSMSMessageList =  smsClient.getSMSMessagingClient().getInboundMessages();
		System.out.println(inboundSMSMessageList);
	}
}
