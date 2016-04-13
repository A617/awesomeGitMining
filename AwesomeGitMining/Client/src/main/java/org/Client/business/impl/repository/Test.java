package org.Client.business.impl.repository;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Common.vo.UserRateVO;

public class Test {

	
	public static void main(String[] args) {
		UserService us = UserServiceImpl.getInstance();
		us.getUser("technoweenie");
		UserRateVO vo = UserServiceImpl.getInstance().getEvaluation("technoweenie");
		System.out.println(vo.getRates());
		System.out.println(vo);
	}
}
