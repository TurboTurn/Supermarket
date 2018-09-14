package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import DriverTest.Driver;

import util.DBManager;
import model.vo.UserVo;

//实现用户对象的操作封装
public class UserDao {
	// 登录请求判断的操作封装
	public static boolean login(UserVo user) {
		boolean ret = false;
		String password = "";
		String sql = "select password from tuser where username='"
				+ user.getUserName() + "'";
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				password = rs.getString("password");
			}
			if (password != null && password.equals(user.getPassword())) {
				ret = true;
				setUser(user);
			}
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 修改数据库中用户的密码
	public static boolean updatePassword(String password) {
		boolean ret = false;
		String sql = "update tuser set password=" + "'" + password + "'"
				+ " where username=" + "'" + Driver.user.getUserName() + "'";
		try {
			DBManager db = new DBManager();
			int count = db.executeUpdate(sql);
			if (count > 0) {
				ret = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 补全用户对象属性（中文名，角色）
	public static void setUser(UserVo user) {
		String sql = "select chname,role from tuser where username=" + "'"
				+ user.getUserName() + "'";
		String chname = null;
		String role = null;
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				chname = rs.getString(1);
				role = rs.getString(2);
			}
			user.setChName(chname);
			user.setRole(role);
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
