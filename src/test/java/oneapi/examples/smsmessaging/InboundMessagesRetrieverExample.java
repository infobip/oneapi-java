package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageListener;
import oneapi.model.common.InboundSMSMessageList;
import oneapi.model.common.LoginResponse;


public class InboundMessagesRetrieverExample {

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
		
		smsClient.getSMSMessagingClient().addPullInboundMessageListener(new InboundMessageListener() {		
			@Override
			public void onMessageRetrieved(InboundSMSMessageList smsMessageList, Throwable error) {
				System.out.println(smsMessageList);
			}
		});
	}
}
