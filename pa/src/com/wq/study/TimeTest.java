package com.wq.study;

import java.util.Date;


public class TimeTest {

	public static void main(String[] args) {
		System.out.println(new Date()+"");
		System.out.println(System.currentTimeMillis()+"");
		System.out.println(String.valueOf(System.currentTimeMillis()));
		System.out.println(String.valueOf(System.currentTimeMillis()).substring(3));
	}

}
