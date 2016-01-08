package com.wq.sqldemo.ctrl;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.wq.sqldemo.impl.UserDaoImpl;
import com.wq.sqldemo.model.Zhihuer;

public class Ctrl {

	public static void getZhihuer(String people_url) {
		Zhihuer zhihu = new Zhihuer();
		try {
			zhihu.setUser_url(people_url);
			
//			Parser parser = Parser.createParser(people_url, "UTF-8");
			Parser parser = new Parser();
			parser.setURL(people_url);
			
			// AndFilter filter = new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("class", "item"));
			NodeList nodelist = parser.parse(new TagNameFilter("a"));
			// 此处能获取到提问，回答，专栏文章，收藏，公共编辑
			for (int i = 0; i < nodelist.size(); i++) {
				System.out.println(i);
				if(i==199){
					System.out.println(i);
				}
				Node node = nodelist.elementAt(i);
				LinkTag link = (LinkTag) nodelist.elementAt(i);
				if(null != link.getAttribute("class") && !"".equals(link.getAttribute("class")) && link.getAttribute("class").trim().equals("item")){
					String link_url = link.getAttribute("href");
					String attr = node.getChildren().elementAt(0).getText();
					// <a class="item "href="/people/magasa/asks">提问<span class="num">0</span></a>
					String numStr = node.getChildren().elementAt(1).getChildren().elementAt(0).getText();
					int num = Integer.valueOf(numStr);
					
					if("提问".equals(attr.trim()) && link_url.contains("asks")){
						zhihu.setAsk_num(num);
					}
					if("回答".equals(attr.trim()) && link_url.contains("answers")){
						zhihu.setAnswer_num(num);
					}
					if("专栏文章".equals(attr.trim()) && link_url.contains("posts")){
						zhihu.setPosts(num);
					}
					if("收藏".equals(attr.trim()) && link_url.contains("collections")){
						zhihu.setCollections(num);
					}
					if("公共编辑".equals(attr.trim()) && link_url.contains("logs")){
						zhihu.setLogs(num);
					}
					System.out.println(node.getChildren().elementAt(0).getText().trim());
					System.out.println("数目:" + node.getChildren().elementAt(1).getChildren().elementAt(0).getText());
					// 链接地址
					System.out.println(link.getAttribute("href"));
				}
			}
			
			UserDaoImpl dao = new UserDaoImpl();
			zhihu.setId(123);
			zhihu.setUser_name("123");
			dao.insert(zhihu);
			
//			System.out.println("zhihu"+ zhihu);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		getZhihuer("http://www.zhihu.com/people/magasa");
//		System.exit(0);
	}
}
