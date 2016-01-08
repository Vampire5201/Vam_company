package com.wq.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.junit.Test;

public class RegexText {

	@Test
	public void test() {
		
		Pattern pattern = Pattern.compile("www\\.zhihu\\.com\\/people\\/[\\s\\S]*");
		Matcher matcher = pattern.matcher("www.zhihu.com/people/lelll");
		Boolean isFind = matcher.find();
		System.out.println(isFind);
	}
	@Test
	public void test3() {
		
		Pattern pattern = Pattern.compile("http:\\/\\/www\\.zhihu\\.com\\/people\\/[\\s\\S]*");
		Matcher matcher = pattern.matcher("http://www.zhihu.com/people/lelll");
		Boolean isFind = matcher.find();
		System.out.println(isFind);
	}
	@Test
	public void test2() {
		
		Pattern pattern = Pattern.compile("^abc.*");
		Matcher matcher = pattern.matcher("abc");
		Boolean isFind = matcher.find();
		System.out.println(isFind);
	}
	@Test
	public void test4() {
		try {
			Parser parser = new Parser();
	        parser.setURL("www.zhihu.com");
		} catch (Exception e) {
		}
	}
	
	@Test
	public void test5() {
		try {
			Parser parser = new Parser();
	        parser.setURL("www.zhihu.com");
		} catch (Exception e) {
		}
	}

}
