package oneapi.client.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.listener.ResponseListener;
import oneapi.model.Authentication;
import oneapi.model.Authentication.AuthType;
import oneapi.model.RequestData;
import oneapi.model.common.RequestError;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

/**
 * Client base class containing common methods and properties
 * @author vavukovic
 *
 */
public class OneAPIBaseClientImpl {
	protected static final Logger LOGGER = LoggerFactory.getLogger(OneAPIBaseClientImpl.class);

	protected static final String CHARSET = "UTF-8";

	protected static final String URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";

	protected static final String JSON_CONTENT_TYPE = "application/json";

	private Configuration configuration = null;
	private ObjectMapper objectMapper = null;
	private AsyncHttpClient asyncHttpClient = null;

	/**
	 * Initialize OneAPIClientBase
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
	 * Get asynchronous http client
	 */
	private AsyncHttpClient getAsyncHttpClient() {
		if (asyncHttpClient == null) {
			asyncHttpClient = new AsyncHttpClient();
		}
		return asyncHttpClient;
	}
	
	/**
	 * Get object mapper
	 * @return ObjectMapper
	 */
	private ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return objectMapper;
	}

	/**
	 * Execute method and deserialize response json
	 */
	protected <T> T executeMethod(RequestData requestData, Class<T> clazz) {
		HttpURLConnection connection = sendOneAPIRequest(requestData);
		return deserialize(connection, clazz, requestData.getRootElement());
	}

	/**
	 * Execute method and validate response 
	 */
	protected void executeMethod(RequestData requestData)
	{
		HttpURLConnection connection = sendOneAPIRequest(requestData);
		validateResponse(connection);
	}

	/**
	 * Execute method asynchronously and deserialize response json
	 */
	protected <T> void executeMethodAsync(RequestData requestData, Class<T> clazz, ResponseListener<T> responseListener) {
		sendOneAPIRequestAsync(requestData, clazz, responseListener);     
	}
	
	/**
	 * Convert json string to specific object
	 * @return T
	 */
	protected <T> T convertJSONToObject(byte[] jsonBytes, Class<T> clazz) {
		return convertJSONToObject(jsonBytes, clazz, null);
	}

	/**
	 * Convert json string to specific object 
	 * @return T
	 */
	protected <T> T convertJSONToObject(byte[] jsonBytes, Class<T> clazz, String rootElement) {
		try {
			if(null != rootElement && !"".equals(rootElement)) {
				JsonNode node = getObjectMapper().reader().readTree(new ByteArrayInputStream(jsonBytes)).get(rootElement);
				return getObjectMapper().readValue(node.toString(), clazz);
			} else {
				return getObjectMapper().readValue(jsonBytes, clazz);
			}
		} catch (Exception e) {
			throw new RequestException(e);	   
		} 
	}
	
	/**
	 * Extract Id from resource url
	 */
	protected String getIdFromResourceUrl(String resourceUrl) 
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

	/**
	 * Encode URL parameter
	 * @return String - encoded parameter
	 */
	protected String encodeURLParam(String param) {
		try {
			return URLEncoder.encode(param, CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RequestException(e);
		}
	}

	/**
	 * Send OneAPI request
	 * @throws RequestException
	 */
	private HttpURLConnection sendOneAPIRequest(RequestData requestData) {

		HttpURLConnection connection = null;

		try {
			String apiUrl = appendMessagingBaseUrl(requestData.getResourcePath());

			//setup connection with custom authorization
			Authentication authentication = configuration.getAuthentication();
			if (authentication.getType().equals(AuthType.BASIC)) {
				connection = setupConnectionWithCustomAuthorization(apiUrl, "Basic", new String(Base64.encodeBase64((authentication.getUsername()+":"+authentication.getPassword()).getBytes("UTF-8")), "UTF-8"));
			} else if (authentication.getType().equals(AuthType.OAUTH)) {
				connection = setupConnectionWithCustomAuthorization(apiUrl, "OAuth", authentication.getAccessToken());
			} else if (authentication.getType().equals(AuthType.IBSSO)) {
				String ibssoToken = authentication.getIbssoToken();
				connection = setupConnectionWithCustomAuthorization(apiUrl, "IBSSO", ibssoToken);
			}

			//Set Content Type
			if ((requestData.getContentType() != null) && (requestData.getContentType().length() != 0)) {
				if (connection != null) {
					connection.setRequestProperty("Content-Type", requestData.getContentType());
				}
			}
			if (connection != null) {
				connection.setRequestProperty("accept", "*/*");
			}
			connection.setRequestProperty("User-Agent", "OneApi-Java-".concat(SMSClient.VERSION));

			//Set Request Method
			connection.setRequestMethod(requestData.getRequestMethod().toString());

			//Set Request Body
			if (requestData.getFormParams() != null) {
				connection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				if (requestData.getContentType().equals(URL_ENCODED_CONTENT_TYPE)) {
					out.write(formEncodeParams(requestData.getFormParams()));
				} else if (requestData.getContentType().equals(JSON_CONTENT_TYPE)) {
					String json = getObjectMapper().writeValueAsString(requestData.getFormParams());
					out.write(json);
				}

				out.close();
			}

			return connection; 

		} catch (Exception e) {
			throw new RequestException(e);
		}    
	}   

	/**
	 * Send OneAPI request asynchronously
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> void sendOneAPIRequestAsync(final RequestData requestData, final Class<T> clazz, final ResponseListener<T> responseListener) {

		String apiUrl = appendMessagingBaseUrl(requestData.getResourcePath());

		//Build request
		RequestBuilder requestBuilder = new RequestBuilder()
		.setUrl(apiUrl)
		.setMethod(requestData.getRequestMethod().toString())
		.addHeader("accept", "*/*")
		.addHeader("User-Agent", "OneApi-Java-".concat(SMSClient.VERSION));

		//Set content type
		if (requestData.getContentType().length() != 0) {
			requestBuilder.addHeader("Content-Type", requestData.getContentType());
		}

		try {
			//Set Authorization header
			Authentication authentication = configuration.getAuthentication();			
			if (authentication.getType().equals(AuthType.BASIC)) {
				String basicCredentials =  new String(Base64.encodeBase64((authentication.getUsername()+":"+authentication.getPassword()).getBytes("UTF-8")), "UTF-8");
				requestBuilder.addHeader("Authorization", "Basic " + basicCredentials);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Authorization type Basic using " + basicCredentials);
				}
			} else if (authentication.getType().equals(AuthType.OAUTH)) {
				String accessToken = authentication.getAccessToken();
				requestBuilder.addHeader("Authorization", "OAuth " + authentication.getAccessToken());
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Authorization type OAuth using " + accessToken);
				}
			} else if (authentication.getType().equals(AuthType.IBSSO)) {
				String ibssoToken = authentication.getIbssoToken();
				requestBuilder.addHeader("Authorization", "IBSSO " + ibssoToken);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Authorization type IBSSO using " + ibssoToken);
				}
			}
		
			//Set Request Body
			if (requestData.getFormParams() != null) {
				if (requestData.getContentType().equals(URL_ENCODED_CONTENT_TYPE)) {
					requestBuilder.setBody(formEncodeParams(requestData.getFormParams()));
				} else if (requestData.getContentType().equals(JSON_CONTENT_TYPE)) {
					String json = getObjectMapper().writeValueAsString(requestData.getFormParams());
                    requestBuilder.setBody(JsonStringEncoder.getInstance().encodeAsUTF8(json));
				}
			}

			//Execute async request
			getAsyncHttpClient().executeRequest(requestBuilder.build(), new AsyncCompletionHandler() {
				@Override
				public Response onCompleted(Response response) {
                    try {
						T jsonObject = deserialize(response, clazz, requestData.getRootElement());
						responseListener.onGotResponse(jsonObject, null);
					} catch (Exception e) {
						responseListener.onGotResponse(null, e);
					}

					return response;
				}

				@Override
				public void onThrowable(Throwable t){
					responseListener.onGotResponse(null, t);
				}
			});

		} catch (Exception e) {
			throw new RequestException(e);
		}
	}

	/**
	 * Setup http connection with custom authorization
	 * @throws MalformedURLException
	 * @throws IOException
	 */
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

	/**
	 * Deserialize response
	 * @return T
	 * @throws RequestException
	 */
	private <T> T deserialize(HttpURLConnection connection, Class<T> clazz, String rootElement) {
		int responseCode = getResponseCode(connection);
		String contentEncoding = getContentEncoding(connection.getContentType());

		if (responseCode >= 200 && responseCode < 300) {	
			try {   	
				return deserializeStream(connection.getInputStream(), contentEncoding, clazz, rootElement);

			} catch (Exception e) {
				throw new RequestException(e);	   
			} 

		} else {
			//Read RequestError from the response and throw the Exception
			throw readRequestException(connection.getErrorStream(), responseCode, contentEncoding);
		}
	}

	/**
	 * Deserialize response
	 * @return T
	 * @throws IOException
	 */
	private <T> T deserialize(Response response, Class<T> clazz, String rootElement) throws IOException {
		int responseCode = response.getStatusCode();
		InputStream inputStream = response.getResponseBodyAsStream();

		if (responseCode >= 200 && responseCode < 300) {	
			T jsonObject =  deserializeStream(inputStream, CHARSET, clazz, rootElement);
			return jsonObject;
		}

		//Read RequestError from the response and throw the Exception
		throw readRequestException(inputStream, responseCode, CHARSET);
	}

	/**
	 * Deserialize input stream
	 * @return T
	 * @throws IOException 
	 */
	private <T> T deserializeStream(InputStream inputStream, String contentEncoding, Class<T> clazz, String rootElement) throws IOException {
		LOGGER.debug("Processing JSON Response");
		byte[] bytes = read(inputStream, contentEncoding);     					
		return convertJSONToObject(bytes, clazz, rootElement);	
	}

	/**
	 * Read connection input stream bytes
	 * @return byte[]
	 * @throws IOException
	 */
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

	private RequestException readRequestException(InputStream errorStream, int responseCode, String contentEncoding) {
		LOGGER.debug("Processing RequestError JSON Response");

		String errorText = "Unexpected error occured.";
		String messageId = "";
		byte[] bytes;
		try {
			bytes = read(errorStream, contentEncoding);
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

	/**
	 * Check if response status code is valid
	 */
	private void validateResponse(HttpURLConnection connection) {
		int responseCode = getResponseCode(connection);

		if (!(responseCode >= 200 && responseCode < 300)) {
			String contentEncoding = getContentEncoding(connection.getContentType());	
			throw readRequestException(connection.getErrorStream(), responseCode, contentEncoding);	
		}
	}
	
	/**
	 * Encode specific object parameters 
	 * @return String
	 * @throws IOException
	 */
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

	/**
	 * Encode specific parameter and append it to the string builder 
	 * @throws UnsupportedEncodingException
	 */
	private void appendEncodedParam(StringBuilder sb, String key, Object value, int paramCounter) throws UnsupportedEncodingException {
		if (paramCounter > 0) {
			sb.append("&");
		}
		sb.append(URLEncoder.encode(key, CHARSET));
		sb.append("=");
		sb.append(URLEncoder.encode(String.valueOf(value), CHARSET));
	}

	/**
	 * Build url, append resourcePath to the apiUrl
	 * @param resourcePath Resource Path
	 * @return String
	 */
	private String appendMessagingBaseUrl(String resourcePath) {
		StringBuilder urlBuilder = new StringBuilder(configuration.getApiUrl());
		if (!configuration.getApiUrl().endsWith("/")) {
			urlBuilder.append("/");
		}
		urlBuilder.append(encodeURLParam(configuration.getVersionOneAPISMS()));
		if (!resourcePath.startsWith("/")) {
			urlBuilder.append("/");
		}

		urlBuilder.append(resourcePath);
		return urlBuilder.toString();
	}

	/**
	 * Get http connection response status code
	 */
	private int getResponseCode(HttpURLConnection connection) {
		try {
			return connection.getResponseCode();
		} catch (IOException e) {
			throw new RequestException(e);
		}
	}

	/**
	 * Extract content encoding from the content type
	 */
	private String getContentEncoding(String contentType) {
		String contentEncoding = "";
		if (contentType != null) {
			String[] contentTypeSplited = contentType.split(";");

			for (String splitedItem : contentTypeSplited) {
				if (splitedItem.toLowerCase().startsWith("charset=")) {
					contentEncoding = splitedItem.substring("charset=".length());
				}
			}
		}

		if (contentEncoding.length() == 0) {
			contentEncoding = CHARSET;
		}

		return contentEncoding;
	}
}
