package oneapi.examples.hlr;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.RoamingNotification;

public class ConvertJsonToHLRNotificationExample {
	private static final String JSON = "{\"terminalRoamingStatusList\":{\"roaming\":{\"address\":\"45534534\",\"currentRoaming\":null,\"servingMccMnc\":{\"mcc\":\"219\",\"mnc\":\"02\"},\"resourceURL\":null,\"retrievalStatus\":\"Error\",\"extendedData\":{\"destinationAddress\":\"54353\",\"statusId\":5,\"doneTime\":1343893501000,\"pricePerMessage\":5.0,\"mccMnc\":\"21902\",\"servingMsc\":\"543553\",\"censoredServingMsc\":\"5345\",\"gsmErrorCode\":0,\"originalNetworkName\":\"VIP-NET\",\"portedNetworkName\":\"TELE2\",\"servingHlr\":\"5435\",\"imsi\":\"219020000627769\",\"originalNetworkPrefix\":\"91\",\"originalCountryPrefix\":\"385\",\"originalCountryName\":\"Croatia                                           \",\"isNumberPorted\":true,\"portedNetworkPrefix\":\"95\",\"portedCountryPrefix\":\"385\",\"portedCountryName\":\"Croatia                                           \",\"numberInRoaming\":false},\"callbackData\":null}}}";

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		SMSClient smsClient = new SMSClient(configuration);

		RoamingNotification roamingNotification = smsClient.getHLRClient().convertJsonToHLRNotificationExample(JSON);
		System.out.println(roamingNotification);
	}
}
