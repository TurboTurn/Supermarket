package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBManager;
import util.Validate;

public class SearchView {
	// ���û�����������Ϣ�����ص�controller����
	public static String getSaleTime() {
		Scanner scan = new Scanner(System.in);
		System.out.println("�������������ڣ�yyyy-mm-dd��:");
		String saletime = scan.next();
		while (!Validate.isDate(saletime)) {
			System.out.println("����������ڸ�ʽ����ȷ������������");
			saletime = scan.next();
		}
		return saletime;
	}

	// �����������ڲ�ѯͳ�Ƶ�ǰ�������е�������Ϣ
	public static void showDetail(String saletime) {
		int productCount = 0;
		int saleCount = 0;
		double saleAmount = 0;
		try {
			DBManager db = new DBManager();
			String array[] = saletime.split("-");
			System.out.println(array[0] + "��" + array[1] + "��" + array[2]
					+ "���������£�");
			System.out.println("��ˮ��\t\t��Ʒ����\t����\t����\t���\tʱ��\t\t\t����Ա");
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
			System.out.println("\n����������" + saleCount + "\t��Ʒ�ܼ���" + productCount
					+ "\t�����ܽ�" + saleAmount);
			System.out.println("����" + array[0] + "��" + array[1] + "��"
					+ array[2] + "��");
			System.out.println("�����������������");
			(new Scanner(System.in)).next();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
