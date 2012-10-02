package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.common.DeliveryInfoList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi C# library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_CheckDeliveryStatus' class to edit where you should populate the following fields: 
 *		'senderAddress'    'username'
 *		'message'          'password' 
 *		'recipientAddress'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application'     
 **/

public class SendSMS_CheckDeliveryStatus {
	
	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String senderAddress = "";
	private static String message = "";
	private static String recipientAddress = "";

	public static void main(String[] args) {

		try
		{
			// example:initialize-sms-client
			Configuration configuration = new Configuration(username, password);
			SMSClient smsClient = new SMSClient(configuration);
			// ----------------------------------------------------------------------------------------------------
			
			// example:prepare-message-without-notify-url
			SMSRequest smsRequest = new SMSRequest(senderAddress, message, recipientAddress);
			// ----------------------------------------------------------------------------------------------------

			// example:send-message
			// Store request id because we can later query for the delivery status with it:
			String requestId = smsClient.getSMSMessagingClient().sendSMS(smsRequest);
			// ----------------------------------------------------------------------------------------------------
		
			// Few seconds later we can check for the sending status   
			Thread.sleep(10000);

			// example:query-for-delivery-status
			DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus(senderAddress, requestId);
			String deliveryStatus = deliveryInfoList.getDeliveryInfo().get(0).getDeliveryStatus();
			// ----------------------------------------------------------------------------------------------------
			System.out.println(deliveryStatus);
				
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}   
	}
}
