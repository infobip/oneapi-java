package oneapi.model.common;

import java.io.Serializable;

/**
 * Confirms the details of a successful request to subscribe to SMS delivery receipts
 */
public class DeliveryReceiptSubscription implements Serializable {

	private static final long serialVersionUID = 7647416372355131397L;
	
	/**
	 * inner class CallbackReference details details the URL of the page/ service to notify and additional data that will be sent 
	 */
	public static class CallbackReference {
		/**
		 * details user requested data to be sent along with the callback notification
		 */
		String callbackData=null;
		/** 
		 * the page / service to send the notification to
		 */
		String notifyURL=null;
		
		/**
		 * return the user data that will be sent along with the callback notification
		 */
		public String getCallbackData() { return callbackData; }
		/**
		 * return the URL of the page / service to send the notification to
		 */
		public String getNotifyURL() { return notifyURL; }
		
		/**
		 * set the user data field that will be sent along with the callback notification
		 */
		public void setCallbackData(String callbackData) { this.callbackData=callbackData; }
		/**
		 * set the URL for the page / service to send the notification to
		 */
		public void setNotifyURL(String notifyURL) { this.notifyURL=notifyURL; }
		
		/**
		 * default constructor
		 */
		public CallbackReference() {
			
		}
		
		/**
		 * alternate constructor setting both callbackData and notifyURL
		 */
		public CallbackReference(String callbackData, String notifyURL) {
			this.callbackData=callbackData;
			this.notifyURL=notifyURL;
		}
		
		/** 
		 * generate a textual representation of the CallbackReference  
		 */	
		@Override
		public String toString() {
			return "CallbackReference {callbackData=" + callbackData
					+ ", notifyURL=" + notifyURL + "}";
		}
	}
	
	/**
	 * reference to the inner callbackReference class - the notification URL and user supplied callback data
	 */
	CallbackReference callbackReference=null;
	/** 
	 * get the reference to the inner callbackReference class - the notification URL and user supplied callback data 
	 * @return CallbackReference
	 */
	public CallbackReference getCallbackReference() { return callbackReference; }
	/**
	 * set the reference to the inner callbackReference class. This is called internally to set the contents according to the JSON response.
	 */
	public void setCallbackReference(CallbackReference callbackReference) { this.callbackReference=callbackReference; }
	
	/**
	 * resourceURL contains a URL uniquely identifying this SMS delivery receipt subscription
	 */		
	private String resourceURL=null;	
	/**
	 * return resourceURL - a URL uniquely identifying this SMS delivery receipt subscription
	 */
	public String getResourceURL() { return resourceURL; }
	/**
	 * set resourceURL, the URL uniquely identifying a successful request to subscribe to SMS delivery receipt subscriptions. This is called internally to set the contents according to the JSON response.
	 */
	public void setResourceURL(String resourceURL) { this.resourceURL=resourceURL; }
	
	/** 
	 * generate a textual representation of the deliveryReceiptSubscription instance including nested elements and classes 
	 */
	@Override
	public String toString() {
		return "DeliveryReceiptSubscription {callbackReference="
				+ callbackReference + ", resourceURL=" + resourceURL + "}";
	}
}
