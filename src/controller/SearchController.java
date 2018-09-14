package controller;

import view.SearchView;

public class SearchController {
	// 控制查询统计逻辑功能
	public static void searchProcess() {
		// 从用户界面获取日期
		String saletime = SearchView.getSaleTime();
		// 显示该日期的销售统计信息
		SearchView.showDetail(saletime);
	}

}
