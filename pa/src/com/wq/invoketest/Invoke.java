package com.wq.invoketest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class Invoke {

	@Test
	public void testVoid() {
		try {
			
			Class<?> type = Class.forName("com.wq.invoketest.Target");
			Method method = type.getDeclaredMethod("myPrintVoid", new Class[]{});
			Object obj = type.newInstance();
			method.invoke(obj, new Object[]{});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStatic() {
		try {
			Class<?> type = Class.forName("com.wq.invoketest.Target");
			Method method = type.getDeclaredMethod("myPrintStatic", new Class[]{});
			method.setAccessible(true);
			method.invoke(null, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStructor() {
		try {
			Class<?> type = Class.forName("com.wq.invoketest.Target");
			Constructor<?> con = type.getConstructor(String.class);
			con.newInstance("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
