package oneapi.model.common;

import java.io.Serializable;

public class DeliveryReportSubscription implements Serializable {

	private static final long serialVersionUID = 12349652699046888L;

	private String subscriptionId;
	private String senderAddress;
	private String criteria;
	private String notifyUrl;
	private String callbackData;
	
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getCallbackData() {
		return callbackData;
	}
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}
	@Override
	public String toString() {
		return "DeliveryReportSubscription {subscriptionId=" + subscriptionId
				+ ", senderAddress=" + senderAddress + ", criteria=" + criteria
				+ ", notifyUrl=" + notifyUrl + ", callbackData=" + callbackData
				+ "}";
	}
	
	
}
