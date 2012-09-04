package oneapi.model.common;

public class MoSubscription {
    private String subscriptionId;
    private String notifyURL;           // (URI) is the URI of your application to which notifications will be sent}
    private String callbackData;        // (string) is a function name or other data that you would like included when the POST is sent to your application.
    private String criteria;            // (string) is case-insensitve text to match against the first word of the message, ignoring any leading whitespace. This allows you to reuse a short code among various applications, each of which can register their own subscription with different criteria.	Optional
    private String destinationAddress;  // (string) is the MSISDN, or code agreed with the operator, to which people may send an SMS to your application	Mandatory

    // HC (uvijek dolazi JSON, ovaj podatak se ne pamti u bazi), nemamo to u bazi tj. ne upisuje se kod upisa mo subscr.
    private String notificationFormat;  // (string) is the content type that notifications will be sent in – the default format is JSON, values of XML or JSON can be specified	Optional
      

    public String getSubscriptionId() {
        return this.subscriptionId;            
    }
    
    public void setSubscriptionId(String s) {
        this.subscriptionId = s;
    }
    
    public String getCallbackData() {
        return this.callbackData;            
    }
    
    public void setCallbackData(String s) {
        this.callbackData = s;            
    }
    
    public String getCriteria() {
        return this.criteria;            
    }
    
    public void setCriteria(String s) {
        this.criteria = s;            
    }

    
    public String getDestinationAddress() {
        return this.destinationAddress;            
    }
    
    public void setDestinationAddress(String s) {
        this.destinationAddress = s;            
    }
    
    public String getNotificationFormat() {
        return this.notificationFormat;            
    }
    
    public void setNotificationFormat(String s) {
        this.notificationFormat = s;            
    }

    public String getNotifyURL() {
        return this.notifyURL;            
    }
    
    public void setNotifyURL(String s) {
        this.notifyURL = s;            
    }

	@Override
	public String toString() {
		return "MoSubscription {subscriptionId=" + subscriptionId
				+ ", notifyURL=" + notifyURL + ", callbackData=" + callbackData
				+ ", criteria=" + criteria + ", destinationAddress="
				+ destinationAddress + ", notificationFormat="
				+ notificationFormat + "}";
	}         
}
