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
	// �Ƿ������Ʒ��Ϣ
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

	// ������Ʒ���������Ʒ����ϸ��Ϣ
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

	// ���ı��ļ���������,���������ݴ����product������
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
			System.out.println("�ļ�δ�ҵ�");
		}
		return list;
	}

	// ��Excel�ļ���������,���������ݴ����product������
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
			System.out.println("�Ҳ����ļ���");
		}
		return list;
	}

	// ��ArrayList�е���Ʒ��Ϣ��ӵ����ݿ��У����سɹ���ӵ���Ʒ����
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
						// ��¼�ɹ��������Ʒ����
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

	// ��Product������ӵ����ݿ�
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
