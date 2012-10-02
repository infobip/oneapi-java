package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.model.common.Roaming;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.QueryHLR' class to edit where you should populate the following fields: 
 *		'address'   'password'    
 *		'username'          
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class QueryHLR {
	
	private static String username = "FILL USERNAME HERE !!!";
	private static String password = "FILL PASSWORD HERE !!!";
	private static String address = "";
	
	public static void main(String[] args) {
		
		try
		{
			// example:data-connection-client
			Configuration configuration = new Configuration(username, password);
			SMSClient smsClient = new SMSClient(configuration);
			// ----------------------------------------------------------------------------------------------------

			// example:retrieve-roaming-status
		    Roaming roaming = smsClient.getHLRClient().queryHLR(address);
		    // ----------------------------------------------------------------------------------------------------
		    System.out.println(roaming);
					
		}
		catch (RequestException e)
		{
			System.out.println("Request Exception: " + e.getMessage());	
		}  
	}
}

