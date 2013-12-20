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
	 * 获取已授权角色
	 * @author lmiky
	 * @date 2013-5-20
	 * @param moduleType	模块类别，详情值参考com.lmiky.jdp.authority.pojo.Authority的模块类别说明
	 * @param moduleId	模块ID
	 * @param functionId	功能ID
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listAuthorizedRole(String moduleType, Long moduleId, Long functionId) throws ServiceException;

	/**
	 * 获取未授权角色
	 * @author lmiky
	 * @date 2013-5-20
	 * @param moduleType	模块类别，详情值参考com.lmiky.jdp.authority.pojo.Authority的模块类别说明
	 * @param moduleId	模块ID
	 * @param functionId	功能ID
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listUnauthorizedRole(String moduleType, Long moduleId, Long functionId) throws ServiceException;
	
	/**
	 * 保存授权列表
	 * @author lmiky
	 * @date 2013-5-20
	 * @param authorities
	 * @throws ServiceException
	 */
	public void save(List<Authority> authorities) throws ServiceException;
	
	/**
	 * 检查是否拥有权限
	 * @author lmiky
	 * @date 2013-5-24
	 * @param moduleGroupId 模块组ID
	 * @param moduleId	模块ID
	 * @param functionId 方法ID
	 * @param userId 用户ID
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkAuthority(Long moduleGroupId, Long moduleId, Long functionId, Long userId) throws ServiceException;

}
