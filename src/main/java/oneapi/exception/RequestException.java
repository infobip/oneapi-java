package oneapi.exception;

public class RequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String messageId;
    private int responseCode;

    public RequestException() {
    }

    public RequestException(String s) {
        super(s);
    }

    public RequestException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RequestException(Throwable throwable) {
        super(throwable);
    }
    
    public RequestException(Throwable throwable, int responseCode) {
        super(throwable);
        this.responseCode = responseCode;
    }
    
    public RequestException(String s, String messageId) {
    	super(s);
        this.messageId = messageId;
    }

    public RequestException(String errorText, String messageId, int responseCode) {
        this(errorText);
        this.messageId = messageId;
        this.responseCode = responseCode;
    }

    public String getMessageId() {
		return messageId;
	}

    public int getResponseCode() {
        return responseCode;
    }
}
