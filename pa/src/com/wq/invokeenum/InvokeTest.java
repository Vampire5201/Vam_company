package com.wq.invokeenum;

import org.junit.Test;

public class InvokeTest {

	// 反射获取枚举
	@Test
	public void test() {
		try {
			
			Class<?> type = Class.forName("com.wq.invokeenum.Target");
			Object[] objs = type.getEnumConstants();
			Object o = type.getEnumConstants()[0];
			System.out.println(o.toString());
            for (Object obj : objs) {
            	System.out.println(obj.toString());
            } 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 反射获取类中枚举（等价于获取内部类）
	@Test
	public void test2() {
		try {
			Class<?> type = Class.forName("com.wq.invokeenum.TargetMore$SdkMode");
			Object o = type.getEnumConstants()[1];
			System.out.println(o.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
