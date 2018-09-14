package view;

import java.util.Scanner;

import DriverTest.Driver;

//实现菜单界面
public class MenuView {
	public static int showMenu() {
		int choose = 0;
		System.out.println("----****超市收银系统----");
		System.out.println("1.收银");
		System.out.println("2.查询统计");
		System.out.println("3.商品维护");
		System.out.println("4.修改密码");
		System.out.println("5.数据导出");
		System.out.println("6.退出");
		System.out.println("当前收银员：" + Driver.user.getChName());
		System.out.println("请选择（1-6）：");
		Scanner scan = new Scanner(System.in);
		choose = scan.nextInt();
		return choose;
	}

}
