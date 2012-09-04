package oneapi.model.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * reference to a resource created by the OneAPI server - in the form of a generated URL
 */

@XmlRootElement
public class ResourceReference {
	/**
	 * contains a URL uniquely identifying a successful request to the OneAPI server
	 */	
	private String resourceURL=null;
	
	public ResourceReference() {
		super();
	}
	
	public ResourceReference(String resourceURL) { this.resourceURL=resourceURL; }
	
	/**
	 * return a URL uniquely identifying a successful OneAPI server request
	 */
	public String getResourceURL() { return resourceURL; }
	/**
	 * set a URL uniquely identifying a successful OneAPI server request. This is called internally to set the contents according to the JSON response.
	 */
	public void setResourceURL(String resourceURL) { this.resourceURL=resourceURL; }
	
	/** 
	 * generate a textual representation of the ResourceReference 
	 */
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("resourceURL = ");
		buffer.append(resourceURL);
		return buffer.toString();
	}
}
