package oneapi.model.common;


/**
 * reference to a resource created by the OneAPI server - in the form of a generated URL
 */

public class ResourceReferenceWrapper {
	
	private ResourceReference ref=null;
	
	public ResourceReferenceWrapper(String resourceURL) {
		ref = new ResourceReference(resourceURL);
	}
	
	public ResourceReference getResourceReference() { return ref; }
	
	public void setResourceReference(ResourceReference ref) { this.ref=ref; }
}