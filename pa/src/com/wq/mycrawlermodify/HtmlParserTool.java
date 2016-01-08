package com.wq.mycrawlermodify;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


//3������HtmlParserTool�࣬���������ҳ�еĳ����ӣ�����a��ǩ��frame�е�src�ȵȣ�����Ϊ�˵õ��ӽڵ��URL����Ҫ����htmlparser.jar
public class HtmlParserTool {
	// ��ȡ��������ҳ�е� URL
	public static Set<String> extracLinks(String url, LinkFilter filter) {

		Set<String> links = new HashSet<String>();
		try {
			
			//��ȡ���ӵ���ҳ���ݶ���
			Parser parser = new Parser(url);
			// parser.setEncoding("utf-8");
			// ���� <frame >��ǩ�� filter��������ȡ frame ��ǩ��� src ��������ʾ������
			@SuppressWarnings("serial")
			NodeFilter frameFilter = new NodeFilter() {
				public boolean accept(Node node) {
					if (node.getText().startsWith("frame src=")) {
						return true;
					} else {
						return false;
					}
//					LinkTag tag = (LinkTag) nodes.elementAt(i);
//					System.out.println(tag.getAttribute("href"));
				}
			};
			// OrFilter �����ù��� <a> ��ǩ���� <frame> ��ǩ
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(
					LinkTag.class), frameFilter);
			// �õ����о������˵ı�ǩ
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof LinkTag)// <a> ��ǩ
				{
					LinkTag link = (LinkTag) tag;
					String linkUrl = link.getLink();// url
//					System.out.println(linkUrl);
//					Pattern pattern = Pattern.compile("<h2>[\\s\\S]*question_link[\\s\\S]*href=\"[\\s\\S]*\"[\\s\\S]*</h2>");
//					Matcher matcher = pattern.matcher(linkUrl);
//					// �Ƿ���question������
//					Boolean isFind = matcher.find();
					if (filter.accept(linkUrl))
						links.add(linkUrl);
				} else// <frame> ��ǩ
				{
					// ��ȡ frame �� src ���Ե������� <frame src="test.html"/>
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if (end == -1)
						end = frame.indexOf(">");
					String frameUrl = frame.substring(5, end - 1);
					if (filter.accept(frameUrl))
						links.add(frameUrl);
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return links;
	}
	
	// ��ȡ���еı༭�Ƽ���֪������
	static ArrayList<Zhihu> getImgLinks(String content) {
		// Ԥ����һ��ArrayList���洢���
		ArrayList<Zhihu> results = new ArrayList<Zhihu>();
		// ����ƥ��url��Ҳ�������������
		// .+?����ƥ�任�з�
//		Pattern pattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Pattern pattern = Pattern.compile("<h2>[\\s\\S]*question_link[\\s\\S]*href=\"[\\s\\S]*\"[\\s\\S]*</h2>");
		Matcher matcher = pattern.matcher(content);
		// �Ƿ����ƥ��ɹ��Ķ���
		Boolean isFind = matcher.find();
		while (isFind) {
			// ����һ��֪���������洢ץȡ������Ϣ
			Zhihu zhihuTemp = new Zhihu(matcher.group(1));
			// ��ӳɹ�ƥ��Ľ��
			results.add(zhihuTemp);
			// ����������һ��ƥ�����
			isFind = matcher.find();
		}
		return results;
	}
	
	//���ط���ҳ��
	static String sendGet(String url) {
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
	
}