package oneapi.model.common;

import java.util.Arrays;

/**
 * InboundMessageList contains the detail of the response to get a list of
 * received SMS messages
 */
public class InboundSMSMessageList implements java.io.Serializable {
	private static final long serialVersionUID = -1816167056547318579L;

	/**
	 * The inboundSMSMessageList object contains an inboundSMSMessage array
	 * detailing: the dateTime that the message was received, destinationAddress
	 * is the number associated with your service (for example an agreed short
	 * code, see �What do I need?� above), messageId is a server-generated
	 * message identifier, the message body, resourceURL is a link to the
	 * message, senderAddress is the MSISDN or Anonymous Customer Reference of
	 * the sender.
	 */
	InboundSMSMessage[] inboundSMSMessage = null;

	/**
	 * return the inboundSMSMessage array
	 */
	public InboundSMSMessage[] getInboundSMSMessage() {
		return inboundSMSMessage;
	}

	/**
	 * set the inboundSMSMessage array. This is called internally to set the
	 * contents according to the JSON response.
	 */
	public void setInboundSMSMessage(InboundSMSMessage[] inboundSMSMessage) {
		this.inboundSMSMessage = inboundSMSMessage;
	}

	/**
	 * the number of messages returned for this batch
	 */
	Integer numberOfMessagesInThisBatch = null;
	/**
	 * resourceURL containing a URL uniquely identifying this MMS message list
	 */
	String resourceURL = null;
	/**
	 * the totalNumberOfPendingMessages awaiting retrieval from gateway storage
	 */
	Integer totalNumberOfPendingMessages = null;

	/**
	 * Used for MO push.
	 */
	private String callbackData = null;

	/**
	 * return the number of messages returned for this batch
	 */
	public Integer getNumberOfMessagesInThisBatch() {
		return numberOfMessagesInThisBatch;
	}

	/**
	 * return resourceURL containing a URL uniquely identifying this MMS message
	 * list
	 */
	public String getResourceURL() {
		return resourceURL;
	}

	/**
	 * return the totalNumberOfPendingMessages awaiting retrieval from gateway
	 * storage
	 */
	public Integer getTotalNumberOfPendingMessages() {
		return totalNumberOfPendingMessages;
	}

	/**
	 * set the number of messages returned for this batch. This is called
	 * internally to set the contents according to the JSON response.
	 */
	public void setNumberOfMessagesInThisBatch(Integer numberOfMessagesInThisBatch) {
		this.numberOfMessagesInThisBatch = numberOfMessagesInThisBatch;
	}

	/**
	 * set resourceURL containing a URL uniquely identifying this MMS message
	 * list. This is called internally to set the contents according to the JSON
	 * response.
	 */
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	/**
	 * set the totalNumberOfPendingMessages awaiting retrieval from gateway
	 * storage. This is called internally to set the contents according to the
	 * JSON response.
	 */
	public void setTotalNumberOfPendingMessages(Integer totalNumberOfPendingMessages) {
		this.totalNumberOfPendingMessages = totalNumberOfPendingMessages;
	}

	public String getCallbackData() {
		return callbackData;
	}

	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}

	/**
	 * generate a textual representation of the inboundSMSMessageList instance
	 * including nested elements and classes
	 */
	@Override
	public String toString() {
		return "InboundSMSMessageList {inboundSMSMessage="
				+ Arrays.toString(inboundSMSMessage)
				+ ", numberOfMessagesInThisBatch="
				+ numberOfMessagesInThisBatch + ", resourceURL=" + resourceURL
				+ ", totalNumberOfPendingMessages="
				+ totalNumberOfPendingMessages + "}";
	}
}
