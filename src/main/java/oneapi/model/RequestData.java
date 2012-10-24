package oneapi.model;

public class RequestData {

	private String resourcePath = "";
	private Method requestMethod;
	private String rootElement = "";
	private Object formParams = null;
	private String contentType = "";

	/**
	 * Supported protocol types
	 */
	public enum Method {
		POST, GET, DELETE;
	};

	public RequestData(String resourcePath, Method requestMethod)
	{
		this.resourcePath = resourcePath;
		this.requestMethod = requestMethod;
	}
		
	public RequestData(String resourcePath, Method requestMethod, String rootElement)
	{
		this(resourcePath, requestMethod);
		this.rootElement = rootElement;
	}
	
	public RequestData(String resourcePath, Method requestMethod, String rootElement, Object formParams)
	{
		this(resourcePath, requestMethod, rootElement);
		this.formParams = formParams;
	}
	
	public RequestData(String resourcePath, Method requestMethod, String rootElement, Object formParams, String contentType)
	{
		this(resourcePath, requestMethod, rootElement, formParams);
		this.contentType = contentType;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public Method getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(Method requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRootElement() {
		return rootElement;
	}

	public void setRootElement(String rootElement) {
		this.rootElement = rootElement;
	}

	public Object getFormParams() {
		return formParams;
	}

	public void setFormParams(Object formParams) {
		this.formParams = formParams;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}     
}
