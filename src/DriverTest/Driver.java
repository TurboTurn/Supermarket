package DriverTest;

import java.io.IOException;
import java.sql.SQLException;

import jxl.read.biff.BiffException;

import controller.LoginController;
import controller.MenuController;
import model.vo.UserVo;
import view.LoginView;

public class Driver {
	public static UserVo user;

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, BiffException, IOException {
		// System.out.println(Encription.md5("1234"));
		// System.exit(0);
		user = LoginView.show();
		if (LoginController.loginProcess(user)) {
			MenuController.menuChoose();
		} else {
			System.out.println("µÇÂ¼Ê§°Ü");
		}

	}

}
