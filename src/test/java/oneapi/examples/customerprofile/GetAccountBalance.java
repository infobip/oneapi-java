package oneapi.examples.customerprofile;

import org.apache.log4j.BasicConfigurator;
import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.AccountBalance;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/infobip
 *
 *  2.) Open 'examples.GetAccountBalance' class to edit where you should populate the following fields: 
 *		'USERNAME'
 *		'PASSWORD' 
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class GetAccountBalance {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	
	public static void main(String[] args) throws Exception {
		
		// Configure logger
		BasicConfigurator.configure();
		
		
		Configuration configuration = new Configuration(USERNAME, PASSWORD);
		SMSClient smsClient = new SMSClient(configuration);

		AccountBalance accountBalance = smsClient.getCustomerProfileClient().getAccountBalance();
		System.out.println(accountBalance);
		
	}
}
