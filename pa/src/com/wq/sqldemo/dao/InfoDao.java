package com.wq.sqldemo.dao;

import com.wq.sqldemo.model.Zhihuer;

public interface InfoDao {
	 public void insert(Zhihuer u);
	 
	 public boolean check(String username);
	 
	 public Zhihuer login(String username,String password);
}
