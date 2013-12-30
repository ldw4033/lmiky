package com.lmiky.jdp.authority.service;

import java.util.List;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.authority.pojo.AuthorityConfig;
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
	 * @param authorityCode	权限编码
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listAuthorizedOperator(String authorityCode) throws ServiceException;

	/**
	 * 获取未授权操作员
	 * @author lmiky
	 * @date 2013-5-20
	 * @param authorityCode	权限编码
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listUnauthorizedOperator(String authorityCode) throws ServiceException;
	
	/**
	 * 获取已授权操作员
	 * @author lmiky
	 * @date 2013-12-30
	 * @param moduleType
	 * @param moduleCode
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listAuthorizedOperator(String moduleType, String moduleCode) throws ServiceException;
	
	/**
	 * 获取未授权操作员
	 * @author lmiky
	 * @date 2013-12-30
	 * @param moduleType
	 * @param moduleCode
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listUnauthorizedOperator(String moduleType, String moduleCode) throws ServiceException;
	
	/**
	 * 授权
	 * @author lmiky
	 * @date 2013-12-29
	 * @param authorityCode 权限编号
	 * @param authorities
	 * @throws ServiceException
	 */
	public void authorize(String authorityCode, List<Authority> authorities) throws ServiceException;
	
	/**
	 * 方法说明
	 * @author lmiky
	 * @date 2013-12-30
	 * @param AuthorityConfigs
	 * @param authorityCode
	 * @throws ServiceException
	 */
	public void authorize(List<AuthorityConfig> authorityConfigs, String authorityCode) throws ServiceException;
	
	/**
	 * 检查是否拥有权限
	 * @author lmiky
	 * @date 2013-5-24
	 * @param authorityCode	权限编号
	 * @param userId 用户ID
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkAuthority(String authorityCode, Long userId) throws ServiceException;

}
