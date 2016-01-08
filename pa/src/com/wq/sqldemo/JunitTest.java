package com.wq.sqldemo;

import org.junit.Ignore;
import org.junit.Test;

import com.wq.sqldemo.ctrl.Ctrl;
import com.wq.sqldemo.impl.UserDaoImpl;
import com.wq.sqldemo.model.Zhihuer;

public class JunitTest {

	@Ignore
	public void test() {
		Zhihuer user = new Zhihuer();
		user.setId(1);
		user.setUser_name("abc");
		UserDaoImpl dao = new UserDaoImpl();
		dao.insert(user);
	}
	
	@Test
	public void test_people() {
		Ctrl.getZhihuer("http://www.zhihu.com/people/magasa");
	}

}
