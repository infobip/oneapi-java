package oneapi.model;

public class USSDRequest {
	private String address = null;
	private String message = null;
	private boolean stopSession = false;

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
	
	public boolean isStopSession() {
		return stopSession;
	}
	
	public void setStopSession(boolean stopSession) {
		this.stopSession = stopSession;
	}	
}
