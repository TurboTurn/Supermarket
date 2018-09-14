package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.dao.ProductDao;
import model.dao.SaleDetailDao;
import model.vo.ProductVo;

import view.ServiceView;
import DriverTest.Driver;

public class ServiceController {
	// 实现商品维护功能
	public static void serviceProcess() {
		int choose = 0;
		int count = 0;
		if (!Driver.user.getRole().equalsIgnoreCase("administrator")) {
			System.out.println("当前用户没有执行该项功能的权限，按任意键返回");
			(new Scanner(System.in)).next();
		} else {
			choose = ServiceView.showMenu();
			while (true) {
				switch (choose) {
				case 1:
					ArrayList<ProductVo> list = ProductDao
							.readProductFromExcel("import/product.xls");
					count = ProductDao.addProduct(list);
					if (count > 0) {
						System.out.println("成功从excel文件导入" + count + "条商品数据！");
					} else {
						System.out.println("从excel文件导入商品数据失败！");
					}
					break;
				case 2:
					ArrayList<ProductVo> list2 = ProductDao
							.readProductFromTxt("import/product.txt");
					count = ProductDao.addProduct(list2);
					if (count > 0) {
						System.out.println("成功从文本文件导入" + count + "条商品数据！");
					} else {
						System.out.println("从文本文件导入商品数据失败！");
					}
					break;
				case 3:
					ProductVo product = ServiceView.inputProduct();
					if (ProductDao.addProduct(product)) {
						System.out.println("成功添加商品信息！");
					} else {
						System.out.println("添加商品信息失败！");
					}
					break;
				case 4:
					ArrayList<ProductVo> list3 = SaleDetailDao.searchName();
					ServiceView.showProduct(list3);
					break;
				case 5:
					return;
				default:
					System.out.println("输入无效，只能输入1-5");
					break;
				}
				choose = ServiceView.showMenu();
			}
		}

	}

}
