package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DBManager {
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	private PreparedStatement pst;

	// 构造方法中实现数据库驱动的加载与连接的建立
	public DBManager() throws ClassNotFoundException, SQLException {
		// 驱动加载
		Class.forName("com.mysql.jdbc.Driver");
		// 指定XML配置文件路径
		String fileName = "config.xml";
		Properties pro = new Properties();
		try {
			// 读取XML文件
			pro.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获取XML文件中信息
		String username = pro.getProperty("Username");
		String password = pro.getProperty("Password");
		String DbName = pro.getProperty("DBname");

		// 连接建立
		String url = "jdbc:mysql://127.0.0.1:3306/" + DbName
				+ "?useUnicode=true&characterEncode=utf-8";
		con = DriverManager.getConnection(url, username, password);
		// 创建语句对象
		stm = con.createStatement();
	}

	// 返回连接
	public Connection getConnection() {
		return con;
	}

	// 返回PreparedStatement对象
	public PreparedStatement getPrepareStatement(String sql)
			throws SQLException {
		pst = con.prepareStatement(sql);
		return pst;
	}

	// 执行select语句,返回查询结果
	public ResultSet executeQuery(String sql) throws SQLException {
		rs = stm.executeQuery(sql);
		return rs;
	}

	// 执行的是非select语句，返回受影响的纪录条数
	public int executeUpdate(String sql) throws SQLException {
		int iCount = 0;
		iCount = stm.executeUpdate(sql);
		return iCount;
	}

	// 释放相关的资源
	public void close() throws SQLException {
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		if (stm != null)
			stm.close();
		if (con != null)
			con.close();
	}

}
