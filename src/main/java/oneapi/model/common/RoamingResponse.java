package oneapi.model.common;

public class RoamingResponse {
	
	private Roaming roaming;
	
	private String callbackData;
	
	public RoamingResponse() {
		super();
	}

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

}
