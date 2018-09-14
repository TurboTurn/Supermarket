package view;

import java.util.Scanner;

import util.Encription;

import model.vo.UserVo;

//实现登录的界面
public class LoginView {
	// 实现界面的显示，接受输入的数据，进行某种形式的封装
	public static UserVo show() {
		System.out.println("欢迎使用****超市收银系统，请登录");
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String userName = scan.next();
		System.out.println("请输入密码：");
		String password = scan.next();
		String miwen = Encription.md5(password);
		UserVo user = new UserVo(userName, miwen);
		return user;
	}

}
