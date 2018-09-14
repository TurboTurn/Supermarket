package controller;

import java.util.Scanner;

import model.dao.UserDao;

import util.Encription;
import util.Validate;

import DriverTest.Driver;

public class PasswordController {
	// �޸��������
	public static void changePassword() {
		Scanner scan = new Scanner(System.in);
		String newPassword = null;
		System.out.println("�����뵱ǰ�û���ԭ���룺");
		String password = scan.next();
		// ��֤�����Ƿ�������ȷ
		while (!Encription.md5(password).equals(Driver.user.getPassword())) {
			System.out.println("ԭ�������벻��ȷ������������:");
			password = scan.next();
		}
		// ����������
		newPassword = setPassword();
		// ִ�����ݿ��и�������MD5����
		if (UserDao.updatePassword(Encription.md5(newPassword))) {
			Driver.user.setPassword(Encription.md5(newPassword));
			System.out.println("���ѳɹ��޸����룬�����");
			System.out.println("�����������");
			scan.next();
		}
	}

	// ����������
	public static String setPassword() {
		Scanner scan = new Scanner(System.in);
		String newPassword = null, verifyPassword = null, password = null;
		System.out.println("�������µ����룺");
		newPassword = scan.next();
		// �ж����븴�Ӷ�
		// ���벻����Ҫ����������
		if (!Validate.isPassword(newPassword)) {
			System.out.println("�������벻���ϸ�����Ҫ��");
			password = setPassword();
		}
		// �������Ҫ�󣬽�һ������ȷ������
		else {
			System.out.println("������ȷ�����룺");
			verifyPassword = scan.next();
			if (!newPassword.equals(verifyPassword)) {
				System.out.println("����������������һ�£��������������룺");
				password = setPassword();
			} else {
				password = newPassword;
			}
		}
		return password;
	}

}
