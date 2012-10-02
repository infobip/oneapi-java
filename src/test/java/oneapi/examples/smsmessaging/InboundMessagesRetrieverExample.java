package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.InboundMessageListener;
import oneapi.model.common.InboundSMSMessageList;

public class InboundMessagesRetrieverExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getSMSMessagingClient().addPullInboundMessageListener(new InboundMessageListener() {		
			@Override
			public void onMessageRetrieved(InboundSMSMessageList inboundSMSMessageList, Throwable error) {
				System.out.println(inboundSMSMessageList);
			}
		});
	}
}
