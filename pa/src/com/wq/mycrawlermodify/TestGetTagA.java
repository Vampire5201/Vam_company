package com.wq.mycrawlermodify;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class TestGetTagA {

	private static Set<String> questionSet = new HashSet<String>();
	private static Set<String> peopleSet = new HashSet<String>();
	public static Set<String> imageUrl = new HashSet<String>();
	
	// http://www.zhihu.com/
	public static void getHERF(String html) {
		try {
			Parser parser = Parser.createParser(html, "UTF-8");
			System.out.println(parser.toString());

			AndFilter filter = new AndFilter(new TagNameFilter("a"),
					new HasAttributeFilter("id", "question_link"));
			NodeList nodelist = parser.parse(filter);

			for (int i = 0; i < nodelist.size(); i++) {
				System.out.println("\n");
				LinkTag link = (LinkTag) nodelist.elementAt(i);
				// ���ӵ�ַ
				System.out.println(link.getAttribute("href"));
				// ��������
				System.out.println(link.getStringText());
			}

		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * ����ҳ���еı�ǩ��Ϣ
	 * 
	 * @param url
	 *            Ҫ������urlҳ��
	 * @param encoding
	 *            ʹ�õ��ַ�����
	 * @param tagclass
	 *            Ҫ��ȡ��ҳ���ǩ,��Ҫ��ȡҳ���еĳ�����
	 *            ֵΪLinkTag.class,Ҫ��ȡҳ����ͼƬ����,ֵΪImageTag.class
	 *            Ҫ����ı�ǩ��Ϊorg.htmlparser.tags�µ�
	 */
	public static void nodeFilterTagClass(String url, String encoding,
			Class<?> tagclass) {
		try {
			Parser parser = new Parser();
			parser.setURL(url);
			if (null == encoding) {
				parser.setEncoding(parser.getEncoding());
			} else {
				parser.setEncoding(encoding);
			}
			// ����ҳ���е����ӱ�ǩ
			NodeFilter filter = new NodeClassFilter(tagclass);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < list.size(); i++) {
				Node node = (Node) list.elementAt(i);
				System.out.println("link is :" + node.toHtml());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��������ӵ�ַ
	 * 
	 * @param url
	 * @param encoding
	 * @param tagclass
	 * @return
	 */
	public static Set<String> getQuestionUrl(String url, String encoding,
			Class<?> tagclass) {
		try {
			Parser parser = new Parser();
			parser.setURL(url);
			if (null == encoding) {
				parser.setEncoding(parser.getEncoding());
			} else {
				parser.setEncoding(encoding);
			}

			// ����ҳ���е����ӱ�ǩ
			NodeFilter filter = new NodeClassFilter(tagclass);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			//�ܻ�ȡ�����ص����⣬������Ҫ��¼��δ��¼���ʸ�����
//			for (int i = 0; i < 5; i++) {
			int j = 0;
			for (int i = 0; i < list.size(); i++) {
				Node node = (Node) list.elementAt(i);
				LinkTag tag = (LinkTag) list.elementAt(i);
				String classStr = tag.getAttribute("class");
				if ("question_link".equals(classStr)) {
					
					// ��ȡ����
					Pattern pattern = Pattern.compile(">[\\s\\S]*<");
					Matcher matcher = pattern.matcher(node.toHtml());
					// �Ƿ����ƥ��ɹ��Ķ���
					String titleStr = "";
					Boolean isFind = matcher.find();
					if (isFind) {
						titleStr = matcher.group();
					}
					String question_url = tag.getAttribute("href");
					// ��URL�洢������
					if (!questionSet.contains(question_url)) {
						System.out.println("����:   " + titleStr);
						questionSet.add(url + question_url);
						System.out.println("Question URL:    " + url + question_url);
					}
					j++;
					/*if(j>6){
						return questionSet;
					}*/
					/*
					 * System.out.println("Link is :   " + node.toHtml());
					 * System.out.println("Node:   " + node.getText());
					 * System.out.println();
					 */
				}
			}
			return questionSet;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡ�û������ӵ�ַ
	 * 
	 * @param question_url
	 * @param encoding
	 * @param tagclass
	 * @return
	 */
	public static Set<String> getPeopleUrl(String question_url,String encoding, Class<?> tagclass) {
		try {
			Parser parser = new Parser();
			parser.setURL(question_url);
			if (null == encoding) {
				parser.setEncoding(parser.getEncoding());
			} else {
				parser.setEncoding(encoding);
			}
			// ����ҳ���е����ӱ�ǩ
			NodeFilter filter = new NodeClassFilter(tagclass);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < list.size(); i++) {
				Node node = (Node) list.elementAt(i);
				LinkTag tag = (LinkTag) list.elementAt(i);
				String all_url = tag.getAttribute("href");

				// ƥ��/people/�����ַ�
				Pattern pattern = Pattern.compile("\\/people\\/.*");
				Matcher matcher = pattern.matcher(node.toHtml());
				Boolean isFind = matcher.find();

				// ƥ��http://www.zhihu.com/people/�����ַ�
				Pattern pattern_all = Pattern
						.compile("http:\\/\\/www\\.zhihu\\.com\\/people\\/.*");
				Matcher matcher_all = pattern_all.matcher(node.toHtml());
				Boolean isFind_all = matcher_all.find();

				// ƥ��http://www.zhihu.com/people/�����ַ�
				Pattern pattern2 = Pattern
						.compile("www\\.zhihu\\.com\\/people\\/.*");
				Matcher matcher2 = pattern2.matcher(node.toHtml());
				Boolean isFind2 = matcher2.find();
				if (isFind_all || isFind2) {
					String people_url = all_url;
					if (!peopleSet.contains(people_url)) {
						 System.out.println("People_URL:   " + people_url);
						peopleSet.add(people_url);
					}
				} else if (isFind) {
					String people_url = "http://www.zhihu.com" + all_url;
					if (!peopleSet.contains(people_url)) {
						 System.out.println("People_URL:   " + people_url);
						peopleSet.add(people_url);
					}
				}
			}
			return peopleSet;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ȡ�û�ͷ������ӵ�ַ
	 * 
	 * @param url
	 * @param encoding
	 * @param tagclass
	 * @return
	 */
	public static String getPeopleImagUrl(String peopleUrl, String encoding,
			Class<?> tagclass) {
		try {
			Parser parser = new Parser();
			parser.setURL(peopleUrl);
			if (null == encoding) {
				parser.setEncoding(parser.getEncoding());
			} else {
				parser.setEncoding(encoding);
			}
			// ����ҳ���е����ӱ�ǩ
			NodeFilter filter = new NodeClassFilter(tagclass);
			NodeList list = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < list.size(); i++) {
				ImageTag tag = (ImageTag) list.elementAt(i);
				String classStr = tag.getAttribute("class");

				if ("avatar avatar-l".equals(classStr)) {
					System.out.println("ͼƬ��ַ:" + tag.getAttribute("src"));
					imageUrl.add(tag.getAttribute("src"));
					return tag.getAttribute("src");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// ��������ͼƬ(������������)
	public static String getImgFromUrl(String urlstr, String savepath) {
		int num = urlstr.indexOf('/', 8);
		int extnum = urlstr.lastIndexOf('.');
		String u = urlstr.substring(0, num);
		String ext = urlstr.substring(extnum + 1, urlstr.length());
		try {
			long curTime = System.currentTimeMillis();
			Random random = new Random(100000000);
			String fileName = String.valueOf(curTime) + "_"
					+ random.nextInt(100000000) + "." + ext;
			// ͼƬ��·��
			// String realPath = AppConstants.ROOTPATH + savepath;
			String realPath = "E:\\picture_d\\";

			URL url = new URL(urlstr);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("referer", u); // ͨ�����httpͷ��αװ��������
			BufferedImage image = ImageIO.read(connection.getInputStream());
			FileOutputStream fout = new FileOutputStream(realPath + fileName);
			if ("gif".equals(ext) || "png".equals("png")) {
				ImageIO.write(image, ext, fout);
			}
			ImageIO.write(image, "jpg", fout);
			fout.flush();
			fout.close();
			System.out.println(realPath + fileName);
			return realPath + fileName;
		} catch (Exception e) {
			System.out.print(e.getMessage().toString());
		}
		return "";
	}

	public static void main(String[] args) {
		// nodeFilterTagClass("http://www.zhihu.com/","UTF-8",LinkTag.class);
		// ��ȡҳ���е�<a href='xxx' [����]>��ʽ������
		String codding = "UTF-8";
		Set<String> questionUrl = getQuestionUrl("http://www.zhihu.com",
				"UTF-8", LinkTag.class);
		
		// ��ȡpeopleURL
		for (String string : questionUrl) {
			getPeopleUrl(string, codding, LinkTag.class);
		}
		for (String string : peopleSet) {
			getPeopleImagUrl(string, codding, ImageTag.class);
		}
		for (String string : imageUrl) {
			getImgFromUrl(string, "");
		}
		
	}

}
