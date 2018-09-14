package controller;

import java.util.Scanner;
import view.MenuView;

public class MenuController {

	// 主菜单进程
	public static void menuChoose() {
		int choose = MenuView.showMenu();
		while (true) {
			switch (choose) {
			case 1:
				// 1.收银
				CashController.cashProcess();
				break;
			case 2:
				// 2.查询统计
				SearchController.searchProcess();
				break;
			case 3:
				// 3.商品维护
				ServiceController.serviceProcess();
				break;
			case 4:
				// 4.修改密码
				PasswordController.changePassword();
				break;
			case 5:
				// 5.数据导出
				ExportController.exportProcess();
				break;
			case 6:
				// 6.退出
				quit();
				break;
			default:
				System.out.println("输入无效，只能输入1-6");
			}
			choose = MenuView.showMenu();
		}
	}

	// 是否确认退出系统
	public static void quit() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("您确认退出系统吗（y/n）");
			String confirm = scan.next();
			if (confirm.equalsIgnoreCase("y")) {
				System.out.println("系统已退出！欢迎下次继续使用");
				System.exit(0);
			} else if (confirm.equalsIgnoreCase("n"))
				MenuController.menuChoose();
			else
				System.out.println("输入无效，只能输入y/n");
		}
	}
}
