package view;

import java.util.Scanner;

import DriverTest.Driver;

//ʵ�ֲ˵�����
public class MenuView {
	public static int showMenu() {
		int choose = 0;
		System.out.println("----****��������ϵͳ----");
		System.out.println("1.����");
		System.out.println("2.��ѯͳ��");
		System.out.println("3.��Ʒά��");
		System.out.println("4.�޸�����");
		System.out.println("5.���ݵ���");
		System.out.println("6.�˳�");
		System.out.println("��ǰ����Ա��" + Driver.user.getChName());
		System.out.println("��ѡ��1-6����");
		Scanner scan = new Scanner(System.in);
		choose = scan.nextInt();
		return choose;
	}

}
