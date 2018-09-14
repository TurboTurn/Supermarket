package util;

import java.util.regex.Pattern;

//ʵ�ֳ����������ݸ�ʽУ��
public class Validate {
	// У�����븴���ԣ����볤�Ȳ�����6���ַ���������һ��Сд��ĸ��������һ����д��ĸ������һ�����֣�
	public static boolean isPassword(String str) {
		int num = 0;
		if (str.length() >= 6) {
			num = Pattern.compile("\\d").matcher(str).find() ? num + 1 : num;
			num = Pattern.compile("[a-z]").matcher(str).find() ? num + 1 : num;
			num = Pattern.compile("[A-Z]").matcher(str).find() ? num + 1 : num;
		}
		return num >= 3;
	}

	// ��֤��Ʒ�������ǲ���6λ�����ַ�
	public static boolean isProductCode(String productCode) {
		boolean ret = false;
		if (Pattern.compile("\\d{6}").matcher(productCode).matches())
			ret = true;
		return ret;
	}

	// ��֤��������ڸ�ʽ���·�С��12����С��31��
	public static boolean isDate(String saletime) {
		boolean ret = false;
		if (Pattern.compile("\\d{4}-(0[1-9]|1[1-2])-(0[1-9]|1[1-9]|2[0-9]|3[0-1])")
				.matcher(saletime).matches())
			ret = true;
		return ret;
	}

}
