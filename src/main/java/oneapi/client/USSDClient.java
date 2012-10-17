package oneapi.client;

import oneapi.model.common.InboundSMSMessage;

public interface USSDClient {

	/**
	 * Send an USSD over OneAPI to one mobile terminal 
	 * @param address
	 * @param message
	 * @return InboundSMSMessage
	 */
	InboundSMSMessage sendMessage(String address, String message);
	
	/**
	 * Stop USSD session
	 * @param address
	 * @param message
	 */
	void stopSession(String address, String message);
}
