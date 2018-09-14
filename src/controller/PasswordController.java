package controller;

import java.util.Scanner;

import model.dao.UserDao;

import util.Encription;
import util.Validate;

import DriverTest.Driver;

public class PasswordController {
	// 修改密码进程
	public static void changePassword() {
		Scanner scan = new Scanner(System.in);
		String newPassword = null;
		System.out.println("请输入当前用户的原密码：");
		String password = scan.next();
		// 验证密码是否输入正确
		while (!Encription.md5(password).equals(Driver.user.getPassword())) {
			System.out.println("原密码输入不正确，请重新输入:");
			password = scan.next();
		}
		// 输入新密码
		newPassword = setPassword();
		// 执行数据库中跟新密码MD5操作
		if (UserDao.updatePassword(Encription.md5(newPassword))) {
			Driver.user.setPassword(Encription.md5(newPassword));
			System.out.println("您已成功修改密码，请谨记");
			System.out.println("按任意键返回");
			scan.next();
		}
	}

	// 输入新密码
	public static String setPassword() {
		Scanner scan = new Scanner(System.in);
		String newPassword = null, verifyPassword = null, password = null;
		System.out.println("请设置新的密码：");
		newPassword = scan.next();
		// 判断密码复杂度
		// 密码不符合要求，重新输入
		if (!Validate.isPassword(newPassword)) {
			System.out.println("您的密码不符合复杂性要求");
			password = setPassword();
		}
		// 密码符合要求，进一步输入确认密码
		else {
			System.out.println("请输入确认密码：");
			verifyPassword = scan.next();
			if (!newPassword.equals(verifyPassword)) {
				System.out.println("两次输入的密码必须一致，请重新输入密码：");
				password = setPassword();
			} else {
				password = newPassword;
			}
		}
		return password;
	}

}
