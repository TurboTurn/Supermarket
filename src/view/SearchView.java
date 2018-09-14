package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBManager;
import util.Validate;

public class SearchView {
	// 让用户输入销售信息并返回到controller处理
	public static String getSaleTime() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入销售日期（yyyy-mm-dd）:");
		String saletime = scan.next();
		while (!Validate.isDate(saletime)) {
			System.out.println("你输入的日期格式不正确，请重新输入");
			saletime = scan.next();
		}
		return saletime;
	}

	// 根据销售日期查询统计当前日期所有的销售信息
	public static void showDetail(String saletime) {
		int productCount = 0;
		int saleCount = 0;
		double saleAmount = 0;
		try {
			DBManager db = new DBManager();
			String array[] = saletime.split("-");
			System.out.println(array[0] + "年" + array[1] + "月" + array[2]
					+ "日销售如下：");
			System.out.println("流水号\t\t商品名称\t单价\t数量\t金额\t时间\t\t\t收银员");
			System.out
					.println("=============================================================================");
			String sql = "select *,price*amount from tsaledetail where "
					+ "saletime like " + "'" + saletime + "%" + "'";
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				saleCount += rs.getInt("amount");
				saleAmount += rs.getDouble("price*amount");
				System.out.println(rs.getString("serialnum") + "\t"
						+ rs.getString("productname") + "\t"
						+ rs.getDouble("price") + "\t" + rs.getInt("amount")
						+ "\t" + rs.getDouble("price*amount") + "\t"
						+ rs.getString("saletime") + "\t"
						+ rs.getString("cashier"));
			}
			String sql2 = "select count(distinct productname) from tsaledetail where "
					+ "saletime like  " + "'" + saletime + "%" + "'";
			rs = db.executeQuery(sql2);
			if (rs.next()) {
				productCount = rs.getInt(1);
			}
			System.out.println("\n销售总数：" + saleCount + "\t商品总件：" + productCount
					+ "\t销售总金额：" + saleAmount);
			System.out.println("日期" + array[0] + "年" + array[1] + "月"
					+ array[2] + "日");
			System.out.println("按任意键返回主界面");
			(new Scanner(System.in)).next();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
