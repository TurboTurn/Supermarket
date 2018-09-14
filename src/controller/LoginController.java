package controller;

import DriverTest.Driver;

import view.LoginView;
import model.dao.UserDao;
import model.vo.UserVo;

public class LoginController {
	static int loginCount = 1;
	// 实现登录进程的逻辑控制 限制登录次数
	public static boolean loginProcess(UserVo user) {
		
		boolean ret = false;
		if (UserDao.login(user)) {
			ret = true;
		} else {
			loginCount++;
			if (loginCount > 3) {
				System.out.println("最多只能尝试3次，即将退出！");
				return false;
			}
			System.out.println("用户名或密码不正确");
			System.out.println("第" + loginCount + "次尝试登录");
			Driver.user = LoginView.show();
			loginProcess(Driver.user);
		}
		return ret;
	}
}
