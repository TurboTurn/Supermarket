package model.vo;

public class SaleDetailVo {
	private String serialnum;
	private String productCode;
	private String productName;
	private double price;
	private int amount;
	private String cashier;
	private String saletime;

	public SaleDetailVo() {
		super();
	}

	public SaleDetailVo(String serialnum, String productCode,
			String productName, double price, int amount, String cashier,
			String saletime) {
		super();
		this.serialnum = serialnum;
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.amount = amount;
		this.cashier = cashier;
		this.saletime = saletime;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getSaletime() {
		return saletime;
	}

	public void setSaletime(String saletime) {
		this.saletime = saletime;
	}

	@Override
	public String toString() {
		return serialnum + "," + productCode + "," + productName + ", " + price
				+ ", " + amount + ", " + cashier + "," + saletime;
	}

}
