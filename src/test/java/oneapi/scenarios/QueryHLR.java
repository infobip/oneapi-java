package oneapi.scenarios;

import org.apache.log4j.BasicConfigurator;
import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.Roaming;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.QueryHLR' class to edit where you should populate the following fields: 
 *		'DESTINATION'   'PASSWORD'    
 *		'USERNAME'          
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class QueryHLR {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");;

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();


		// example:data-connection-client
		Configuration configuration = new Configuration(USERNAME, PASSWORD);
		SMSClient smsClient = new SMSClient(configuration);
		// ----------------------------------------------------------------------------------------------------

		// example:retrieve-roaming-status
		Roaming roaming = smsClient.getHLRClient().queryHLR(DESTINATION);
		// ----------------------------------------------------------------------------------------------------
		System.out.println(roaming);

	}
}

