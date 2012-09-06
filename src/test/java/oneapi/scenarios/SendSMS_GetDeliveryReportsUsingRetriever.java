package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryReportListener;
import oneapi.model.DeliveryReportList;
import oneapi.model.SMSRequest;
import oneapi.model.common.LoginResponse;


/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.SendSMS_GetDeliveryReportsUsingRetriever' class to edit where you should populate the following fields: 
 *		'senderAddress' 'username' 
 *	    'message'       'password'
 *		'recipientAddress'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Delivery Reports' are retrieved default every 5000 milisecons (5 seconds). Retrieving interval can be changed
 *  	   by setting the 'Configuration' property 'DlrRetrievingInterval'.
 **/

public class SendSMS_GetDeliveryReportsUsingRetriever {

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

			// Login sms client
			LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();
			if (loginResponse.isVerified() == false)
			{
				System.out.println("User is not verified!");
				return;
			}

			 // Add listener(start retriever and pull 'Delivery Reports')   
			smsClient.getSMSMessagingClient().addPullDeliveryReportListener(new DeliveryReportListener() {
				@Override
				public void onDeliveryReportReceived(DeliveryReportList deliveryReportList, Throwable error) {
					//Handle pulled 'Delivery Reports'
					if (error == null) {
						System.out.println(deliveryReportList);
					} else {
						System.out.println(error.getMessage());
					}			
				}
			});

			// Send SMS 
			smsClient.getSMSMessagingClient().sendSMS(new SMSRequest(senderAddress, message, recipientAddress));
			
			// Wait 30 seconds for the 'Delivery Reports' before stop the retriever  
            Thread.sleep(30000);

			// Remove 'Delivery Reports' pull listeners and stop the retriever
			smsClient.getSMSMessagingClient().removePullDeliveryReportListeners();
			
			  // Logout sms client
			smsClient.getCustomerProfileClient().logout();

		} catch (Exception e) {  
			System.out.println(e.getMessage());
		}
	}
}
