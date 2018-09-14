package controller;

import java.util.Scanner;

import model.dao.ProductDao;
import model.dao.SaleDetailDao;
import model.vo.ProductVo;
import view.CashView;

public class CashController {
	// 控制收银进程的逻辑功能
	public static void cashProcess() {
		Scanner scan = new Scanner(System.in);
		int amount = 0;
		String productCode = CashView.getProductCode();
		if (ProductDao.existProduct(productCode)) {
			ProductVo product = ProductDao.queryProduct(productCode);
			System.out.print("请输入商品数量：");
			amount = scan.nextInt();
			SaleDetailDao.addDetail(amount, product);

		} else {
			System.out.println("您输入的商品条形码不存在，请确认后重新输入");
			CashController.cashProcess();
		}
	}

}
