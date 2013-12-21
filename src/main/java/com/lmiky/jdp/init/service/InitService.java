package com.lmiky.jdp.init.service;

import com.lmiky.jdp.service.BaseService;

/**
 * 初始化业务
 * @author lmiky
 * @date 2013-10-13
 */
public interface InitService extends BaseService {

	/**
	 * 初始化
	 * @author lmiky
	 * @date 2013-10-13
	 * @param adminName	管理员姓名
	 * @param adminLoginName	管理员登陆账号
	 * @param adminPassword	管理员登陆密码
	 * @throws Exception
	 */
	public void init(String adminName, String adminLoginName, String adminPassword) throws Exception;
	
	/**
	 * 初始化功能模块
	 * @author lmiky
	 * @date 2013-12-21
	 * @throws Exception
	 */
	public void updateModule() throws Exception;
}
