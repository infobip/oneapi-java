package oneapi.model.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * contains an error response returned from the OneAPI server
 */
public class RequestError implements java.io.Serializable {
	private static final long serialVersionUID = -4594109872052136844L;
	protected static final Logger LOG = LoggerFactory.getLogger(RequestError.class);
	
	/**
	 * internally used to indicate the type of exception being stored is a ServiceException
	 */
	public static final int SERVICEEXCEPTION=1;
	/**
	 * internally used to indicate the type of exception being stored is a PolicyException
	 */
	public static final int POLICYEXCEPTION=2;
	
	/**
	 * instance of a ServiceException
	 */
	private String clientCorrelator=null;
	
	/**
	 * instance of a ServiceException
	 */
	private ServiceException serviceException=null;
	/** 
	 * instance of a PolicyException
	 */
	private PolicyException policyException=null;
	
	/**
	 * the type of exception being stored
	 */
	private int exceptionType=0;

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
	public ServiceException getServiceException() { return serviceException; }
	/**
	 * set the serviceException instance
	 */
	public void setServiceException(ServiceException serviceException) { this.serviceException=serviceException; exceptionType=SERVICEEXCEPTION; }
	
	/**
	 * get the type of the exception
	 */
	@JsonIgnore
	public int getExceptionType() { return exceptionType; }

	/**
	 * get the HTTP response code
	 */
	@JsonIgnore
	public int getHttpResponseCode() { return httpResponseCode; }
	
	/**
	 * return the policyException instance
	 */
	public PolicyException getPolicyException() { return policyException; }
	/**
	 * set the policyException instance
	 */
	public void setPolicyException(PolicyException policyException) { this.policyException=policyException; exceptionType=POLICYEXCEPTION; }

	/**
	 * set the type of the exception
	 */
	@JsonIgnore
	public void setExceptionType(int n) { exceptionType=n; }

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
		if (type==SERVICEEXCEPTION) {
			serviceException=new ServiceException();
			serviceException.setMessageId(messageId);
			serviceException.setText(text);
			serviceException.setVariables(variables);
		} else if (type==POLICYEXCEPTION) {
			policyException=new PolicyException();
			policyException.setMessageId(messageId);
			policyException.setText(text);
			policyException.setVariables(variables);			
		}
		exceptionType=type;
		this.clientCorrelator=clientCorrelator;
		this.httpResponseCode = httpResponseCode;
	}
	
	/**
	 * default constructor
	 */
	public RequestError() {
	}
	
	/** 
	 * generate JSON representation of the RequestError including all nested elements and classes 
	 */
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		String ret = null;
		try {
			ret = "{\"requestError\":" + mapper.writeValueAsString(this) + "}";
		} catch (Exception e) {
			LOG.error( "Error mapping json " + e.getMessage(), e );
		}
		return ret;
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
		if (serviceException!=null) {
			buffer.append("serviceException = {");
			buffer.append("messageId = ");
			buffer.append(serviceException.getMessageId());
			buffer.append(", text = ");
			buffer.append(serviceException.getText());
			buffer.append(", variables = ");
			if (serviceException.getVariables()!=null) {
				buffer.append("{");
				for (int i=0; i<serviceException.getVariables().length; i++) {
					buffer.append("[");
					buffer.append(i);
					buffer.append("] = ");
					buffer.append(serviceException.getVariables()[i]);
				}
				buffer.append("}");
			}
			buffer.append("}");
		}
		if (policyException!=null) {
			buffer.append("policyException = {");
			buffer.append("messageId = ");
			buffer.append(policyException.getMessageId());
			buffer.append(", text = ");
			buffer.append(policyException.getText());
			buffer.append(", variables = ");
			if (policyException.getVariables()!=null) {
				buffer.append("{");
				for (int i=0; i<policyException.getVariables().length; i++) {
					buffer.append("[");
					buffer.append(i);
					buffer.append("] = ");
					buffer.append(policyException.getVariables()[i]);
				}
				buffer.append("}");
			}
			buffer.append("}");
		}

		return buffer.toString();		
	}
	
	
}

