package com.wq.mycrawler;

public interface LinkFilter {
	public boolean accept(String url);
}
