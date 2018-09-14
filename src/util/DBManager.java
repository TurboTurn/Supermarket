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

	// ���췽����ʵ�����ݿ������ļ��������ӵĽ���
	public DBManager() throws ClassNotFoundException, SQLException {
		// ��������
		Class.forName("com.mysql.jdbc.Driver");
		// ָ��XML�����ļ�·��
		String fileName = "config.xml";
		Properties pro = new Properties();
		try {
			// ��ȡXML�ļ�
			pro.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ȡXML�ļ�����Ϣ
		String username = pro.getProperty("Username");
		String password = pro.getProperty("Password");
		String DbName = pro.getProperty("DBname");

		// ���ӽ���
		String url = "jdbc:mysql://127.0.0.1:3306/" + DbName
				+ "?useUnicode=true&characterEncode=utf-8";
		con = DriverManager.getConnection(url, username, password);
		// ����������
		stm = con.createStatement();
	}

	// ��������
	public Connection getConnection() {
		return con;
	}

	// ����PreparedStatement����
	public PreparedStatement getPrepareStatement(String sql)
			throws SQLException {
		pst = con.prepareStatement(sql);
		return pst;
	}

	// ִ��select���,���ز�ѯ���
	public ResultSet executeQuery(String sql) throws SQLException {
		rs = stm.executeQuery(sql);
		return rs;
	}

	// ִ�е��Ƿ�select��䣬������Ӱ��ļ�¼����
	public int executeUpdate(String sql) throws SQLException {
		int iCount = 0;
		iCount = stm.executeUpdate(sql);
		return iCount;
	}

	// �ͷ���ص���Դ
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
