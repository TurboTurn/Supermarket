package util;

import java.util.regex.Pattern;

//实现常见各种数据格式校验
public class Validate {
	// 校验密码复杂性（密码长度不少于6个字符，至少有一个小写字母，至少有一个大写字母，至少一个数字）
	public static boolean isPassword(String str) {
		int num = 0;
		if (str.length() >= 6) {
			num = Pattern.compile("\\d").matcher(str).find() ? num + 1 : num;
			num = Pattern.compile("[a-z]").matcher(str).find() ? num + 1 : num;
			num = Pattern.compile("[A-Z]").matcher(str).find() ? num + 1 : num;
		}
		return num >= 3;
	}

	// 验证商品条形码是不是6位数字字符
	public static boolean isProductCode(String productCode) {
		boolean ret = false;
		if (Pattern.compile("\\d{6}").matcher(productCode).matches())
			ret = true;
		return ret;
	}

	// 验证输入的日期格式（月份小于12，日小于31）
	public static boolean isDate(String saletime) {
		boolean ret = false;
		if (Pattern.compile("\\d{4}-(0[1-9]|1[1-2])-(0[1-9]|1[1-9]|2[0-9]|3[0-1])")
				.matcher(saletime).matches())
			ret = true;
		return ret;
	}

}
