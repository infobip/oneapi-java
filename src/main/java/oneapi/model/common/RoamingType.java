package oneapi.model.common;

/**
 * Roaming types as defined by OneAPI standard.
 * 
 */
public enum RoamingType {

	NOT_ROAMING("NotRoaming"),
	DOMESTIC_ROAMING("DomesticRoaming"),
	INTERNATIONAL_ROAMING("InternationalRoaming"),
	UNKNOWN(null);

	private String description;

	private RoamingType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
