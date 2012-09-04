package oneapi.model;

import java.io.Serializable;

import oneapi.model.common.DeliveryInfoList.DeliveryInfo;

public class DeliveryInfoNotification implements Serializable {
	private static final long serialVersionUID = -7489813428504662415L;
	
	private DeliveryInfo deliveryInfo;
	private String callbackData;
	
	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}
	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	public String getCallbackData() {
		return callbackData;
	}
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}
	@Override
	public String toString() {
		return "DeliveryInfoNotification {deliveryInfo=" + deliveryInfo
				+ ", callbackData=" + callbackData + "}";
	}	
}
