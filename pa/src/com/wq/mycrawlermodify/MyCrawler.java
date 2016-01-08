package com.wq.mycrawlermodify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.tags.ImageTag;

//4、编写测试类MyCrawler，用来测试爬取效果//至此，可以看到f:\spider文件夹下面已经出现了很多html文件，都是关于百度的，以“www.baidu.com”为开头
public class MyCrawler {
	
	private Set<String> imageUrl = new HashSet<String>();
	
	/**
	 * 使用种子初始化 URL 队列
	 *
	 * @return
	 * @param seeds
	 *            种子URL
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	/**
	 * 抓取过程
	 *
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds) { // 定义过滤器，提取以http://www.lietu.com开头的链接
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
//				if (url.startsWith("http://www.zhihu.com/"))
//				if (url.startsWith("http://www.zhihu.com/question") 
//						|| url.startsWith("http://www.zhihu.com/people") 
//						|| url.startsWith("http://www.zhihu.com/topic")
//						||url.startsWith("http://www.zhihu.com/explore"))
				if (url.startsWith("http://www.zhihu.com/people"))
					return true;
				else
					return false;
			}
		};
		// 初始化 URL 队列
		initCrawlerWithSeeds(seeds);
		// 循环条件：待抓取的链接不空且抓取的网页不多于1000
		while (!LinkQueue.unVisitedUrlsEmpty()
				&& LinkQueue.getVisitedUrlNum() <= 1000) {
			// 队头URL出队列
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			
			if (visitUrl == null)
				continue;
			
			//获取网页中知乎相关信息
			//visitUrl是否匹配<h2>([\\s\\S]*)question_link([\\s\\S]*)href=\"([\\s\\S]*)\"[\\s\\S]*</h2>
			ArrayList<Zhihu> visitUrlContent = HtmlParserTool.getImgLinks(visitUrl);
			
			DownLoadFile downLoader = new DownLoadFile();
			// 下载网页
			downLoader.downloadFile(visitUrl);
			System.out.println(visitUrl);
			// 该 url 放入到已访问的 URL 中
			LinkQueue.addVisitedUrl(visitUrl);
			
			//下载用户头像
			String peopleImageUrl = TestGetTagA.getPeopleImagUrl(visitUrl, "UTF-8", ImageTag.class);
			if(!imageUrl.contains(peopleImageUrl) && !"".endsWith(peopleImageUrl)){
				TestGetTagA.getImgFromUrl(peopleImageUrl, "");
				imageUrl.add(peopleImageUrl);
			}
			// 提取出下载网页中的 URL
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// 新的未访问的 URL 入队
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
			}
		}
	}

	// main 方法入口
	public static void main(String[] args) {
//		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "false");
//		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
		
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[] { "http://www.zhihu.com/explore" });
	}

}