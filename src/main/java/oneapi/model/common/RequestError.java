package oneapi.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * contains an error response returned from the OneAPI server
 */
public class RequestError implements java.io.Serializable {
    private static final long serialVersionUID = -4594109872052136844L;

    /**
     * internally used to indicate the type of exception being stored is a ServiceException
     */
    public static final int SERVICEEXCEPTION = 1;
    /**
     * internally used to indicate the type of exception being stored is a PolicyException
     */
    public static final int POLICYEXCEPTION = 2;

    /**
     * instance of a ServiceException
     */
    private String clientCorrelator = null;

    /**
     * instance of a ServiceException
     */
    private ServiceException serviceException = null;
    /**
     * instance of a PolicyException
     */
    private PolicyException policyException = null;

    /**
     * the type of exception being stored
     */
    private int exceptionType = 0;

    /**
     * HTTP response code
     */
    private int httpResponseCode = 400;

    /**
     * return the clientCorrelator
     */
    public String getClientCorrelator() {
        return clientCorrelator;
    }

    /**
     * return the serviceException instance
     */
    public ServiceException getServiceException() {
        return serviceException;
    }

    /**
     * set the serviceException instance
     */
    public void setServiceException(ServiceException serviceException) {
        this.serviceException = serviceException;
        exceptionType = SERVICEEXCEPTION;
    }

    /**
     * get the type of the exception
     */
    @JsonIgnore
    public int getExceptionType() {
        return exceptionType;
    }

    /**
     * get the HTTP response code
     */
    @JsonIgnore
    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    /**
     * return the policyException instance
     */
    public PolicyException getPolicyException() {
        return policyException;
    }

    /**
     * set the policyException instance
     */
    public void setPolicyException(PolicyException policyException) {
        this.policyException = policyException;
        exceptionType = POLICYEXCEPTION;
    }

    /**
     * set the type of the exception
     */
    @JsonIgnore
    public void setExceptionType(int n) {
        exceptionType = n;
    }

    /**
     * set the HTTP response code
     */
    @JsonIgnore
    public void setHttpResponseCode(int n) {
        httpResponseCode = n;
    }

    /**
     * utility constructor to create an RequestError instance with all fields set
     */
    public RequestError(int type, int httpResponseCode, String messageId, String clientCorrelator, String text, String... variables) {
        if (type == SERVICEEXCEPTION) {
            serviceException = new ServiceException();
            serviceException.setMessageId(messageId);
            serviceException.setText(text);
            serviceException.setVariables(variables);
        } else if (type == POLICYEXCEPTION) {
            policyException = new PolicyException();
            policyException.setMessageId(messageId);
            policyException.setText(text);
            policyException.setVariables(variables);
        }
        exceptionType = type;
        this.clientCorrelator = clientCorrelator;
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
        StringBuilder stringBuilder = new StringBuilder();
        if (clientCorrelator != null) {
            stringBuilder.append("clientCorrelator=");
            stringBuilder.append(clientCorrelator);
        }
        if (serviceException != null) {
            stringBuilder.append("serviceException = {");
            stringBuilder.append("messageId = ");
            stringBuilder.append(serviceException.getMessageId());
            stringBuilder.append(", text = ");
            stringBuilder.append(serviceException.getText());
            stringBuilder.append(", variables = ");
            if (serviceException.getVariables() != null) {
                stringBuilder.append("{");
                for (int i = 0; i < serviceException.getVariables().length; i++) {
                    stringBuilder.append("[");
                    stringBuilder.append(i);
                    stringBuilder.append("] = ");
                    stringBuilder.append(serviceException.getVariables()[i]);
                }
                stringBuilder.append("}");
            }
            stringBuilder.append("}");
        }
        if (policyException != null) {
            stringBuilder.append("policyException = {");
            stringBuilder.append("messageId = ");
            stringBuilder.append(policyException.getMessageId());
            stringBuilder.append(", text = ");
            stringBuilder.append(policyException.getText());
            stringBuilder.append(", variables = ");
            if (policyException.getVariables() != null) {
                stringBuilder.append("{");
                for (int i = 0; i < policyException.getVariables().length; i++) {
                    stringBuilder.append("[");
                    stringBuilder.append(i);
                    stringBuilder.append("] = ");
                    stringBuilder.append(policyException.getVariables()[i]);
                }
                stringBuilder.append("}");
            }
            stringBuilder.append("}");
        }

        return stringBuilder.toString();
    }


}

