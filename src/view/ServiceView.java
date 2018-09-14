package view;

import java.util.ArrayList;
import java.util.Scanner;

import util.Validate;

import model.dao.ProductDao;
import model.vo.ProductVo;

public class ServiceView {
	// 显示商品维护功能的菜单信息
	public static int showMenu() {
		int choice = 0;
		System.out.println("===****超市商品管理维护====");
		System.out.println("1、从excel中导入数据");
		System.out.println("2、从文本文件导入数据");
		System.out.println("3、键盘输入");
		System.out.println("4、按商品名称查询");
		System.out.println("5、返回主菜单");
		System.out.println("请选择（1-5）：");
		Scanner scan = new Scanner(System.in);
		choice = scan.nextInt();
		return choice;
	}

	// 从用户界面输入商品信息
	public static ProductVo inputProduct() {
		String info;
		ProductVo product = new ProductVo();
		System.out.println("请输入商品信息（100001 书包 300 耐克）");
		Scanner scan = new Scanner(System.in);
		info = scan.nextLine();
		String array[] = info.split(" ");
		while (!Validate.isProductCode(array[0]) | array.length < 4) {
			System.out.println("你输入的数据格式不正确，请重新输入");
			info = scan.nextLine();
			array = info.split(" ");
		}
		if (ProductDao.existProduct(array[0])) {
			System.out.println("条形码不能重复，请重新输入");
			product = inputProduct();
		} else {
			double price = Double.parseDouble(array[2]);
			product = new ProductVo(array[0], array[1], price, array[3]);
		}
		return product;
	}

	// 显示商品查询结果
	public static void showProduct(ArrayList<ProductVo> list) {
		int count = 0;
		if (list.size() > 0) {
			System.out.println("满足条件的记录总共" + list.size() + "条，信息如下：");
			System.out.println("序号\t条形码\t商品名称\t单价\t供应商");
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
			System.out.println("商品库中没有类似的商品！");
		}
	}

}
