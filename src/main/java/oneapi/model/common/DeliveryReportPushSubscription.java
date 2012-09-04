package oneapi.model.common;


public class DeliveryReportPushSubscription {
    private String callbackData;
    private String notifyUrl;

    public DeliveryReportPushSubscription(String callbackData, String notifyUrl) {
        this.callbackData = callbackData;
        this.notifyUrl = notifyUrl;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

	@Override
	public String toString() {
		return "DeliveryReportPushSubscription {callbackData=" + callbackData
				+ ", notifyUrl=" + notifyUrl + "}";
	}
}
