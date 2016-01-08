package com.wq.mycrawlermodify;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//1.�����ѷ��ʶ��У������ʶ��к���ȡ��URL�Ĺ�ϣ�����������У�����У��ж϶����Ƿ�յȲ���
public class LinkQueue {
	// �ѷ��ʵ� url ����
	private static Set<String> visitedUrl = new HashSet<String>();
	// �����ʵ� url ����
	private static Queue<String> unVisitedUrl = new PriorityQueue<String>();

	// ���URL����
	public static Queue<String> getUnVisitedUrl() {
		return unVisitedUrl;
	}

	// ��ӵ����ʹ���URL������
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}

	// �Ƴ����ʹ���URL
	public static void removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}

	// δ���ʵ�URL������
	public static Object unVisitedUrlDeQueue() {
		return unVisitedUrl.poll();
	}

	// ��֤ÿ�� url ֻ������һ��
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url))
			unVisitedUrl.add(url);
	}

	// ����Ѿ����ʵ�URL��Ŀ
	public static int getVisitedUrlNum() {
		return visitedUrl.size();
	}

	// �ж�δ���ʵ�URL�������Ƿ�Ϊ��
	public static boolean unVisitedUrlsEmpty() {
		return unVisitedUrl.isEmpty();
	}

}
