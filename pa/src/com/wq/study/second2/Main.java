package com.wq.study.second2;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// ���弴�����ʵ�����
		String url = "http://www.zhihu.com/explore/recommendations";
		// �������Ӳ���ȡҳ������
		String content = Spider.SendGet(url);
		// ��ȡ��ҳ������е�֪������
		ArrayList<Zhihu> myZhihu = Spider.GetZhihu(content);
		// ��ӡ���
		System.out.println(myZhihu);
	}
}
