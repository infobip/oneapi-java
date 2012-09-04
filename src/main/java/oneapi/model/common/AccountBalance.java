package oneapi.model.common;

public class AccountBalance {

	/**
	 * Serial Version UID
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private java.math.BigDecimal balance;

	private Currency currency;

	public AccountBalance() {
		super();
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public java.math.BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(java.math.BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountBalance {balance=" + balance + ", currency=" + currency
				+ "}";
	}
}
