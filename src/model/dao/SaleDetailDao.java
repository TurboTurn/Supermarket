package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import DriverTest.Driver;

import util.DBManager;

import model.vo.ProductVo;
import model.vo.SaleDetailVo;

public class SaleDetailDao {

	// 向数据库中添加销售信息
	public static void addDetail(int amount, ProductVo product) {
		try {
			DBManager db = new DBManager();
			String serialnum = lsh();
			String cashier = Driver.user.getChName();
			String saletime = getDate("time");
			// --插入商品信息
			String sql = "insert INTO tsaledetail(serialnum,productcode,productname,price,amount,cashier,saletime)"
					+ "VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pst = db.getPrepareStatement(sql);
			pst.setString(1, serialnum);
			pst.setString(2, product.getProductCode());
			pst.setString(3, product.getProductName());
			pst.setDouble(4, product.getPrice());
			pst.setInt(5, amount);
			pst.setString(6, cashier);
			pst.setString(7, saletime);
			int iCount = pst.executeUpdate();
			if (iCount > 0) {
				System.out.println("成功增加" + iCount + "笔销售记录!");
			} else {
				System.out.println("失败!");
			}
			pst.close();
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 按商品名称查询
	public static ArrayList<ProductVo> searchName() {
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入查询的商品名称：");
		String productName = scan.next();
		String productcode = "", productname = "", supplier = "";
		double price = 0;
		String sql = "select * from tproduct where productname like " + "'%"
				+ productName + "%'";
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				productcode = rs.getString(1);
				productname = rs.getString(2);
				price = rs.getDouble(3);
				supplier = rs.getString(4);
				ProductVo product = new ProductVo(productcode, productname,
						price, supplier);
				list.add(product);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 读取数据库销售信息到ArrayList<SaleDetailVo> list
	public static ArrayList<SaleDetailVo> readSaleDetail() {
		ArrayList<SaleDetailVo> list = new ArrayList<SaleDetailVo>();
		String serialnum = "";
		String productCode = "";
		String productName = "";
		double price = 0;
		int amount = 0;
		String cashier = "";
		String saletime = "";
		try {
			DBManager db = new DBManager();
			String sql = "select * from tsaledetail ";
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				serialnum = rs.getString(1);
				productCode = rs.getString(2);
				productName = rs.getString(3);
				price = rs.getDouble(4);
				amount = rs.getInt(5);
				cashier = rs.getString(6);
				saletime = rs.getString(7);
				SaleDetailVo saledetail = new SaleDetailVo(serialnum,
						productCode, productName, price, amount, cashier,
						saletime);
				list.add(saledetail);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 生成流水号
	public static String lsh() {
		String serialnumber = null;
		try {
			DBManager db = new DBManager();
			String sql1 = "select max(right(serialnum,4)) from tsaledetail "
					+ "where left(serialnum,8)=" + getDate("cal");
			ResultSet rs = db.executeQuery(sql1);
			String rowcount = null;
			int rowCount = 0;
			if (rs.next()) {
				rowcount = rs.getString(1);
				if (rowcount != null) {
					rowCount = Integer.parseInt(rowcount);
					rowCount++;
					rowcount = "" + rowCount;
					while (rowcount.length() < 4) {
						rowcount = "0" + rowcount;
					}
				} else {
					rowcount = "0001";
					rowCount = 1;
				}
			}
			serialnumber = getDate("cal") + rowcount;
			System.out.println("流水号为" + serialnumber);
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serialnumber;
	}

	// 获取当前日期时间
	public static String getDate(String a) {
		Date date = new Date();
		if (a == "cal") {
			DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
			String time2 = format2.format(date);
			return time2;
		} else if (a == "time") {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			return time;
		} else
			return null;
	}

}
