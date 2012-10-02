package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.DeliveryReportList;
import oneapi.model.SMSRequest;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi C# library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_GetDeliveryReports' class to edit where you should populate the following fields: 
 *		'senderAddress'    'username'
 *		'message'          'password' 
 *		'recipientAddress'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application'     
 **/

public class SendSMS_GetDeliveryReports {
	
	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String senderAddress = "";
	private static String message = "";
	private static String recipientAddress = "";

	public static void main(String[] args) {
		
		try
		{
			// Initialize Configuration object 
			Configuration configuration = new Configuration(username, password);

			// Initialize SMSClient using the Configuration object
			SMSClient smsClient = new SMSClient(configuration);
			
			// Send SMS 
			smsClient.getSMSMessagingClient().sendSMS(new SMSRequest(senderAddress, message, recipientAddress));
			
			// Wait for 30 seconds to give enought time for the message to be delivered
			Thread.sleep(30000);

			// Get 'Delivery Reports'
			DeliveryReportList deliveryReportList = smsClient.getSMSMessagingClient().getDeliveryReports();
			System.out.println(deliveryReportList);

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}   
	}
}
