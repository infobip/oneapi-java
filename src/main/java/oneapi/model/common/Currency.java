package oneapi.model.common;

public class Currency {

	private int id;
	private String currencyName;
	private String symbol;

	public Currency(){
	}

	public void setCurrencyName(String currencyName){
		this.currencyName = currencyName;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getCurrencyName(){
		return this.currencyName;
	}

	public String getSymbol(){
		return this.symbol;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return this.id;
	}
}