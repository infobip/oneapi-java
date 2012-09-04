package oneapi.model;

public class SMSRequest {
	private String senderAddress = "";
	private String[] address = null;
	private String message = "";
	private String clientCorrelator = null; 
	private String notifyURL = null;
	private String senderName = null;
	private String callbackData = null;
		
    public SMSRequest() {
	}
    
	/**
	 * Initialize SMS object using mandatory parameters
	 * @param senderAddress is the address to whom a responding SMS may be sent
	 * @param recipientAddress contains one or more addresses for end user ID to send to 
	 * @param message contains the message text to send
	 */
	public SMSRequest(String senderAddress, String message, String... recipientAddress) {
		this.senderAddress = senderAddress;
		this.message = message;
		this.address = recipientAddress;
	}

	/**
	 *Initialize SMS object using mandatory parameters
	 @param senderAddress is the address to whom a responding SMS may be sent
	 @param recipientAddress contains one or more addresses for end user ID to send to 
	 @param message contains the message text to send
	 @param clientCorrelator uniquely identifies this create MMS request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid sending the same MMS twice.
	 @param notifyURL is the URL to which you would like a notification of delivery sent
	 @param senderName is the name to appear on the user's terminal as the sender of the message
	 @param callbackData will be passed back to the notifyURL location, so you can use it to identify the message the receipt relates to (or any other useful data, such as a function name)
	 */
	public SMSRequest(String senderAddress, String message, String clientCorrelator, String notifyURL, String senderName, String callbackData, String... recipientAddress) {
		this.senderAddress = senderAddress;	
		this.message = message;
		this.clientCorrelator = clientCorrelator;
		this.notifyURL = notifyURL;
		this.senderName = senderName;
		this.callbackData = callbackData;
		this.address = recipientAddress;
	}

	/**
	 * (mandatory) is the address to whom a responding SMS may be sent
	 * @return senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * (mandatory) is the address to whom a responding SMS may be sent
	 * @param senderAddress
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	/**
	 * (mandatory) contains one address for end user ID to send to 
	 * @return recipientsAddress
	 */
	public String[] getAddress() {
		return address;
	}

	/**
	 * (mandatory) contains one address for end user ID to send to 
	 * @return recipientsAddress
	 */
	public void setAddress(String[] address) {
		this.address = address;
	}

	/**
	 * (mandatory) contains the message text to send
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * (mandatory) contains the message text to send
	 * @param messageText
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * (optional) uniquely identifies this create MMS request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid sending the same MMS twice.
	 * @return clientCorrelator
	 */
	public String getClientCorrelator() {
		return clientCorrelator;
	}

	/**
	 * (optional) uniquely identifies this create MMS request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid sending the same MMS twice.
	 * @param clientCorrelator
	 */
	public void setClientCorrelator(String clientCorrelator) {
		this.clientCorrelator = clientCorrelator;
	}

	/**
	 * (optional) is the URL to which you would like a notification of delivery sent
	 * @return notifyURL
	 */
	public String getNotifyURL() {
		return notifyURL;
	}

	/**
	 * (optional) is the URL to which you would like a notification of delivery sent
	 * @param notifyURL
	 */
	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}

	/**
	 * (optional) is the name to appear on the user's terminal as the sender of the message
	 * @return senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * (optional) is the name to appear on the user's terminal as the sender of the message
	 * @param senderName
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * (optional) will be passed back to the notifyURL location, so you can use it to identify the message the receipt relates to (or any other useful data, such as a function name)
	 * @param callbackData
	 */
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}	

	/**
	 * (optional) will be passed back to the notifyURL location, so you can use it to identify the message the receipt relates to (or any other useful data, such as a function name)
	 * @return callbackData
	 */
	public String getCallbackData() {
		return callbackData;
	}
}
