package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;

public class QueryHLRUsingNotifyUrlExample {
	
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getHLRClient().queryHLR("11111111111111111111111111", "http://127.0.0.1:8181/dr/");
	}
}
