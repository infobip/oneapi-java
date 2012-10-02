package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.DeliveryReportList;

public class GetDeliveryReportsExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
		SMSClient smsClient = new SMSClient(configuration);

		DeliveryReportList deliveryReportList =  smsClient.getSMSMessagingClient().getDeliveryReports();
		System.out.println(deliveryReportList);
	}
}
