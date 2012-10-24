package oneapi.model.common;

import oneapi.util.RoamingTypeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Roaming contains the result of an attempt to retrieve a single mobile terminal roaming status - either successfully or unsuccessfully
 */
public class Roaming implements java.io.Serializable {
	
	private static final long serialVersionUID = 6670681002831841088L;

	/**
	 * the MSISDN of the mobile terminal 
	 */
	private String address=null;
	/**
	 * the current roaming status with possible values: "InternationalRoaming", "DomesticRoaming", "NotRoaming"
	 */
	private String currentRoaming=null;
	/**
	 * return the MSISDN of the mobile terminal 
	 */
	public String getAddress() { return address; }
	/**
	 * set the MSISDN of the mobile terminal. This is called internally to set the contents according to the JSON response.
	 */
	public void setAddress(String address) {this.address=address; }

	/**
	 * return the status of the roaming
	 */
	public String getCurrentRoaming() { return currentRoaming; }
	/**
	 * set the status of the roaming. This is called internally to set the contents according to the JSON response.
	 */
	public void setCurrentRoaming(String currentRoaming) { this.currentRoaming=currentRoaming; }
	
	@JsonIgnore
	public void setCurrentRoaming(HlrResponseData extendedData) {
		RoamingTypeConverter roamingTypeConverter = new RoamingTypeConverter(extendedData);
		RoamingType roamingType = roamingTypeConverter.convert();
		
		this.setCurrentRoaming(roamingType.getDescription());
		
		if (roamingType == RoamingType.UNKNOWN)
			this.setRetrievalStatus("Error");
	}
	
	/**
	 * the inner class CurrentLocation contains the serving network country code/ network code 
	 */
	public static class ServingMccMnc {
		/**
		 * mobile country code
		 */
		private String mcc=null;
		/**
		 * mobile network code
		 */
		private String mnc=null;
	
		/**
		 * return mobile country code
		 */
		public String getMcc() { return mcc; }
		/**
		 * return mobile network code
		 */
		public String getMnc() { return mnc; }
		/**
		 * set mobile country code. This is called internally to set the contents according to the JSON response.  
		 */
		public void setMcc(String mcc) { this.mcc=mcc; }
		/**
		 * set mobile network code. This is called internally to set the contents according to the JSON response.  
		 */
		public void setMnc(String mnc) { this.mnc=mnc; }

		/**
		 * utility constructor to create a Roaming.ServingMccMnc object with all fields set
		 */
		public ServingMccMnc(String mcc, String mnc) {
			this.mcc = mcc;
			this.mnc = mnc;
		}
	}
	
	/**
	 * in case the terminal was successfully contacted servingMccMnc contains the connection profile details
	 */
	private ServingMccMnc servingMccMnc=null;
	/**
	 * return the location details for a successful terminal location request
	 */
	public ServingMccMnc getServingMccMnc() { return servingMccMnc; }
	/**
	 * set the location details for a successful terminal location request. This is called internally to set the contents according to the JSON response. 
	 */
	public void setServingMccMnc(ServingMccMnc servingMccMnc) { this.servingMccMnc=servingMccMnc; }
	/**
	 * set the location details for a successful terminal location request parsed from IB format. 
	 */
	@JsonIgnore
	public void setServingMccMnc(String mccMnc) {
		if (mccMnc != null && mccMnc.length() > 2) {
			this.servingMccMnc = new ServingMccMnc(mccMnc.substring(0, 3), mccMnc.substring(3));
		}
	}
		
	/**
	 * The resourceURL is a self-referring URL 
	 */
	private String resourceURL=null;
	/**
	 * return the url
	 */
	public String getResourceURL() { return resourceURL; }
	/**
	 * set the url 
	 */
	public void setResourceURL(String resourceURL) { this.resourceURL=resourceURL; }
	
	/**
	 * the response status with possible values: "Retrieved", "Error"
	 */
	private String retrievalStatus="Retrieved";
	
	public String getRetrievalStatus() {
		return retrievalStatus;
	}

	public void setRetrievalStatus(String retrievalStatus) {
		this.retrievalStatus = retrievalStatus;
	}

	/**
	 * original detailed HLR data
	 */
	private HlrResponseData extendedData;
	
	public HlrResponseData getExtendedData() {
		return extendedData;
	}
	
	public void setExtendedData(HlrResponseData extendedData) {
		this.extendedData = extendedData;
	}
	
	/**
	 * custom provided data when pushing HLR to a customer's URL
	 */
	private String callbackData = null;
	
	public String getCallbackData() {
		return callbackData;
	}
	
	public void setCallbackData(String callbackData) {
		this.callbackData = callbackData;
	}
	

	/** 
	 * generate a textual representation of the Roaming instance including all nested elements and classes 
	 */
	
	@Override
	public String toString() {
		return "Roaming {address=" + address + ", currentRoaming="
				+ currentRoaming + ", servingMccMnc=" + servingMccMnc
				+ ", resourceURL=" + resourceURL + ", retrievalStatus="
				+ retrievalStatus + ", extendedData=" + extendedData
				+ ", callbackData=" + callbackData + "}";
	}
}
