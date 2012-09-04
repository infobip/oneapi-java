package oneapi.client.impl;

import oneapi.client.impl.OneAPIBaseClientImpl;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.model.Authentication.AuthType;
import oneapi.model.common.RequestError;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Client base class containing common methods and properties
 * @author vavukovic
 *
 */
 public class OneAPIBaseClientImpl {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OneAPIBaseClientImpl.class);

    protected static final String CHARSET = "UTF-8";

    protected static final int RESPONSE_CODE_200_OK = 200;

    protected static final int RESPONSE_CODE_201_CREATED = 201;
 
    protected static final int RESPONSE_CODE_204_NO_CONTENT = 204;

    private static final String POST_REQUEST_METHOD = "POST";
    
    private static final String PUT_REQUEST_METHOD = "PUT";

    private static final String GET_REQUEST_METHOD = "GET";

    private static final String DELETE_REQUEST_METHOD = "DELETE";

    protected static final String URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    
    protected static final String JSON_CONTENT_TYPE = "application/json";
 
    private Configuration configuration = null;
    private ObjectMapper objectMapper = null;

    /**
     * Initialize OneAPIClientBase
     * @param configuration
     */
    protected OneAPIBaseClientImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Get Configuration object
     * @return Configuration
     */
    protected Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Execute POST Request
     * @param apiUrl
     * @return HttpURLConnection
     */
    protected HttpURLConnection executePost(String apiUrl) {
        return executePost(apiUrl, null);
    }

    /**
     * Execute POST Request
     * @param apiUrl
     * @param formParams
     * @return HttpURLConnection
     */
    protected HttpURLConnection executePost(String apiUrl, Object formParams) {
        return setupOneAPIConnection(POST_REQUEST_METHOD, apiUrl, formParams, URL_ENCODED_CONTENT_TYPE);
    }
    
    /**
     * Execute POST Request
     * @param apiUrl
     * @param formParams
     * @param contentType
     * @return HttpURLConnection
     */
    protected HttpURLConnection executePost(String apiUrl, Object formParams, String contentType) {
        return setupOneAPIConnection(POST_REQUEST_METHOD, apiUrl, formParams, contentType);
    }
    
    /**
	 * Execute PUT Request
	 * @param apiUrl
	 * @param formParams
	 * @return HttpURLConnection
	 */
	protected HttpURLConnection executePut(String apiUrl, Object formParams) {
		return setupOneAPIConnection(PUT_REQUEST_METHOD, apiUrl, formParams, URL_ENCODED_CONTENT_TYPE);
	}

    /**
     * Execute GET Request
     * @param apiUrl
     * @return HttpURLConnection
     * @throws RequestException
     */
    protected HttpURLConnection executeGet(String apiUrl) {
        return setupOneAPIConnection(GET_REQUEST_METHOD, apiUrl);
    }

    /**
     * Execute DELETE Request
     * @param apiUrl
     * @return HttpURLConnection
     */
    protected HttpURLConnection executeDelete(String apiUrl) {
        return setupOneAPIConnection(DELETE_REQUEST_METHOD, apiUrl);
    }

    /**
     * Setup connection
     * @param requestMethod
     * @param apiUrl
     * @return HttpURLConnection
     */
    private HttpURLConnection setupOneAPIConnection(String requestMethod, String apiUrl) {
        return setupOneAPIConnection(requestMethod, apiUrl, null, null);
    }

    /**
     * Setup connection
     * @param requestMethod
     * @param apiUrl
     * @param formParams
     * @param contentType
     * @return HttpURLConnection
     * @throws RequestException
     */
    private HttpURLConnection setupOneAPIConnection(String requestMethod, String apiUrl, Object formParams, String contentType) {
        try {
            HttpURLConnection connection = null;
            //setup connection with custom authorization
            if (configuration.getAuthentication().getType().equals(AuthType.OAUTH)) {
                connection = setupConnectionWithCustomAuthorization(apiUrl, "OAuth", configuration.getAuthentication().getAccessToken());
            } else if (configuration.getAuthentication().getType().equals(AuthType.IBSSO)) {
                String ibssoToken = configuration.getAuthentication().getIbssoToken();
                connection = setupConnectionWithCustomAuthorization(apiUrl, "IBSSO", ibssoToken);
            }

            //Set Content Type
            if ((contentType != null) && (!contentType.isEmpty())) {
                connection.setRequestProperty("Content-Type", contentType);
            }
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("User-Agent", "OneApi-Java-".concat(SMSClient.VERSION));
                     
            //Set Request Method
            connection.setRequestMethod(requestMethod);
            
          

            //Set Request Body
            if (formParams != null) {
                connection.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                if (contentType.equals(URL_ENCODED_CONTENT_TYPE)) {
                	 out.write(formEncodeParams(formParams));
                } else if (contentType.equals(JSON_CONTENT_TYPE)) {
                	 String json = getObjectMapper().writeValueAsString(formParams);
                	 out.write(json);
                }
               
                out.close();
            }

            return connection;

        } catch (Exception e) {
            throw new RequestException(e.getMessage());
        }
    }

    private HttpURLConnection setupConnectionWithCustomAuthorization(String url, String authorizationScheme, String authHeaderValue) throws MalformedURLException, IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Intitiating connection to URL: " + url);
        }

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        if (authHeaderValue!=null) {
            con.setRequestProperty("Authorization", authorizationScheme + " " + authHeaderValue);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Authorization type " + authorizationScheme + " using " + authHeaderValue);
            }
        }
        return con;
    }

    @SuppressWarnings("unchecked")
    private String formEncodeParams(Object formParams) throws IOException {
    	Map<String, Object> formParamsMap = getObjectMapper().convertValue(formParams, Map.class);
    	if (formParamsMap == null) {
    		if (LOGGER.isDebugEnabled()) {
    			LOGGER.debug("No request form parameters!");
    		}
    		return "";
    	}

    	StringBuilder sb = new StringBuilder();
    	int i = 0;
    	for (Entry<String, Object> entry : formParamsMap.entrySet()) {
    		if (entry.getValue() != null) {	
    			if (entry.getValue() instanceof ArrayList) {
    				ArrayList<String> list = (ArrayList<String>) entry.getValue();
    				for (String listItem : list) {
    					if (listItem != null) {
    						appendEncodedParam(sb, entry.getKey(), listItem, i++);	
    					}
    				}
    			} else {		
    				appendEncodedParam(sb, entry.getKey(), entry.getValue(), i++);
    			}
    		}
    	}

    	if (LOGGER.isDebugEnabled()) {
    		LOGGER.debug("Request form parameters: " + sb.toString());
    	}
    	return sb.toString();
    }
    
    private void appendEncodedParam(StringBuilder sb, String key, Object value, int paramCounter) throws UnsupportedEncodingException {
    	if (paramCounter > 0) {
			sb.append("&");
		}
		sb.append(URLEncoder.encode(key, CHARSET));
		sb.append("=");
		sb.append(URLEncoder.encode(String.valueOf(value), CHARSET));
    }

    /**
     * Build url, append relativeScopeUrl to the messagingBaseUrl
     * @param relativeApiUrl relative URL
     * @return String
     */
    protected String appendMessagingBaseUrl(String relativeApiUrl) {
        StringBuilder urlBuilder = new StringBuilder(configuration.getApiUrl());
        if (!configuration.getApiUrl().endsWith("/")) {
            urlBuilder.append("/");
        }
        urlBuilder.append(encodeURLParam(configuration.getVersionOneAPISMS()));
        if (!relativeApiUrl.startsWith("/")) {
            urlBuilder.append("/");
        }

        urlBuilder.append(relativeApiUrl);
        return urlBuilder.toString();
    }

    /**
     * Encode URL parameter
     * @param param
     * @return String - encoded parameter

     */
    protected String encodeURLParam(String param) {
        try {
            return URLEncoder.encode(param, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RequestException(e);
        }
    }

    protected <T> T deserialize(HttpURLConnection connection, Class<T> clazz, int requiredStatus) throws RequestException {
        return deserialize(connection, clazz, requiredStatus, null);
    }

    /**
     * Deserialize response
     *
     * @param connection
     * @param clazz
     * @param requiredStatus
     * @param rootElement
     * @return T of 'Class<T> clazz' type
     * @throws RequestException
     */
    protected <T> T deserialize(HttpURLConnection connection, Class<T> clazz, int requiredStatus, String rootElement) {
    	int responseCode = getResponseCode(connection);
    	String contentEncoding = getContentEncoding(connection.getContentType());

    	if (responseCode == requiredStatus) {	
    		try {   	
    			LOGGER.debug("Processing JSON Response");
    			byte[] bytes = read(connection.getInputStream(), contentEncoding);     					
    			return convertJSONToObject(bytes, clazz, rootElement);
    			
    		} catch (Exception e) {
    			throw new RequestException(e);	   
    		} 

    	} else {
    		//Read RequestError from the response and throw the Exception
    		throw readRequestException(connection, responseCode, contentEncoding);
    	}
    }

    protected byte[] read(HttpURLConnection connection, int requiredStatus)  {
        return read(connection, requiredStatus, null);
    }

   /**
    * Read connection input stream bytes
    * @param connection
    * @param requiredStatus
    * @param rootElement
    * @return
    */
    protected byte[] read(HttpURLConnection connection, int requiredStatus, String rootElement) {
    	int responseCode = getResponseCode(connection);
    	String contentEncoding = getContentEncoding(connection.getContentType());

    	if (responseCode == requiredStatus) {
    		try {
    			LOGGER.debug("Processing JSON Response");
    			byte[] bytes = read(connection.getInputStream(), contentEncoding);     
   
    			if(null != rootElement) {
    				return getObjectMapper().reader().readTree(new ByteArrayInputStream(bytes)).get(rootElement).getTextValue().getBytes(contentEncoding);
    			} else {
    				return bytes;
    			}

    		} catch (Exception e) {
    			throw new RequestException(e);
    		}

    	} else {	  
    		//Read RequestError from the response and throw the Exception
    		throw readRequestException(connection, responseCode, contentEncoding);
    	}	
    }

    private byte[] read(InputStream inputStream, String contentEncoding) throws IOException {
    	if (inputStream == null) {
    		throw new RequestException("Unexpected error occured. Response is empty.");
    	}
    	
    	// Convert response body so it can be processed through JSON parser
    	ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
    	int i;
    	while ((i = (byte) inputStream.read()) != -1) baOutputStream.write(i);

    	byte[] bytes = baOutputStream.toByteArray();     
    	
    	LOGGER.debug("Response data: " + new String(baOutputStream.toByteArray(), contentEncoding));
    	return bytes;   
    }

    private RequestException readRequestException(HttpURLConnection connection, int responseCode, String contentEncoding) {
    	LOGGER.debug("Processing RequestError JSON Response");

    	String errorText = "Unexpected error occured.";
    	String messageId = "";
    	byte[] bytes = new byte[0];
    	try {
    		bytes = read(connection.getErrorStream(), contentEncoding);
    		RequestError errorResponse = convertJSONToObject(bytes, RequestError.class, "requestError");
    		
    		if (errorResponse != null) {
    			if (errorResponse.getPolicyException() != null) {
    				errorText = errorResponse.getPolicyException().getText();
    				messageId = errorResponse.getPolicyException().getMessageId();
    			} else if (errorResponse.getServiceException() != null) {
    				errorText = errorResponse.getServiceException().getText();
    				messageId = errorResponse.getServiceException().getMessageId();
    			}
    		}

    	} catch (Exception e) {	
    		return new RequestException(e, responseCode);
    	}

    	return new RequestException(errorText, messageId, responseCode);
    }
    
    protected <T> T convertJSONToObject(byte[] jsonBytes, Class<T> clazz) {
    	return convertJSONToObject(jsonBytes, clazz, null);
    }
    
    protected <T> T convertJSONToObject(byte[] jsonBytes, Class<T> clazz, String rootElement) {
    	try {
    		if(null != rootElement) {
				return getObjectMapper().readValue(getObjectMapper().reader().readTree(new ByteArrayInputStream(jsonBytes)).get(rootElement), clazz);
			} else {
				return getObjectMapper().readValue(jsonBytes, clazz);
			}
    	} catch (Exception e) {
			throw new RequestException(e);	   
		} 
    }
    
    protected void validateResponse(HttpURLConnection connection, int responseCode, int requiredStatus) {
    	if (responseCode != requiredStatus) {
    		String contentEncoding = getContentEncoding(connection.getContentType());	
    		throw readRequestException(connection, responseCode, contentEncoding);	
    	}
    }
 
    protected int getResponseCode(HttpURLConnection connection) {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            throw new RequestException(e);
        }
    }

    protected String getContentEncoding(String contentType) {
    	String contentEncoding = "";
    	if (contentType != null) {
    		String[] contentTypeSplited = contentType.split(";");
    		
    		for (String splitedItem : contentTypeSplited) {
    			if (splitedItem.toLowerCase().startsWith("charset=")) {
    				contentEncoding = splitedItem.substring("charset=".length());
    			}
    		}
    	}

    	if (contentEncoding.isEmpty()) {
    		contentEncoding = CHARSET;
    	}

    	return contentEncoding;
    }
    
    protected String GetIdFromResourceUrl(String resourceUrl) 
    {
    	String id = "";
        if (resourceUrl.contains("/"))
        {
        	String[] arrResourceUrl = resourceUrl.split("/");
            if (arrResourceUrl.length > 0)
            {
                id = arrResourceUrl[arrResourceUrl.length - 1];
            }
        }

        return id;
    }

    private ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
