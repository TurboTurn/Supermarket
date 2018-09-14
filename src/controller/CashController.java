package controller;

import java.util.Scanner;

import model.dao.ProductDao;
import model.dao.SaleDetailDao;
import model.vo.ProductVo;
import view.CashView;

public class CashController {
	// �����������̵��߼�����
	public static void cashProcess() {
		Scanner scan = new Scanner(System.in);
		int amount = 0;
		String productCode = CashView.getProductCode();
		if (ProductDao.existProduct(productCode)) {
			ProductVo product = ProductDao.queryProduct(productCode);
			System.out.print("��������Ʒ������");
			amount = scan.nextInt();
			SaleDetailDao.addDetail(amount, product);

		} else {
			System.out.println("���������Ʒ�����벻���ڣ���ȷ�Ϻ���������");
			CashController.cashProcess();
		}
	}

}
