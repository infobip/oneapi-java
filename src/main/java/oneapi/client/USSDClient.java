package oneapi.client;

import oneapi.model.USSDRequest;

public interface USSDClient {
	/**
	 * Send an USSD over OneAPI to one  mobile terminal using the customized 'USSDRequest' object
	 * @param ussdRequest (mandatory) object containing data needed to be filled in order to send the USSD
	 */
	void sendUSSD(USSDRequest ussdRequest);
}
