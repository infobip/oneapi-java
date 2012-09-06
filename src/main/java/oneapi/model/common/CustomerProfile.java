package oneapi.model.common;

public class CustomerProfile {
	private int id;

	private String username;
	private String forename;
	private String surname;
	private String street;
	private String city;
	private String zipCode;
	private String telephone;
	private String gsm;
	private String fax;
	private String email;
	private String msn;
	private String skype;
    private Integer countryId;
    private Integer timezoneId;
    private Integer primaryLanguageId;
    private Integer secondaryLanguageId;
    private boolean enabled;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

	public Integer getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(Integer timezoneId) {
		this.timezoneId = timezoneId;
	}

	public Integer getPrimaryLanguageId() {
		return primaryLanguageId;
	}

	public void setPrimaryLanguageId(Integer primaryLanguageId) {
		this.primaryLanguageId = primaryLanguageId;
	}

	public Integer getSecondaryLanguageId() {
		return secondaryLanguageId;
	}

	public void setSecondaryLanguageId(Integer secondaryLamguageId) {
		this.secondaryLanguageId = secondaryLamguageId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "CustomerProfile {id=" + id + ", username=" + username
				+ ", forename=" + forename + ", surname=" + surname
				+ ", street=" + street + ", city=" + city + ", zipCode="
				+ zipCode + ", telephone=" + telephone + ", gsm=" + gsm
				+ ", fax=" + fax + ", email=" + email + ", msn=" + msn
				+ ", skype=" + skype + ", countryId=" + countryId
				+ ", timezoneId=" + timezoneId + ", primaryLanguageId="
				+ primaryLanguageId + ", secondaryLanguageId="
				+ secondaryLanguageId + ", enabled=" + enabled + "}";
	}
}
