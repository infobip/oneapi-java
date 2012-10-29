package oneapi.examples.smsmessaging;

import org.apache.log4j.BasicConfigurator;

import oneapi.PropertyLoader;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.DeliveryReportListener;
import oneapi.model.DeliveryReportList;
import oneapi.model.SMSRequest;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'examples.SendSMS_GetDeliveryReportsUsingRetriever' class to edit where you should populate the following fields: 
 *		'SENDER'  	  'USERNAME' 
 *	    'MESSAGE'	  'PASSWORD'
 *		'DESTINATION'	
 *
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 *
 *  Note: 'Delivery Reports' are retrieved default every 5000 milisecons (5 seconds). Retrieving interval can be changed
 *  	   by setting the 'Configuration' property 'DlrRetrievingInterval'.
 **/

public class SendSMS_GetDeliveryReportsUsingRetriever {

	// ----------------------------------------------------------------------------------------------------
	// TODO: Fill you own values here or create/change the example.properties file:
	// ----------------------------------------------------------------------------------------------------

	private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
	private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
	private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
	private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
	private static final String MESSAGE = "Hello"; 

	public static void main(String[] args) throws Exception {

		// Configure logger
		BasicConfigurator.configure();


		// Initialize Configuration object 
		Configuration configuration = new Configuration(USERNAME, PASSWORD);

		// Initialize SMSClient using the Configuration object
		SMSClient smsClient = new SMSClient(configuration);

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
		smsClient.getSMSMessagingClient().sendSMS(new SMSRequest(SENDER, MESSAGE, DESTINATION));

		// Wait 30 seconds for the 'Delivery Reports' before stop the retriever  
		Thread.sleep(30000);

		// Remove 'Delivery Reports' pull listeners and stop the retriever
		smsClient.getSMSMessagingClient().removePullDeliveryReportListeners();

	}
}
