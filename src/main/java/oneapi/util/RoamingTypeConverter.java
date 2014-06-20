package oneapi.util;

import oneapi.model.common.HlrResponseData;
import oneapi.model.common.RoamingType;

public class RoamingTypeConverter {

	private HlrResponseData hlrData;
	
	public RoamingTypeConverter(HlrResponseData hlrData) {
		this.hlrData = hlrData;
	}
	
	/**
	 * Converts from HlrResponseData to RoamingType.
	 * Note that HlrResponseData can have some (or all) properties that are empty (equals null).
	 * @return RoamingType enum value
	 */
	public RoamingType convert() {
		
		if (hlrData.getNumberInRoaming() != null) {
			if (hlrData.getNumberInRoaming() == false) {
				return RoamingType.NOT_ROAMING;
			} else {
				return RoamingType.INTERNATIONAL_ROAMING;
			}
		}
		
		Boolean isInternationalRoaming = isInternationalRoaming();
		if (isInternationalRoaming == null) {
			return RoamingType.UNKNOWN;
		} else if (isInternationalRoaming.booleanValue()) {
			return RoamingType.INTERNATIONAL_ROAMING;
		}

		Boolean isDomesticRoaming = isDomesticRoaming();
		RoamingType ret = RoamingType.UNKNOWN;
		if (isDomesticRoaming == null) {
			ret = RoamingType.UNKNOWN;
		} else if (isDomesticRoaming == true) {
			ret = RoamingType.DOMESTIC_ROAMING;
		} else if (isDomesticRoaming == false) {
			ret= RoamingType.NOT_ROAMING;
		}
		
		return ret;
	}
	
	/**
	 * Compares roaming country to ported country. If fails then compares roaming country to original country.
	 * Note that ported country is more important than original country.
	 *  
	 * @return null if cannot compare countries
	 */
	private Boolean isInternationalRoaming() {
		Boolean ret;
		ret = compare(hlrData.getRoamingCountryCode(), hlrData.getPortedCountryCode());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingCountryPrefix(), hlrData.getPortedCountryPrefix());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingCountryName(), hlrData.getPortedCountryName());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingCountryCode(), hlrData.getOriginalCountryCode());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingCountryPrefix(), hlrData.getOriginalCountryPrefix());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingMcc(), hlrData.getMcc());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingCountryName(), hlrData.getOriginalCountryName());
		if (ret != null) return !ret;
		
		return null;
	}

	/**
	 * Compares roaming network to ported network. If it fails then compares roaming network to original network.
	 * Note that ported network is more important than original network.
	 * 
	 * @return null if cannot compare networks
	 */
	private Boolean isDomesticRoaming() {
		Boolean ret;
		ret = compare(hlrData.getRoamingNetworkPrefix(), hlrData.getPortedNetworkPrefix());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingNetworkName(), hlrData.getPortedNetworkName());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingNetworkServiceProviderId(), hlrData.getPortedNetworkServiceProviderId());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingMnc(), hlrData.getMnc());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingNetworkPrefix(), hlrData.getOriginalNetworkPrefix());
		if (ret != null) return !ret;
		ret = compare(hlrData.getRoamingNetworkName(), hlrData.getOriginalNetworkName());
		if (ret != null) return !ret;
		
		return null;
	}

	/**
	 * Compares stings a and b. Returns null if not comparable. Returns true is a and b are equal.
	 * @param a
	 * @param b
	 */
	private Boolean compare (String a, String b) {
		if (a == null || b == null)
			return null;
		return a.equalsIgnoreCase(b);
	}

	/**
	 * Compares integers a and b. Returns null if not comparable. Returns true is a and b are equal.
	 * @param a
	 * @param b
	 */
	private Boolean compare (Integer a, Integer b) {
		if (a == null || b == null)
			return null;
		return (a == b);
	}
}
