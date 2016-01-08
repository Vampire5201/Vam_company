package com.wq.urlencode;

import java.net.URLDecoder;

import org.junit.Test;

public class EncodeUrlJunit {

	@Test
	public void test() {
		try {
			String url = "http://qpay.s-shell.com/pages/wap/home.jsp?status=0&data=%7B%22appname%22%3A%22%E8%89%B2%E5%BD%A9%E9%9F%B3%E4%B9%90%22%2C%22cpname%22%3A%22%E5%8C%97%E4%BA%AC%E9%A3%9E%E5%AE%A2%E4%BA%92%E5%8A%A8%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%22%2C%22goodsname%22%3A%22%E5%A5%BD%E5%90%AC%E9%9F%B3%E4%B9%90%22%2C%22fee%22%3A%221%22%2C%22telnumber%22%3A%22400-601-0255%22%2C%22price%22%3A%221%22%2C%22amount%22%3A%221%22%2C%22cpid%22%3A%22887%22%2C%22strdate%22%3A%222015-11-09%22%2C%22mobile%22%3A%2213045876531%22%2C%22paymenttype%22%3A%22%22%2C%22clienttype%22%3A%222%22%2C%22operatortype%22%3A%221%22%2C%22goodscode%22%3A%22024763000%22%2C%22chcode%22%3A%22100003%22%2C%22gameid%22%3A%223002%22%7D";
			String code = "utf-8";
			String decodeUrl = URLDecoder.decode(url, code);
//			String encodeUrl = URLEncoder.encode(url, code);
			
			System.out.println("decodeUrl: " + decodeUrl);
//			System.out.println("encodeUrl: " + encodeUrl);
		
			
		} catch (Exception e) {
		}
	}

}
