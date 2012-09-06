package oneapi.model.common;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Contains the detail of a request to obtain delivery information for SMS messages sent via the OneAPI server
 */
public class DeliveryInfoList implements java.io.Serializable {
	private static final long serialVersionUID = -916640634071165842L;
	
	/**
	 * The inner class DeliveryInfo contains pairings of the recipient address and a textual delivery status
	 */
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public static class DeliveryInfo {
		/**
		 * The address of the recipient (normally MSISDN)
		 */
		private String address=null;
		/**
		 * Delivery status may be one of
		 * "DeliveredToTerminal", successful delivery to Terminal.
		 * "DeliveryUncertain", delivery status unknown: e.g. because it was handed off to another network.
		 * "DeliveryImpossible", unsuccessful delivery; the message could not be delivered before it expired.
		 * "MessageWaiting" , the message is still queued for delivery. This is a temporary state, pending transition to one of the preceding states.
		 * "DeliveredToNetwork", successful delivery to the network enabler responsible for routing the MMS
		 */
		private String deliveryStatus=null;
		
		/**
		 * return the address of the recipient (normally MSISDN)
		 */
		public String getAddress() { return address; }
		/**
		 * return the delivery status for this recipient
		 */
		public String getDeliveryStatus() { return deliveryStatus; }
		
		/**
		 * set the recipient address. This is called internally to set the contents according to the JSON response.
		 */
		public void setAddress(String address) { this.address=address; }
		/**
		 * set the deliveryStatus. This is called internally to set the contents according to the JSON response.
		 */
		public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus=deliveryStatus; }
		
		/**
		 * default constructor
		 */
		public DeliveryInfo() {
			
		}
		
		/**
		 * utility constructor to create a DeliveryInfo instance with all fields set
		 * @param address
		 * @param deliveryStatus
		 */
		public DeliveryInfo(String address, String deliveryStatus) {
			this.address=address;
			this.deliveryStatus=deliveryStatus;
		}
		@Override
		public String toString() {
			return "DeliveryInfo {address=" + address + ", deliveryStatus="
					+ deliveryStatus + "}";
		}
	}
		
	/** 
	 * the deliveryInfoList object contains the delivery information for each address that you asked to send the message to, in a deliveryInfo array.
	 */
	List<DeliveryInfo> deliveryInfo=null;
	/**
	 * return the array of deliveryInfo pairs of address/ deliveryStatus
	 */
	public List<DeliveryInfo> getDeliveryInfo() { return deliveryInfo; }
	/**
	 * set the array of deliveryInfo pairs of address/ deliveryStatus. This is called internally to set the contents according to the JSON response. 
	 */
	public void setDeliveryInfo(List<DeliveryInfo> deliveryInfo) { this.deliveryInfo=deliveryInfo; }

	/**
	 * resourceURL contains a URL uniquely identifying this DeliveryInfoList request
	 */
	String resourceURL=null;
	/**
	 * return resourceURL containing a URL uniquely identifying this DeliveryInfoList request
	 */
	public String getResourceURL() { return resourceURL; }
	/**
	 * set resourceURL containing a URL uniquely identifying this DeliveryInfoList request. This is called internally to set the contents according to the JSON response. 
	 */
	public void setResourceURL(String resourceURL) { this.resourceURL=resourceURL; }
	
	/** 
	 * generate a textual representation of the DeliveryInfoList instance including nested elements and classes 
	 */
	@Override
	public String toString() {
		return "DeliveryInfoList {deliveryInfo=" + deliveryInfo
				+ ", resourceURL=" + resourceURL + "}";
	}
}
