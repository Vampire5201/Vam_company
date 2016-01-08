package com.wq.invoketest;

public class Target {
	
	public Target(){
		System.out.println("null");
	}
	public Target(String s){
		System.out.println("构造方法"+s);
	}
	public static void myPrintStatic() {
		System.out.println("反射静态方法");
	}
	public void myPrintVoid() {
		System.out.println("反射非静态方法");
	}
}
