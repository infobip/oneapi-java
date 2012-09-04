package oneapi.model.common;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class HlrResponseData implements Serializable {
	private static final long serialVersionUID = -5229756379388281156L;

	private String destinationAddress;
	private int statusId;
	private Date submitTime;
	private Date doneTime;
	private Double pricePerMessage;
	private String mccMnc;
	private String mcc;
	private String mnc;

	
	private String servingMsc;
	private String censoredServingMsc;

	private int gsmErrorCode;

	private String originalNetworkName; // Recipient number original network
	private String portedNetworkName; // If the number has been ported, return info about ported network
	private String roamingNetworkName; // Current visited network (if the recipient is roaming)
	private String roamingCountryCode; // Current visited network country (if the recipient is roaming)
	private String roamingCountryName;

	private String servingHlr;
	
	private String imsi;

	private String originalNetworkPrefix; // Recipient network prefix
	private String originalCountryPrefix; // Recipient network country prefix
	private String originalCountryCode; // Recipient network country code
	private String originalCountryName; // Recipient network country name
	private String roamingNetworkPrefix; // Current visited network prefix (if the recipient is roaming)
	private String roamingCountryPrefix; // Current visited network country prefix (if the recipient is roaming)

	private Boolean isNumberPorted; // If the number has been ported returns true

	private String portedNetworkPrefix;
	private String portedCountryCode;
	private String portedCountryPrefix;
	private String portedCountryName;

	private String roamingMccMnc;
	private String roamingMcc;
	private String roamingMnc;

	private Boolean numberInRoaming;
	private Boolean isNumberCorrect;
	
	private Integer originalNetworkServiceProviderId;
	private Integer portedNetworkServiceProviderId;
	private Integer roamingNetworkServiceProviderId;

	private String originalNetworkServiceProviderName;
	private String portedNetworkServiceProviderName;
	private String roamingNetworkServiceProviderName;

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}

	public Double getPricePerMessage() {
		return pricePerMessage;
	}

	public void setPricePerMessage(Double pricePerMessage) {
		this.pricePerMessage = pricePerMessage;
	}

	public String getMccMnc() {
		return mccMnc;
	}

	public void setMccMnc(String mccmnc) {
		this.mccMnc = mccmnc;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	
	public String getServingMsc() {
		return servingMsc;
	}

	public void setServingMsc(String servingMsc) {
		this.servingMsc = servingMsc;
	}

	public int getGsmErrorCode() {
		return gsmErrorCode;
	}

	public void setGsmErrorCode(int gsmErrorCode) {
		this.gsmErrorCode = gsmErrorCode;
	}

	public String getOriginalNetworkName() {
		return originalNetworkName;
	}

	public void setOriginalNetworkName(String originalNetworkName) {
		this.originalNetworkName = originalNetworkName;
	}

	public String getPortedNetworkName() {
		return portedNetworkName;
	}

	public void setPortedNetworkName(String portedNetworkName) {
		this.portedNetworkName = portedNetworkName;
	}

	public String getRoamingNetworkName() {
		return roamingNetworkName;
	}

	public void setRoamingNetworkName(String roamingNetworkName) {
		this.roamingNetworkName = roamingNetworkName;
	}

	public String getRoamingCountryCode() {
		return roamingCountryCode;
	}

	public void setRoamingCountryCode(String roamingCountryCode) {
		this.roamingCountryCode = roamingCountryCode;
	}

	public String getRoamingCountryName() {
		return roamingCountryName;
	}

	public void setRoamingCountryName(String roamingCountryName) {
		this.roamingCountryName = roamingCountryName;
	}

	public String getServingHlr() {
		return servingHlr;
	}

	public void setServingHlr(String servingHlr) {
		this.servingHlr = servingHlr;
	}

	public String getOriginalNetworkPrefix() {
		return originalNetworkPrefix;
	}

	public void setOriginalNetworkPrefix(String originalNetworkPrefix) {
		this.originalNetworkPrefix = originalNetworkPrefix;
	}

	public String getOriginalCountryPrefix() {
		return originalCountryPrefix;
	}

	public void setOriginalCountryPrefix(String originalCountryPrefix) {
		this.originalCountryPrefix = originalCountryPrefix;
	}

	public String getOriginalCountryCode() {
		return originalCountryCode;
	}

	public void setOriginalCountryCode(String originalCountryCode) {
		this.originalCountryCode = originalCountryCode;
	}

	public String getOriginalCountryName() {
		return originalCountryName;
	}

	public void setOriginalCountryName(String originalCountryName) {
		this.originalCountryName = originalCountryName;
	}

	public String getRoamingNetworkPrefix() {
		return roamingNetworkPrefix;
	}

	public void setRoamingNetworkPrefix(String roamingNetworkPrefix) {
		this.roamingNetworkPrefix = roamingNetworkPrefix;
	}

	public String getRoamingCountryPrefix() {
		return roamingCountryPrefix;
	}

	public void setRoamingCountryPrefix(String roamingCountryPrefix) {
		this.roamingCountryPrefix = roamingCountryPrefix;
	}

	public Boolean getIsNumberPorted() {
		return isNumberPorted;
	}

	public void setIsNumberPorted(Boolean isNumberPorted) {
		this.isNumberPorted = isNumberPorted;
	}

	public String getPortedNetworkPrefix() {
		return portedNetworkPrefix;
	}

	public void setPortedNetworkPrefix(String portedNetworkPrefix) {
		this.portedNetworkPrefix = portedNetworkPrefix;
	}

	public String getPortedCountryCode() {
		return portedCountryCode;
	}

	public void setPortedCountryCode(String portedCountryCode) {
		this.portedCountryCode = portedCountryCode;
	}

	public String getPortedCountryPrefix() {
		return portedCountryPrefix;
	}

	public void setPortedCountryPrefix(String portedCountryPrefix) {
		this.portedCountryPrefix = portedCountryPrefix;
	}

	public String getPortedCountryName() {
		return portedCountryName;
	}

	public void setPortedCountryName(String portedCountryName) {
		this.portedCountryName = portedCountryName;
	}

	public String getRoamingMccMnc() {
		return roamingMccMnc;
	}

	public void setRoamingMccMnc(String roamingMccMnc) {
		this.roamingMccMnc = roamingMccMnc;
	}

	public String getRoamingMcc() {
		return roamingMcc;
	}

	public void setRoamingMcc(String roamingMcc) {
		this.roamingMcc = roamingMcc;
	}

	public String getRoamingMnc() {
		return roamingMnc;
	}

	public void setRoamingMnc(String roamingMnc) {
		this.roamingMnc = roamingMnc;
	}

	public Boolean getNumberInRoaming() {
		return numberInRoaming;
	}

	public void setNumberInRoaming(Boolean numberInRoaming) {
		this.numberInRoaming = numberInRoaming;
	}
	
	public void setIsNumberCorrect(Boolean isNumberCorrect) {
		this.isNumberCorrect = isNumberCorrect;
	}
	
	public Boolean getIsNumberCorrect() {
		return isNumberCorrect;
	}

	public Integer getOriginalNetworkServiceProviderId() {
		return originalNetworkServiceProviderId;
	}

	public void setOriginalNetworkServiceProviderId(Integer originalNetworkServiceProviderId) {
		this.originalNetworkServiceProviderId = originalNetworkServiceProviderId;
	}

	public Integer getPortedNetworkServiceProviderId() {
		return portedNetworkServiceProviderId;
	}

	public void setPortedNetworkServiceProviderId(Integer portedNetworkServiceProviderId) {
		this.portedNetworkServiceProviderId = portedNetworkServiceProviderId;
	}

	public Integer getRoamingNetworkServiceProviderId() {
		return roamingNetworkServiceProviderId;
	}

	public void setRoamingNetworkServiceProviderId(Integer roamingNetworkServiceProviderId) {
		this.roamingNetworkServiceProviderId = roamingNetworkServiceProviderId;
	}

	public String getOriginalNetworkServiceProviderName() {
		return originalNetworkServiceProviderName;
	}

	public void setOriginalNetworkServiceProviderName(String originalNetworkServiceProviderName) {
		this.originalNetworkServiceProviderName = originalNetworkServiceProviderName;
	}

	public String getPortedNetworkServiceProviderName() {
		return portedNetworkServiceProviderName;
	}

	public void setPortedNetworkServiceProviderName(String portedNetworkServiceProviderName) {
		this.portedNetworkServiceProviderName = portedNetworkServiceProviderName;
	}

	public String getRoamingNetworkServiceProviderName() {
		return roamingNetworkServiceProviderName;
	}

	public void setRoamingNetworkServiceProviderName(String roamingNetworkServiceProviderName) {
		this.roamingNetworkServiceProviderName = roamingNetworkServiceProviderName;
	}
	
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	public String getImsi() {
		return imsi;
	}
	
	public void setCensoredServingMsc(String censoredServingMsc) {
		this.censoredServingMsc = censoredServingMsc;
	}
	
	public String getCensoredServingMsc() {
		return censoredServingMsc;
	}

	@Override
	public String toString() {
		return "HlrResponseData {destinationAddress=" + destinationAddress
				+ ", statusId=" + statusId + ", submitTime=" + submitTime
				+ ", doneTime=" + doneTime + ", pricePerMessage="
				+ pricePerMessage + ", mccMnc=" + mccMnc + ", mcc=" + mcc
				+ ", mnc=" + mnc + ", servingMsc=" + servingMsc
				+ ", censoredServingMsc=" + censoredServingMsc
				+ ", gsmErrorCode=" + gsmErrorCode + ", originalNetworkName="
				+ originalNetworkName + ", portedNetworkName="
				+ portedNetworkName + ", roamingNetworkName="
				+ roamingNetworkName + ", roamingCountryCode="
				+ roamingCountryCode + ", roamingCountryName="
				+ roamingCountryName + ", servingHlr=" + servingHlr + ", imsi="
				+ imsi + ", originalNetworkPrefix=" + originalNetworkPrefix
				+ ", originalCountryPrefix=" + originalCountryPrefix
				+ ", originalCountryCode=" + originalCountryCode
				+ ", originalCountryName=" + originalCountryName
				+ ", roamingNetworkPrefix=" + roamingNetworkPrefix
				+ ", roamingCountryPrefix=" + roamingCountryPrefix
				+ ", isNumberPorted=" + isNumberPorted
				+ ", portedNetworkPrefix=" + portedNetworkPrefix
				+ ", portedCountryCode=" + portedCountryCode
				+ ", portedCountryPrefix=" + portedCountryPrefix
				+ ", portedCountryName=" + portedCountryName
				+ ", roamingMccMnc=" + roamingMccMnc + ", roamingMcc="
				+ roamingMcc + ", roamingMnc=" + roamingMnc
				+ ", numberInRoaming=" + numberInRoaming + ", isNumberCorrect="
				+ isNumberCorrect + ", originalNetworkServiceProviderId="
				+ originalNetworkServiceProviderId
				+ ", portedNetworkServiceProviderId="
				+ portedNetworkServiceProviderId
				+ ", roamingNetworkServiceProviderId="
				+ roamingNetworkServiceProviderId
				+ ", originalNetworkServiceProviderName="
				+ originalNetworkServiceProviderName
				+ ", portedNetworkServiceProviderName="
				+ portedNetworkServiceProviderName
				+ ", roamingNetworkServiceProviderName="
				+ roamingNetworkServiceProviderName + "}";
	}
}
