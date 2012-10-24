package oneapi.examples.smsmessaging;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryReportListener;
import oneapi.model.DeliveryReportList;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

public class DeliveryReportsRetrieverExample {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

		smsClient.getSMSMessagingClient().addPullDeliveryReportListener(new DeliveryReportListener() {
			@Override
			public void onDeliveryReportReceived(DeliveryReportList deliveryReportList, Throwable error) {
                System.out.println(deliveryReportList);
			}
		});

        sendSms(smsClient, "Sender", "Hello", "11111111111111111111111111");
        sendSms(smsClient, "Sender2", "Hello2", "11111111111111111111111112");
        sendSms(smsClient, "Sender3", "Hello3", "11111111111111111111111113");
    }

    private static void sendSms(SMSClient smsClient, String sender, String message, String destination) {
        SendMessageResult response = smsClient.getSMSMessagingClient().sendSMS(new SMSRequest(sender, message, destination));
        System.out.println(response);
    }
}
