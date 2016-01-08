package com.wq.study.second2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
	static String SendGet(String url) {
		// ����һ���ַ��������洢��ҳ����
		String result = "";
		// ����һ�������ַ�������
		BufferedReader in = null;
		try {
			// ��stringת��url����
			URL realUrl = new URL(url);
			// ��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection connection = realUrl.openConnection();
			// ��ʼʵ�ʵ�����
			connection.connect();
			// ��ʼ�� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			// ������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally���ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	static ArrayList<Zhihu> GetZhihu(String content) {
		// Ԥ����һ��ArrayList���洢���
		ArrayList<Zhihu> results = new ArrayList<Zhihu>();
		// ����ƥ�����
		Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
		Matcher questionMatcher = questionPattern.matcher(content);
		// ����ƥ��url��Ҳ�������������
		Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
		Matcher urlMatcher = urlPattern.matcher(content);
		// ���������Ҫ����ƥ�䵽
		boolean isFind = questionMatcher.find() && urlMatcher.find();
		while (isFind) {
			// ����һ��֪���������洢ץȡ������Ϣ
			Zhihu zhuhuTemp = new Zhihu();
			zhuhuTemp.question = questionMatcher.group(1);
			zhuhuTemp.zhihuUrl = "http://www.zhihu.com" + urlMatcher.group(1);
			// ��ӳɹ�ƥ��Ľ��
			results.add(zhuhuTemp);
			// ����������һ��ƥ�����
			isFind = questionMatcher.find() && urlMatcher.find();
		}
		return results;
	}
}
