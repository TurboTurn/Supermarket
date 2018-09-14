package view;

import java.util.Scanner;

import util.Validate;

public class CashView {
	static Scanner scan = new Scanner(System.in);

	public static String getProductCode() {
		String productCode = "";
		System.out.print("请输入商品条形码（6位数字字符）：");
		productCode = scan.next();
		while (!Validate.isProductCode(productCode)) {
			System.out.print("条形码输入格式不正确，请重新输入");
			productCode = scan.next();
		}
		return productCode;
	}

}
