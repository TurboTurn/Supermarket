package view;

import java.util.Scanner;

import util.Validate;

public class CashView {
	static Scanner scan = new Scanner(System.in);

	public static String getProductCode() {
		String productCode = "";
		System.out.print("��������Ʒ�����루6λ�����ַ�����");
		productCode = scan.next();
		while (!Validate.isProductCode(productCode)) {
			System.out.print("�����������ʽ����ȷ������������");
			productCode = scan.next();
		}
		return productCode;
	}

}
