package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.model.common.InboundSMSMessageList;
import oneapi.model.common.LoginResponse;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.GetInboundMessages' class to edit where you should populate the following fields: 
 *		'username'
 *		'password'
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class GetInboundMessages {

	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";

	public static void main(String[] args) {

		try {		
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

			// example:retrieve-inbound-messages
			InboundSMSMessageList inboundSMSMessageList =  smsClient.getSMSMessagingClient().getInboundMessages();
			// ---------------------------------------------------------------------------------------------------- 
			System.out.println(inboundSMSMessageList);

			// Logout sms client
			smsClient.getCustomerProfileClient().logout();

		} catch (RequestException e) {  
			System.out.println(e.getMessage());
		}
	}
}
