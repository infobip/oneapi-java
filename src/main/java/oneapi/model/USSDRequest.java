package oneapi.model;

public class USSDRequest {
	private String address = null;
	private String message = null;
	private Boolean stopSession = null;

	public USSDRequest() {
	}
	
	public USSDRequest(String address, String message) {
		this.address = address;
		this.message = message;
	}

	public USSDRequest(String address, String message, boolean stopSession) {		
		this(address, message);
		this.stopSession = stopSession;
	}
		
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean isStopSession() {
		return stopSession;
	}
	
	public void setStopSession(Boolean stopSession) {
		this.stopSession = stopSession;
	}	
}
