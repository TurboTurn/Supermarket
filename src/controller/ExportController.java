package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Number;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import model.dao.SaleDetailDao;
import model.vo.SaleDetailVo;

public class ExportController {
	// �������ݵ����Ĺ���
	public static void exportProcess() {
		int choice = show();
		while (true) {
			switch (choice) {
			case 1:
				ArrayList<SaleDetailVo> list1 = SaleDetailDao.readSaleDetail();
				String excelName = "saleDetail" + SaleDetailDao.getDate("cal")
						+ ".xls";
				int count = writeToExcel(list1, "export/" + excelName);
				if (count > 0)
					System.out.println("�ɹ�����" + count + "���������ݵ�excel�ļ���");
				else
					System.out.println("δ�����κ����ݵ�excel");
				break;
			case 2:
				ArrayList<SaleDetailVo> list2 = SaleDetailDao.readSaleDetail();
				String txtName = "saleDetail" + SaleDetailDao.getDate("cal")
						+ ".txt";
				int count2 = writeToTxt(list2, "export/" + txtName);
				if (count2 > 0)
					System.out.println("�ɹ�����" + count2 + "���������ݵ�Txt�ļ���");
				else
					System.out.println("δ�����κ����ݵ�Txt");
				break;
			case 3:
				return;
			default:
				System.out.println("������Ч!��ѡ��1-3����");
				break;
			}
			choice = show();
		}
	}

	public static int show() {
		Scanner scan = new Scanner(System.in);
		System.out.println("===****����������Ϣ����====");
		System.out.println("1��������excel�ļ�");
		System.out.println("2���������ı��ļ�");
		System.out.println("3���������˵�");
		System.out.println("��ѡ��1-3����");
		int choice = scan.nextInt();
		return choice;
	}

	// ��list������д��excel�����سɹ�д�������
	public static int writeToExcel(ArrayList<SaleDetailVo> list, String fileName) {
		int count = 0;
		File file = new File(fileName);
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("��Ʒ������Ϣ", 0);
			String title[] = { "��ˮ��", "��Ʒ������", "��Ʒ����", "�۸�", "����", "����Ա",
					"����ʱ��" };
			// ���ñ����ʽ��������ɫ
			WritableFont font = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,
					Colour.RED);
			WritableCellFormat format = new WritableCellFormat(font);
			// �����������ָ�ʽ
			WritableFont font2 = new WritableFont(WritableFont.ARIAL, 12);
			WritableCellFormat format2 = new WritableCellFormat(font2);
			// ���ò�ͬ�еĿ��
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(6, 25);
			// ��sheet���Ӱ������е�������Ĭ�ϵ��еĿ��;
			sheet.getSettings().setDefaultColumnWidth(10);

			// ���ñ�����ɫ;
			format.setBackground(Colour.BLUE_GREY);

			// ���ñ߿�;
			format.setBorder(Border.ALL, BorderLineStyle.DASHED);

			// �������־��ж��뷽ʽ;
			format.setAlignment(Alignment.CENTRE);

			for (int col = 0; col < title.length; col++) {
				Label label = new Label(col, 0, title[col], format);
				sheet.addCell(label);
			}
			for (int row = 1; row < list.size() + 1; row++) {
				Label label1 = new Label(0, row, list.get(row - 1)
						.getSerialnum(), format2);
				sheet.addCell(label1);
				Label label2 = new Label(1, row, list.get(row - 1)
						.getProductCode(), format2);
				sheet.addCell(label2);
				Label label3 = new Label(2, row, list.get(row - 1)
						.getProductName(), format2);
				sheet.addCell(label3);
				Number label4 = new Number(3, row,
						list.get(row - 1).getPrice(), format2);
				sheet.addCell(label4);
				Number label5 = new Number(4, row, list.get(row - 1)
						.getAmount(), format2);
				sheet.addCell(label5);
				Label label6 = new Label(5, row,
						list.get(row - 1).getCashier(), format2);
				sheet.addCell(label6);
				Label label7 = new Label(6, row, list.get(row - 1)
						.getSaletime(), format2);
				sheet.addCell(label7);
				count++;
			}
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return count;
	}

	// ��list������д���ı��ļ������سɹ�д�������
	public static int writeToTxt(ArrayList<SaleDetailVo> list, String fileName) {
		int count = 0;
		String aline = "";
		File file = new File(fileName);
		try {
			FileWriter wr = new FileWriter(file);
			PrintWriter pr = new PrintWriter(wr);
			aline = "��ˮ�ţ���Ʒ�����룬��Ʒ���ƣ��۸�����������Ա������ʱ��";
			pr.println(aline);
			for (int i = 0; i < list.size(); i++) {
				aline = list.get(i).toString();
				pr.println(aline);
				count++;
			}
			pr.close();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

}
