package oneapi.model.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * InboundMessage contains the main message information for an SMS message (not
 * including attachment information)
 */
public class InboundSMSMessage implements Serializable {
	private static final long serialVersionUID = -5772672709791289909L;

	/**
	 * the date/time that the SMS message was sent
	 */
	private Date dateTime = null;
	/**
	 * the recipient MSISDN or other identifying number
	 */
	private String destinationAddress = null;
	/**
	 * unique messageId for the message
	 */
	private String messageId = null;
	/**
	 * content of the SMS message body
	 */
	private String message = null;
	/**
	 * resourceReference contains a URL uniquely identifying this SMS message
	 */
	String resourceURL = null;
	/**
	 * the sender MSISDN or other identifying number
	 */
	private String senderAddress = null;
	/**
	 * internal two-way SMS session id
	 */
	private int moSessionId = 0;

	private String moResponseKey;

	private String callbackData;

	private double price;

	public InboundSMSMessage() {
		super();
	}
	
	/**
	 * return the date/time that the SMS message was sent.
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * return the recipient MSISDN or other identifying number
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * return the unique messageId for the message
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * return the SMS message body
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * return resourceURL containing a URL uniquely identifying this SMS message
	 */
	public String getResourceURL() {
		return resourceURL;
	}

	/**
	 * return the sender MSISDN or other identifying number
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * alternate method to get the message date/time in java.util.Date format
	 */
	@JsonIgnore
	public java.util.Date getDateTimeAsDate() {
		return dateTime;
	}

	/**
	 * set the date/time that the SMS message was sent.
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * set the recipient MSISDN or other identifying number. This is called
	 * internally to set the contents according to the JSON response.
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * set the unique messageId for the SMS message. This is called internally
	 * to set the contents according to the JSON response.
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * set the SMS message body. This is called internally to set the contents
	 * according to the JSON response.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * set resourceURL containing a URL uniquely identifying this SMS message.
	 * This is called internally to set the contents according to the JSON
	 * response.
	 */
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	/**
	 * set the sender MSISDN or other identifying number. This is called
	 * internally to set the contents according to the JSON response.
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public int getMoSessionId() {
		return moSessionId;
	}

	public void setMoSessionId(int moSessionId) {
		this.moSessionId = moSessionId;
	}

	public String getMoResponseKey() {
		return moResponseKey;
	}

	public void setMoResponseKey(String moResponseKey) {
		this.moResponseKey = moResponseKey;
	}

	public String getCallbackData() {
		return callbackData;
	}

	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * generate a textual representation of the InboundMessage including all
	 * nested elements and classes
	 */
	@Override
	public String toString() {
		return "InboundSMSMessage {dateTime=" + dateTime
				+ ", destinationAddress=" + destinationAddress + ", messageId="
				+ messageId + ", message=" + message + ", resourceURL="
				+ resourceURL + ", senderAddress=" + senderAddress
				+ ", moSessionId=" + moSessionId 
				+ ", moResponseKey=" + moResponseKey 
				+ ", callbackData=" + callbackData 
				+ ", price=" + price 
				+ "}";
	}
}
