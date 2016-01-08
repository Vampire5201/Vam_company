package com.wq.invokeenum;

import org.junit.Test;

public class InvokeTest {

	// �����ȡö��
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
	
	// �����ȡ����ö�٣��ȼ��ڻ�ȡ�ڲ��ࣩ
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
