package controller;

import view.SearchView;

public class SearchController {
	// ���Ʋ�ѯͳ���߼�����
	public static void searchProcess() {
		// ���û������ȡ����
		String saletime = SearchView.getSaleTime();
		// ��ʾ�����ڵ�����ͳ����Ϣ
		SearchView.showDetail(saletime);
	}

}
