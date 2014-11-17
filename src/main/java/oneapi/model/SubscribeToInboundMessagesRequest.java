package oneapi.model;

public class SubscribeToInboundMessagesRequest {
    private String destinationAddress = null;
    private String notifyURL = null;
    private String criteria = null;
    private String notificationFormat = null;
    private String clientCorrelator = null;
    private String callbackData = null;

    public SubscribeToInboundMessagesRequest() {
    }

    public SubscribeToInboundMessagesRequest(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    /**
     * Start subscribing to notifications of SMS messages sent to your application
     * @param destinationAddress is the address/ MSISDN, or code agreed with the operator, to which people may send an SMS to your application
     * @param notifyURL is the URL to which you would like a notification of message receipts sent
     */
    public SubscribeToInboundMessagesRequest(String destinationAddress, String notifyURL) {
        this.destinationAddress = destinationAddress;
        this.notifyURL = notifyURL;
    }

    /**
     * Start subscribing to notifications of SMS messages sent to your application
     * @param destinationAddress (mandatory) is the address/ MSISDN, or code agreed with the operator, to which people may send an SMS to your application
     * @param notifyURL (mandatory) is the URL to which you would like a notification of message receipts sent
     * @param criteria (optional) is case-insensitve text to match against the first word of the message, ignoring any leading whitespace. This allows you to reuse a short code among various applications, each of which can register their own subscription with different criteria
     * @param notificationFormat (optional) is the content type that notifications will be sent in Đ for OneAPI v1.0 only JSON is supported
     * @param clientCorrelator (optional) uniquely identifies this create subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid creating a duplicate subscription
     * @param callbackData (optional) is a function name or other data that you would like included when the POST is sent to your application
     */
    public SubscribeToInboundMessagesRequest(String destinationAddress, String notifyURL, String criteria, String notificationFormat, String clientCorrelator, String callbackData) {
        this.destinationAddress = destinationAddress;
        this.notifyURL = notifyURL;
        this.criteria = criteria;
        this.notificationFormat = notificationFormat;
        this.criteria = criteria;
        this.clientCorrelator = clientCorrelator;
        this.callbackData = callbackData;

    }

    /**
     * (mandatory) is the address/ MSISDN, or code agreed with the operator, to which people may send an SMS to your application
     * @return destinationAddress
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * (mandatory) is the address/ MSISDN, or code agreed with the operator, to which people may send an SMS to your application
     */
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * (mandatory) is the URL to which you would like a notification of message receipts sent
     * @return notifyURL
     */
    public String getNotifyURL() {
        return notifyURL;
    }

    /**
     * (mandatory) is the URL to which you would like a notification of message receipts sent
     */
    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    /**
     * (optional) is case-insensitve text to match against the first word of the message, ignoring any leading whitespace. This allows you to reuse a short code among various applications, each of which can register their own subscription with different criteria
     * @return criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * (optional) is case-insensitve text to match against the first word of the message, ignoring any leading whitespace. This allows you to reuse a short code among various applications, each of which can register their own subscription with different criteria
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    /**
     * (optional) is the content type that notifications will be sent in Đ for OneAPI v1.0 only JSON is supported
     * @return notificationFormat
     */
    public String getNotificationFormat() {
        return notificationFormat;
    }

    /**
     * (optional) is the content type that notifications will be sent in Đ for OneAPI v1.0 only JSON is supported
     */
    public void setNotificationFormat(String notificationFormat) {
        this.notificationFormat = notificationFormat;
    }

    /**
     * (optional) uniquely identifies this create subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid creating a duplicate subscription
     * @return clientCorrelator
     */
    public String getClientCorrelator() {
        return clientCorrelator;
    }

    /**
     * (optional) uniquely identifies this create subscription request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request allows the operator to avoid creating a duplicate subscription
     */
    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    /**
     * (optional) is a function name or other data that you would like included when the POST is sent to your application
     */
    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    /**
     * (optional) is a function name or other data that you would like included when the POST is sent to your application
     * @return callbackData
     */
    public String getCallbackData() {
        return callbackData;
    }
}
