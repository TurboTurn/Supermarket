package view;

import java.util.Scanner;

import util.Encription;

import model.vo.UserVo;

//ʵ�ֵ�¼�Ľ���
public class LoginView {
	// ʵ�ֽ������ʾ��������������ݣ�����ĳ����ʽ�ķ�װ
	public static UserVo show() {
		System.out.println("��ӭʹ��****��������ϵͳ�����¼");
		Scanner scan = new Scanner(System.in);
		System.out.println("�������û�����");
		String userName = scan.next();
		System.out.println("���������룺");
		String password = scan.next();
		String miwen = Encription.md5(password);
		UserVo user = new UserVo(userName, miwen);
		return user;
	}

}
