package com.wq.study.third;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// ���弴�����ʵ�����
		String url = "http://www.zhihu.com/explore/recommendations";
		// �������Ӳ���ȡҳ������
		String content = Spider.SendGet(url);
		// ��ȡ�༭�Ƽ�
		ArrayList<Zhihu> myZhihu = Spider.GetRecommendations(content);
		// ��ӡ���
		System.out.println(myZhihu);
	}
}