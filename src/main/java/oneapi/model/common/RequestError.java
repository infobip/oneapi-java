package oneapi.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * contains an error response returned from the OneAPI server
 */
public class RequestError implements java.io.Serializable {
	private static final long serialVersionUID = -4594109872052136844L;	
	
	/**
	 * instance of a ServiceException
	 */
	private String clientCorrelator=null;
	
	/**
	 * instance of a Exception
	 */
	private ServiceException exception=null;
	

	/**
	 * HTTP response code
	 */
	private int httpResponseCode = 400;

	/**
	 * return the clientCorrelator
	 */
	public String getClientCorrelator() { return clientCorrelator; }
	
	/**
	 * return the serviceException instance
	 */
	public ServiceException getException() { return exception; }
	/**
	 * set the serviceException instance
	 */
	public void setException(ServiceException exception) { this.exception=exception;  }
	

	/**
	 * get the HTTP response code
	 */
	@JsonIgnore
	public int getHttpResponseCode() { return httpResponseCode; }
	

	/**
	 * set the HTTP response code
	 */
	@JsonIgnore
	public void setHttpResponseCode(int n) { httpResponseCode = n; }

	/**
	 * utility constructor to create an RequestError instance with all fields set
	 * @param type
	 * @param httpResponseCode
	 * @param messageId
	 * @param text
	 * @param variables
	 */
	public RequestError(int type, int httpResponseCode, String messageId, String clientCorrelator, String text, String... variables) {		
		this.exception = new ServiceException();
        this.exception.setMessageId(messageId);
        this.exception.setText(text);
        this.exception.setVariables(variables);		
		this.clientCorrelator=clientCorrelator;
		this.httpResponseCode = httpResponseCode;
	}
	
	/**
	 * default constructor
	 */
	public RequestError() {
	}
	
	/** 
	 * generate a textual representation of the RequestError including all nested elements and classes 
	 */
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		if (clientCorrelator!=null) {
			buffer.append("clientCorrelator=");
			buffer.append(clientCorrelator);
		}
		if (exception!=null) {
			buffer.append("serviceException = {");
			buffer.append("messageId = ");
			buffer.append(exception.getMessageId());
			buffer.append(", text = ");
			buffer.append(exception.getText());
			buffer.append(", variables = ");
			if (exception.getVariables()!=null) {
				buffer.append("{");
				for (int i=0; i<exception.getVariables().length; i++) {
					buffer.append("[");
					buffer.append(i);
					buffer.append("] = ");
					buffer.append(exception.getVariables()[i]);
				}
				buffer.append("}");
			}
			buffer.append("}");
		}		

		return buffer.toString();		
	}
	
	
}

