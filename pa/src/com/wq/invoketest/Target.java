package com.wq.invoketest;

public class Target {
	
	public Target(){
		System.out.println("null");
	}
	public Target(String s){
		System.out.println("���췽��"+s);
	}
	public static void myPrintStatic() {
		System.out.println("���侲̬����");
	}
	public void myPrintVoid() {
		System.out.println("����Ǿ�̬����");
	}
}
