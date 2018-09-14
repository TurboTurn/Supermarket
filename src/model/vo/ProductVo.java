package model.vo;

public class ProductVo {
	private String productCode;
	private String productName;
	private double price;
	private String supplier;

	public ProductVo() {
		super();
	}

	public ProductVo(String productCode, String productName, double price,
			String supplier) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.supplier = supplier;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
