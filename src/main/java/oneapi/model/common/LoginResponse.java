package oneapi.model.common;

public class LoginResponse {
    private boolean verified;
    private String ibAuthCookie;

    public LoginResponse() {
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getIbAuthCookie() {
        return ibAuthCookie;
    }

    public void setIbAuthCookie(String ibAuthCookie) {
        this.ibAuthCookie = ibAuthCookie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResponse that = (LoginResponse) o;

        if (ibAuthCookie != null ? !ibAuthCookie.equals(that.ibAuthCookie) : that.ibAuthCookie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return ibAuthCookie != null ? ibAuthCookie.hashCode() : 0;
    }

	@Override
	public String toString() {
		return "LoginResponse {verified=" + verified + ", ibAuthCookie="
				+ ibAuthCookie + "}";
	}
}
