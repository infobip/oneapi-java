package oneapi.model;

public class RequestData {

	private String resourcePath = "";
	private int requiredStatus;
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

	public RequestData(String resourcePath, int requiredStatus, Method requestMethod)
	{
		this.resourcePath = resourcePath;
		this.requiredStatus = requiredStatus;
		this.requestMethod = requestMethod;
	}

//	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, Object formParams)
//	{
//		this(resourcePath, requiredStatus, requestMethod);
//		this.formParams = formParams;
//	}
//
//	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement)
//	{
//		this(resourcePath, requiredStatus, requestMethod);
//		this.rootElement = rootElement;
//	}
//
//	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement, Object formParams)
//	{
//		this(resourcePath, requiredStatus, requestMethod, rootElement);
//		this.formParams = formParams;
//	}
	
//	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement, Object formParams)
//	{
//		this(resourcePath, requiredStatus, requestMethod, rootElement);
//		this.formParams = formParams;
//	}
	
	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement)
	{
		this(resourcePath, requiredStatus, requestMethod);
		this.rootElement = rootElement;
	}
	
	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement, Object formParams)
	{
		this(resourcePath, requiredStatus, requestMethod, rootElement);
		this.formParams = formParams;
	}
	
	public RequestData(String resourcePath, int requiredStatus, Method requestMethod, String rootElement, Object formParams, String contentType)
	{
		this(resourcePath, requiredStatus, requestMethod, rootElement, formParams);
		this.contentType = contentType;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public int getRequiredStatus() {
		return requiredStatus;
	}

	public void setRequiredStatus(int requiredStatus) {
		this.requiredStatus = requiredStatus;
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
