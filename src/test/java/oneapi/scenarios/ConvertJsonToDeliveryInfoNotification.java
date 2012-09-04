package oneapi.scenarios;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.DeliveryInfoNotification;

/**
 * To run this example follow these 3 steps:
 *
 *  1.) Download 'OneApi Java library' - available at github.com/parseco
 *
 *  2.) Open 'scenarios.ConvertJsonToDeliveryInfoNotificationExample' class    
 *		
 *  3.) Run the example class by right click it and select 'Run As -> Java Application' 
 **/

public class ConvertJsonToDeliveryInfoNotification {
	// Pushed 'Delivery Info Notification' JSON example
	private static final String JSON = "{\"deliveryInfoNotification\":{\"deliveryInfo\":{\"address\":\"38454234234\",\"deliveryStatus\":\"DeliveredToTerminal\"},\"callbackData\":\"\"}}";

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		SMSClient smsClient = new SMSClient(configuration);

		// example:on-delivery-notification
		DeliveryInfoNotification deliveryInfoNotification = smsClient.getSMSMessagingClient().convertJsonToDeliveryInfoNotification(JSON);
	    // ----------------------------------------------------------------------------------------------------
		System.out.println(deliveryInfoNotification);
	}
}
