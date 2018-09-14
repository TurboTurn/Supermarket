package view;

import java.util.ArrayList;
import java.util.Scanner;

import util.Validate;

import model.dao.ProductDao;
import model.vo.ProductVo;

public class ServiceView {
	// ��ʾ��Ʒά�����ܵĲ˵���Ϣ
	public static int showMenu() {
		int choice = 0;
		System.out.println("===****������Ʒ����ά��====");
		System.out.println("1����excel�е�������");
		System.out.println("2�����ı��ļ���������");
		System.out.println("3����������");
		System.out.println("4������Ʒ���Ʋ�ѯ");
		System.out.println("5���������˵�");
		System.out.println("��ѡ��1-5����");
		Scanner scan = new Scanner(System.in);
		choice = scan.nextInt();
		return choice;
	}

	// ���û�����������Ʒ��Ϣ
	public static ProductVo inputProduct() {
		String info;
		ProductVo product = new ProductVo();
		System.out.println("��������Ʒ��Ϣ��100001 ��� 300 �Ϳˣ�");
		Scanner scan = new Scanner(System.in);
		info = scan.nextLine();
		String array[] = info.split(" ");
		while (!Validate.isProductCode(array[0]) | array.length < 4) {
			System.out.println("����������ݸ�ʽ����ȷ������������");
			info = scan.nextLine();
			array = info.split(" ");
		}
		if (ProductDao.existProduct(array[0])) {
			System.out.println("�����벻���ظ�������������");
			product = inputProduct();
		} else {
			double price = Double.parseDouble(array[2]);
			product = new ProductVo(array[0], array[1], price, array[3]);
		}
		return product;
	}

	// ��ʾ��Ʒ��ѯ���
	public static void showProduct(ArrayList<ProductVo> list) {
		int count = 0;
		if (list.size() > 0) {
			System.out.println("���������ļ�¼�ܹ�" + list.size() + "������Ϣ���£�");
			System.out.println("���\t������\t��Ʒ����\t����\t��Ӧ��");
			System.out
					.println("==============================================");
			for (int i = 0; i < list.size(); i++) {
				count++;
				System.out.println(count + "\t" + list.get(i).getProductCode()
						+ "\t" + list.get(i).getProductName() + "\t"
						+ list.get(i).getPrice() + "\t"
						+ list.get(i).getSupplier());
			}
		} else {
			System.out.println("��Ʒ����û�����Ƶ���Ʒ��");
		}
	}

}
