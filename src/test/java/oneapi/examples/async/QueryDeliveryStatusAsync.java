package oneapi.examples.async;

import org.apache.log4j.BasicConfigurator;
import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.common.DeliveryInfoList;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/infobip
 *
 *  2.) Open 'examples.QueryDeliveryStatusAsync' class to edit where you should populate the following fields: 
 *		'USERNAME'   'SENDER'
 *		'PASSWORD'   'REQUEST_ID'
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class QueryDeliveryStatusAsync {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
	private static final String REQUEST_ID = "FILL REQUEST ID!!!"; 

	public static void main(String[] args) {

		// Configure logger
		BasicConfigurator.configure();


		Configuration configuration = new Configuration(USERNAME, PASSWORD);
		SMSClient smsClient = new SMSClient(configuration);

		smsClient.getSMSMessagingClient().queryDeliveryStatusAsync(SENDER, REQUEST_ID, new ResponseListener<DeliveryInfoList>() {

			@Override
			public void onGotResponse(DeliveryInfoList deliveryInfoList, Throwable error) {
				if (error == null) {
					System.out.println(deliveryInfoList);
				} else {
					System.out.println(error.getMessage());
				}				
			}
		});
		
	}
}

