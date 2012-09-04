package oneapi.model;

import java.io.Serializable;

import oneapi.model.common.Roaming;

public class RoamingNotification implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private Roaming roaming;
	private String callbackData;
	
	public Roaming getRoaming() {
		return roaming;
	}
	public void setRoaming(Roaming roaming) {
		this.roaming = roaming;
	}
	public String getCallbackData() {
		return callbackData;
	}
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}
	@Override
	public String toString() {
		return "RoamingNotification {roaming=" + roaming + ", callbackData="
				+ callbackData + "}";
	}
}
