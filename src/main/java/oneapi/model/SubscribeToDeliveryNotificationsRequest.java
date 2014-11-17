package oneapi.model;

// TODO(TK) Obrisati i gdje se koristi zamijeniti s DeliveryReceiptSubscription
public class SubscribeToDeliveryNotificationsRequest {
	private String senderAddress = null;
	private String notifyURL = null;
	private String criteria = null;
	private String clientCorrelator = null; 
	private String callbackData = null;
		
    public SubscribeToDeliveryNotificationsRequest() {
	}

    public SubscribeToDeliveryNotificationsRequest(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    /**
	 * Start subscribing to delivery status notifications over OneAPI for all your sent SMS
	 * @param senderAddress is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
	 * @param notifyURL is the URL to which you would like a notification of delivery sent
	 */
	public SubscribeToDeliveryNotificationsRequest(String senderAddress, String notifyURL) {
		this.senderAddress = senderAddress;
		this.notifyURL = notifyURL;	
	}
	
	/**
	 * Start subscribing to delivery status notifications over OneAPI for all your sent SMS                      
	 * @param senderAddress (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
	 * @param notifyURL (mandatory) is the URL to which you would like a notification of delivery sent
	 * @param criteria (optional) Text in the message to help you route the notification to a specific application. For example you may ask users to ‘text GIGPICS to 12345′ for your rock concert photos application. This text is matched against the first word, as defined as the initial characters after discarding any leading Whitespace and ending with a Whitespace or end of the string. The matching shall be case-insensitive.
	 * @param clientCorrelator (optional) uniquely identifies this subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid setting up the same subscription twice
	 * @param callbackData (optional) will be passed back to the notifyURL location, so you can use it to identify the message the delivery receipt relates to (or any other useful data, such as a function name)
	 */
	public SubscribeToDeliveryNotificationsRequest(String senderAddress, String notifyURL, String criteria, String clientCorrelator, String callbackData) {
		this.senderAddress = senderAddress;
		this.notifyURL = notifyURL;
		this.criteria = criteria;
		this.clientCorrelator = clientCorrelator;
		this.callbackData = callbackData;	
	}

	/**
	 * (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
	 * @return senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	
	/**
	 * (mandatory) is the URL to which you would like a notification of delivery sent
	 * @return notifyURL
	 */
	public String getNotifyURL() {
		return notifyURL;
	}

	/**
	 * (mandatory) is the URL to which you would like a notification of delivery sent
	 */
	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}
	
	/**
	 * criteria (optional) Text in the message to help you route the notification to a specific application. For example you may ask users to ‘text GIGPICS to 12345′ for your rock concert photos application. This text is matched against the first word, as defined as the initial characters after discarding any leading Whitespace and ending with a Whitespace or end of the string. The matching shall be case-insensitive.
	 * @return criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * criteria (optional) Text in the message to help you route the notification to a specific application. For example you may ask users to ‘text GIGPICS to 12345′ for your rock concert photos application. This text is matched against the first word, as defined as the initial characters after discarding any leading Whitespace and ending with a Whitespace or end of the string. The matching shall be case-insensitive.
	 */
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	/**
	 * (optional) optional) uniquely identifies this subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid setting up the same subscription twice
	 * @return clientCorrelator
	 */
	public String getClientCorrelator() {
		return clientCorrelator;
	}

	/**
	 * (optional) optional) uniquely identifies this subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid setting up the same subscription twice
	 */
	public void setClientCorrelator(String clientCorrelator) {
		this.clientCorrelator = clientCorrelator;
	}

	/**
	 * (optional) will be passed back to the notifyURL location, so you can use it to identify the message the delivery receipt relates to (or any other useful data, such as a function name)
	 */
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}	

	/**
	 * (optional) will be passed back to the notifyURL location, so you can use it to identify the message the delivery receipt relates to (or any other useful data, such as a function name)
	 * @return callbackData
	 */
	public String getCallbackData() {
		return callbackData;
	}
}
