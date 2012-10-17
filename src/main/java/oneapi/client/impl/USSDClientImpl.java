package oneapi.client.impl;

import oneapi.client.USSDClient;
import oneapi.config.Configuration;
import oneapi.model.RequestData;
import oneapi.model.USSDRequest;
import oneapi.model.RequestData.Method;

public class USSDClientImpl extends OneAPIBaseClientImpl implements USSDClient {

	private static final String USSD_URL_BASE = "/ussd/outbound";
	
	public USSDClientImpl(Configuration configuration) {
		super(configuration);
	}

	/**
	 * Send an USSD over OneAPI to one  mobile terminal using the customized 'USSDRequest' object
	 * @param ussdRequest (mandatory) object containing data needed to be filled in order to send the USSD
	 */
	@Override
	public void sendUSSD(USSDRequest ussdRequest) {
		RequestData requestData = new RequestData(USSD_URL_BASE, RESPONSE_CODE_200_OK, Method.POST);
		requestData.setFormParams(ussdRequest);
		requestData.setContentType(URL_ENCODED_CONTENT_TYPE);
		executeMethod(requestData);      
	}
}
