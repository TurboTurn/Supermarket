package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import util.DBManager;
import model.vo.ProductVo;

public class ProductDao {
	// 是否存在商品信息
	public static boolean existProduct(String productCode) {
		boolean ret = false;
		String sql = "select productcode from tproduct where productcode="
				+ "'" + productCode + "'";
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				ret = true;
			}
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	// 根据商品代码查找商品的详细信息
	public static ProductVo queryProduct(String productCode) {
		ProductVo product = new ProductVo();
		String productName, supplier;
		double price;
		String sql = "select * from tproduct where productcode=" + "'"
				+ productCode + "'";
		try {
			DBManager db = new DBManager();
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				productName = rs.getString(2);
				price = rs.getDouble(3);
				supplier = rs.getString(4);
				product.setProductCode(productCode);
				product.setProductName(productName);
				product.setPrice(price);
				product.setSupplier(supplier);
			}
			db.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	// 从文本文件批量导入,将所有数据存放于product集合中
	public static ArrayList<ProductVo> readProductFromTxt(String fileName) {
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		try {
			File file = new File(fileName);
			FileReader rd = new FileReader(file);
			BufferedReader buff = new BufferedReader(rd);
			String aLine = "";
			while ((aLine = buff.readLine()) != null) {
				String[] array = aLine.split(" ");
				double price = Double.parseDouble(array[2]);
				ProductVo product = new ProductVo(array[0], array[1], price,
						array[3]);
				list.add(product);
			}
			buff.close();
			rd.close();
		} catch (Exception e) {
			System.out.println("文件未找到");
		}
		return list;
	}

	// 从Excel文件批量导入,将所有数据存放于product集合中
	public static ArrayList<ProductVo> readProductFromExcel(String fileName) {
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		try {
			File file = new File(fileName);

			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			String array[] = new String[4];
			for (int row = 1; row < sheet.getRows(); row++) {
				for (int col = 0; col < sheet.getColumns(); col++) {
					Cell cell = sheet.getCell(col, row);
					array[col] = cell.getContents();
				}
				double price = Double.parseDouble(array[2]);
				ProductVo product = new ProductVo(array[0], array[1], price,
						array[3]);
				list.add(product);
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println("找不到文件！");
		}
		return list;
	}

	// 将ArrayList中的商品信息添加到数据库中，返回成功添加的商品条数
	public static int addProduct(ArrayList<ProductVo> list) {
		int count = 0;
		try {
			DBManager db = new DBManager();
			int icount = 0;
			String sql = "insert into tproduct values (?,?,?,?)";
			PreparedStatement pst = db.getPrepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				if (!existProduct(list.get(i).getProductCode())) {
					pst.setString(1, list.get(i).getProductCode());
					pst.setString(2, list.get(i).getProductName());
					pst.setDouble(3, list.get(i).getPrice());
					pst.setString(4, list.get(i).getSupplier());
					icount = pst.executeUpdate();
					if (icount > 0) {
						// 记录成功导入的商品条数
						count += icount;
					}
				}
			}
			pst.close();
			db.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 将Product对象添加到数据库
	public static boolean addProduct(ProductVo product) {
		boolean ret = false;
		String sql = "insert into tproduct values (" + "'"
				+ product.getProductCode() + "','" + product.getProductName()
				+ "','" + product.getPrice() + "','" + product.getSupplier()
				+ "')";
		try {
			DBManager db = new DBManager();
			int i = db.executeUpdate(sql);
			if (i > 0) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
