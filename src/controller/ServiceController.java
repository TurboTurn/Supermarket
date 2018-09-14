package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.dao.ProductDao;
import model.dao.SaleDetailDao;
import model.vo.ProductVo;

import view.ServiceView;
import DriverTest.Driver;

public class ServiceController {
	// ʵ����Ʒά������
	public static void serviceProcess() {
		int choose = 0;
		int count = 0;
		if (!Driver.user.getRole().equalsIgnoreCase("administrator")) {
			System.out.println("��ǰ�û�û��ִ�и���ܵ�Ȩ�ޣ������������");
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
						System.out.println("�ɹ���excel�ļ�����" + count + "����Ʒ���ݣ�");
					} else {
						System.out.println("��excel�ļ�������Ʒ����ʧ�ܣ�");
					}
					break;
				case 2:
					ArrayList<ProductVo> list2 = ProductDao
							.readProductFromTxt("import/product.txt");
					count = ProductDao.addProduct(list2);
					if (count > 0) {
						System.out.println("�ɹ����ı��ļ�����" + count + "����Ʒ���ݣ�");
					} else {
						System.out.println("���ı��ļ�������Ʒ����ʧ�ܣ�");
					}
					break;
				case 3:
					ProductVo product = ServiceView.inputProduct();
					if (ProductDao.addProduct(product)) {
						System.out.println("�ɹ������Ʒ��Ϣ��");
					} else {
						System.out.println("�����Ʒ��Ϣʧ�ܣ�");
					}
					break;
				case 4:
					ArrayList<ProductVo> list3 = SaleDetailDao.searchName();
					ServiceView.showProduct(list3);
					break;
				case 5:
					return;
				default:
					System.out.println("������Ч��ֻ������1-5");
					break;
				}
				choose = ServiceView.showMenu();
			}
		}

	}

}
