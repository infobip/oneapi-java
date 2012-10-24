package oneapi.model;

import oneapi.model.common.ResourceReference;

public class SendMessageResult {

	private String clientCorrelator;
	private SendMessageResultItem[] sendMessageResults;
	private ResourceReference resourceReference;
	
	public SendMessageResult() {
		super();
	}

	public String getClientCorrelator() {
		return clientCorrelator;
	}

	public void setClientCorrelator(String clientCorrelator) {
		this.clientCorrelator = clientCorrelator;
	}

	public SendMessageResultItem[] getSendMessageResults() {
		return sendMessageResults;
	}

	public void setSendMessageResults(SendMessageResultItem[] sendMessageResults) {
		this.sendMessageResults = sendMessageResults;
	}

	public ResourceReference getResourceReference() {
		return resourceReference;
	}

	public void setResourceReference(ResourceReference resourceReference) {
		this.resourceReference = resourceReference;
	}

}
