package controller;

import java.util.Scanner;
import view.MenuView;

public class MenuController {

	// ���˵�����
	public static void menuChoose() {
		int choose = MenuView.showMenu();
		while (true) {
			switch (choose) {
			case 1:
				// 1.����
				CashController.cashProcess();
				break;
			case 2:
				// 2.��ѯͳ��
				SearchController.searchProcess();
				break;
			case 3:
				// 3.��Ʒά��
				ServiceController.serviceProcess();
				break;
			case 4:
				// 4.�޸�����
				PasswordController.changePassword();
				break;
			case 5:
				// 5.���ݵ���
				ExportController.exportProcess();
				break;
			case 6:
				// 6.�˳�
				quit();
				break;
			default:
				System.out.println("������Ч��ֻ������1-6");
			}
			choose = MenuView.showMenu();
		}
	}

	// �Ƿ�ȷ���˳�ϵͳ
	public static void quit() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("��ȷ���˳�ϵͳ��y/n��");
			String confirm = scan.next();
			if (confirm.equalsIgnoreCase("y")) {
				System.out.println("ϵͳ���˳�����ӭ�´μ���ʹ��");
				System.exit(0);
			} else if (confirm.equalsIgnoreCase("n"))
				MenuController.menuChoose();
			else
				System.out.println("������Ч��ֻ������y/n");
		}
	}
}
