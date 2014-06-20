package oneapi.client.impl;

import oneapi.client.USSDClient;
import oneapi.config.Configuration;
import oneapi.model.RequestData;
import oneapi.model.USSDRequest;
import oneapi.model.RequestData.Method;
import oneapi.model.common.InboundSMSMessage;

public class USSDClientImpl extends OneAPIBaseClientImpl implements USSDClient {

	private static final String USSD_URL_BASE = "/ussd/outbound";
	
	public USSDClientImpl(Configuration configuration) {
		super(configuration);
	}

	/**
	 * Send an USSD over OneAPI to one mobile terminal '
	 * @param address
	 * @param message
	 * @return InboundSMSMessage
	 */
	@Override
	public InboundSMSMessage sendMessage(String address, String message) {
		RequestData requestData = new RequestData(USSD_URL_BASE, Method.POST);
		requestData.setFormParams(new USSDRequest(address, message));
        requestData.setContentType(JSON_CONTENT_TYPE);
		return executeMethod(requestData, InboundSMSMessage.class);
	}

	/**
	 * Stop USSD session
	 * @param address
	 * @param message
	 */
	@Override
	public void stopSession(String address, String message) {
		RequestData requestData = new RequestData(USSD_URL_BASE, Method.POST);
		requestData.setFormParams(new USSDRequest(address, message, true));
		requestData.setContentType(JSON_CONTENT_TYPE);
	    executeMethod(requestData);
	}
}
