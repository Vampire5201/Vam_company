package com.wq.mycrawlermodify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.tags.ImageTag;

//4����д������MyCrawler������������ȡЧ��//���ˣ����Կ���f:\spider�ļ��������Ѿ������˺ܶ�html�ļ������ǹ��ڰٶȵģ��ԡ�www.baidu.com��Ϊ��ͷ
public class MyCrawler {
	
	private Set<String> imageUrl = new HashSet<String>();
	
	/**
	 * ʹ�����ӳ�ʼ�� URL ����
	 *
	 * @return
	 * @param seeds
	 *            ����URL
	 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	/**
	 * ץȡ����
	 *
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds) { // �������������ȡ��http://www.lietu.com��ͷ������
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
		// ��ʼ�� URL ����
		initCrawlerWithSeeds(seeds);
		// ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		while (!LinkQueue.unVisitedUrlsEmpty()
				&& LinkQueue.getVisitedUrlNum() <= 1000) {
			// ��ͷURL������
			String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			
			if (visitUrl == null)
				continue;
			
			//��ȡ��ҳ��֪�������Ϣ
			//visitUrl�Ƿ�ƥ��<h2>([\\s\\S]*)question_link([\\s\\S]*)href=\"([\\s\\S]*)\"[\\s\\S]*</h2>
			ArrayList<Zhihu> visitUrlContent = HtmlParserTool.getImgLinks(visitUrl);
			
			DownLoadFile downLoader = new DownLoadFile();
			// ������ҳ
			downLoader.downloadFile(visitUrl);
			System.out.println(visitUrl);
			// �� url ���뵽�ѷ��ʵ� URL ��
			LinkQueue.addVisitedUrl(visitUrl);
			
			//�����û�ͷ��
			String peopleImageUrl = TestGetTagA.getPeopleImagUrl(visitUrl, "UTF-8", ImageTag.class);
			if(!imageUrl.contains(peopleImageUrl) && !"".endsWith(peopleImageUrl)){
				TestGetTagA.getImgFromUrl(peopleImageUrl, "");
				imageUrl.add(peopleImageUrl);
			}
			// ��ȡ��������ҳ�е� URL
			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// �µ�δ���ʵ� URL ���
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
			}
		}
	}

	// main �������
	public static void main(String[] args) {
//		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "false");
//		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");
		
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[] { "http://www.zhihu.com/explore" });
	}

}