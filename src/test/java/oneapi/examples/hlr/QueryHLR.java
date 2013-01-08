package oneapi.examples.hlr;

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
 *  2.) Open 'examples.QueryHLR' class to edit where you should populate the following fields: 
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
        System.out.println("HLR result:");
        System.out.println("servingMccMnc: "+roaming.getServingMccMnc()); 
        System.out.println("address: "+roaming.getAddress()); 
        System.out.println("currentRoaming: "+roaming.getCurrentRoaming()); 
        System.out.println("resourceURL: "+roaming.getResourceURL()); 
        System.out.println("retrievalStatus: "+roaming.getRetrievalStatus()); 
        System.out.println("callbackData: "+roaming.getCallbackData()); 
        System.out.println("extendedData: "+roaming.getExtendedData()); 
        System.out.println("IMSI: "+roaming.getExtendedData().getImsi()); 
        System.out.println("destinationAddres: "+roaming.getExtendedData().getDestinationAddress()); 
        System.out.println("originalNetworkPrefix: "+roaming.getExtendedData().getOriginalNetworkPrefix()); 
        System.out.println("portedNetworkPrefix: "+roaming.getExtendedData().getPortedNetworkPrefix()); 
		// ----------------------------------------------------------------------------------------------------


	}
}

