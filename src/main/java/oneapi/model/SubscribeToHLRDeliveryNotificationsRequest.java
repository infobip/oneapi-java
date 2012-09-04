package oneapi.model;

public class SubscribeToHLRDeliveryNotificationsRequest {
	private String notifyURL = null;
	private String callbackData = null;
	private String clientCorrelator = null; 
			
    public SubscribeToHLRDeliveryNotificationsRequest() {
	}

    public SubscribeToHLRDeliveryNotificationsRequest(String notifyURL) {
        this.notifyURL = notifyURL;
    }

	public SubscribeToHLRDeliveryNotificationsRequest(String notifyURL, String callbackData, String clientCorrelator) {
		this.notifyURL = notifyURL;
		this.callbackData = callbackData;	
		this.clientCorrelator = clientCorrelator;	
	}

	public String getNotifyURL() {
		return notifyURL;
	}

	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}

	public String getCallbackData() {
		return callbackData;
	}

	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}

	public String getClientCorrelator() {
		return clientCorrelator;
	}

	public void setClientCorrelator(String clientCorrelator) {
		this.clientCorrelator = clientCorrelator;
	}

	@Override
	public String toString() {
		return "SubscribeToHLRDeliveryNotificationsRequest {notifyURL="
				+ notifyURL + ", callbackData=" + callbackData
				+ ", clientCorrelator=" + clientCorrelator + "}";
	}
}
