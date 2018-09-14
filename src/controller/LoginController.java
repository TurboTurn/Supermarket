package controller;

import DriverTest.Driver;

import view.LoginView;
import model.dao.UserDao;
import model.vo.UserVo;

public class LoginController {
	static int loginCount = 1;
	// ʵ�ֵ�¼���̵��߼����� ���Ƶ�¼����
	public static boolean loginProcess(UserVo user) {
		
		boolean ret = false;
		if (UserDao.login(user)) {
			ret = true;
		} else {
			loginCount++;
			if (loginCount > 3) {
				System.out.println("���ֻ�ܳ���3�Σ������˳���");
				return false;
			}
			System.out.println("�û��������벻��ȷ");
			System.out.println("��" + loginCount + "�γ��Ե�¼");
			Driver.user = LoginView.show();
			loginProcess(Driver.user);
		}
		return ret;
	}
}
