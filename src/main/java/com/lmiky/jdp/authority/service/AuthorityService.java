package com.lmiky.jdp.authority.service;

import java.util.List;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.Role;

/**
 * 权限业务
 * @author lmiky
 * @date 2013-5-20
 */
public interface AuthorityService {

	/**
	 * 获取已授权操作员
	 * @author lmiky
	 * @date 2013-5-20
	 * @param functionPath	功能路径
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listAuthorizedOperator(String functionPath) throws ServiceException;

	/**
	 * 获取未授权操作员
	 * @author lmiky
	 * @date 2013-5-20
	 * @param functionPath	功能路径
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listUnauthorizedOperator(String functionPath) throws ServiceException;
	
	/**
	 * 授权
	 * @author lmiky
	 * @date 2013-12-29
	 * @param functionPath 功能路径
	 * @param authorities
	 * @throws ServiceException
	 */
	public void authorize(String functionPath, List<Authority> authorities) throws ServiceException;
	
	/**
	 * 检查是否拥有权限
	 * @author lmiky
	 * @date 2013-5-24
	 * @param functionPath	功能路径
	 * @param userId 用户ID
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkAuthority(String functionPath, Long userId) throws ServiceException;

}
